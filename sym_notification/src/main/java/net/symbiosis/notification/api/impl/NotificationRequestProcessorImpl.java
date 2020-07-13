package net.symbiosis.notification.api.impl;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import net.symbiosis.common.configuration.ThreadPoolManager;
import net.symbiosis.common.contract.SymList;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.common.persistence.entity.enumeration.sym_distribution_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.enumeration.SymDistributionChannel;
import net.symbiosis.core_lib.enumeration.SymResponseCode;
import net.symbiosis.core_lib.structure.Pair;
import net.symbiosis.notification.api.RequestProcessor;
import net.symbiosis.notification.api.service.ConverterService;
import net.symbiosis.notification.api.service.NotificationRequestProcessor;
import net.symbiosis.notification.persistence.log.sym_notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import static com.nexmo.client.sms.MessageStatus.OK;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.common.utilities.ValidationHelper.validateChannel;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
import static net.symbiosis.core_lib.enumeration.SymChannel.fromString;
import static net.symbiosis.core_lib.enumeration.SymEventType.NOTIFICATION_HISTORY;
import static net.symbiosis.core_lib.enumeration.SymEventType.SMS_NOTIFICATION;
import static net.symbiosis.core_lib.enumeration.SymNotificationStatus.*;
import static net.symbiosis.core_lib.enumeration.SymNotificationType.SMS;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.*;
import static net.symbiosis.core_lib.utilities.CommonUtilities.formatFullMsisdn;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/***************************************************************************
 *                                                                         *
 * Created:     12 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service
public class NotificationRequestProcessorImpl implements NotificationRequestProcessor, RequestProcessor {

    private static final Logger logger = Logger.getLogger(NotificationRequestProcessorImpl.class.getSimpleName());
	private final ConverterService converterService;

	@Autowired
	public NotificationRequestProcessorImpl(ConverterService converterService) {
		this.converterService = converterService;
	}

	@Override
	public SymList<SymNotification> getSMS(SymSystemUser systemUser, String channel, Long notificationId) {
		logger.info(format("Getting SMS notification with ID %s for user %s from channel %s", notificationId,
				systemUser.getUsername(), channel));

		sym_request_response_log requestResponseLog = new sym_request_response_log(
			fromEnum(fromString(channel)), fromEnum(NOTIFICATION_HISTORY), systemUser.getUserId(), systemUser.getAuthUserId(),
			format("userId=%s|channel=%s|notificationId=%s|", systemUser.getUserId(), channel, notificationId)
		).save();

		var notification = getEntityManagerRepo().findById(sym_notification.class, notificationId);

		if (notification == null || !notification.getNotification_type().equals(fromEnum(SMS))
								 || !notification.getAuth_user_id().equals(systemUser.getAuthUserId())) {
			logger.info(format("Could not find SMS notification with ID %s", notificationId));
			requestResponseLog.setResponse_code(fromEnum(DATA_NOT_FOUND))
				.setOutgoing_response(DATA_NOT_FOUND.getMessage())
				.setOutgoing_response_time(new Date())
				.save();
			return new SymList<>(DATA_NOT_FOUND);
		}

		requestResponseLog.setResponse_code(fromEnum(SUCCESS))
				.setOutgoing_response(notification.toString())
				.setOutgoing_response_time(new Date())
				.save();

		logger.info(format("Returning SMS: %s", notification.toString()));
		return new SymList<>(SUCCESS, new ArrayList<>(singletonList(converterService.toDTO(notification))));
	}

	@Override
	public SymList<SymNotification> getSMSs(SymSystemUser systemUser, String channel, Long startId, Long endId) {
		logger.info(format("Getting SMS notifications from ID %s to ID %s for user %s from channel %s", startId, endId,
				systemUser.getUsername(), channel));

		sym_request_response_log requestResponseLog = new sym_request_response_log(
				fromEnum(fromString(channel)), fromEnum(NOTIFICATION_HISTORY), systemUser.getUserId(), systemUser.getAuthUserId(),
				format("userId=%s|channel=%s|startId=%s|endId=%s|", systemUser.getUserId(), channel, startId, endId)
		).save();

		var notifications = getEntityManagerRepo().findWhere(sym_notification.class, asList(
			new Pair<>("id >", startId),
			new Pair<>("id <", endId),
			new Pair<>("sym_user_id", systemUser.getUserId()),
			new Pair<>("notification_type.name", SMS.name())
		));

		if (notifications == null) {
			logger.info(format("Could not find SMS notifications from ID %s to ID %s for user %s from channel %s",
					startId, endId, systemUser.getUsername(), channel));
			requestResponseLog.setResponse_code(fromEnum(DATA_NOT_FOUND))
					.setOutgoing_response(DATA_NOT_FOUND.getMessage())
					.setOutgoing_response_time(new Date())
					.save();
			return new SymList<>(DATA_NOT_FOUND);
		}

		logger.info(format("Returning %s SMSs", notifications.size()));
		ArrayList<SymNotification> smsNotification = new ArrayList<>();
		notifications.forEach((n) -> smsNotification.add(converterService.toDTO(n)));
		return new SymList<>(SUCCESS, smsNotification);
	}

	@Override
    public SymResponse sendSMS(SymSystemUser systemUser, String channel, String msisdn, String message) {

        logger.info(format("Got request to send SMS from %s:%s to %s. Message: \"%s\"", systemUser.getUsername(), channel, msisdn, message));

        sym_distribution_channel distributionChannel = fromEnum(SymDistributionChannel.SMS);
        if (!distributionChannel.getIs_enabled()) {
            logger.warning(format("Distribution channel %s is disabled! Will not process.", SymDistributionChannel.SMS.name()));
            return new SymResponse(NOT_SUPPORTED);
        }

        String incomingRequest = format("authUserId=%s|channel=%s|msisdn=%s|message=%s", systemUser.getAuthUserId(), channel, msisdn, message);

        //validate channel
        var channelResponse = validateChannel(channel);
        if (!channelResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Send SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        var log = new sym_request_response_log(channelResponse.getResponseObject(), fromEnum(SMS_NOTIFICATION),
		        systemUser.getUserId(), systemUser.getAuthUserId(), incomingRequest).save();

        //validate phone number
        final String phoneNumber = formatFullMsisdn(msisdn, getConfig(CONFIG_DEFAULT_COUNTRY_CODE));
//        if (!SymValidator.isValidFullMsisdn(phoneNumber)) {
//            String responseMessage = format("Send SMS failed! Invalid MSISDN %s", phoneNumber);
//            logger.warning(responseMessage);
//            logResponse(loginResponse.getResponseObject().getAuth_user_id(), log, INPUT_INVALID_MSISDN, responseMessage);
//            return new SymResponse(INPUT_INVALID_MSISDN);
//        }

        //validate message
        int maxLength = parseInt(getConfig(CONFIG_SMS_MAX_LENGTH));
        if (message.length() > maxLength) {
            String responseMessage = format("Send SMS failed! Message length is greater than %s ", maxLength);
            logger.warning(responseMessage);
            logResponse(systemUser.getAuthUserId(), systemUser.getAuthUserId(), log, INPUT_INVALID_REQUEST, responseMessage);
            return new SymResponse(INPUT_INVALID_REQUEST);
        }

        sym_notification notification = new sym_notification(systemUser.getUserId(), systemUser.getAuthUserId(),
	        phoneNumber, fromEnum(SMS), null, fromEnum(SENDING), new Date(), message, null
        ).save();

        submitVonageSMS(notification, systemUser, log);

        return new SymResponse(SUCCESS);
    }

    @Override
    public SymResponse resendSMS(SymSystemUser systemUser, String channel, Long notificationId) {
        logger.info(format("Got request to resend SMS %s by user %s:%s", notificationId, systemUser.getUsername(), channel));

        sym_distribution_channel distributionChannel = fromEnum(SymDistributionChannel.SMS);
        if (!distributionChannel.getIs_enabled()) {
            logger.warning(format("Distribution channel %s is disabled! Will not process.", SymDistributionChannel.SMS.name()));
            return new SymResponse(NOT_SUPPORTED);
        }

        String incomingRequest = format("authUserId=%s|channel=%s|notificationId=%s", systemUser.getAuthUserId(), channel, notificationId);

        //validate channel
        var channelResponse = validateChannel(channel);
        if (!channelResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Resend SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        var log = new sym_request_response_log(channelResponse.getResponseObject(), fromEnum(SMS_NOTIFICATION),
		        systemUser.getUserId(), systemUser.getAuthUserId(), incomingRequest).save();

        var notification = getEntityManagerRepo().findById(sym_notification.class, notificationId);
        if (notification == null || !notification.getNotification_type().getName().equals(SMS.name())) {
            String responseMessage = format("Resend SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        submitVonageSMS(notification, systemUser, log);
        return new SymResponse(SUCCESS);
    }

    private static void submitVonageSMS(sym_notification notification, SymSystemUser systemUser, sym_request_response_log log){

        ThreadPoolManager.schedule(() -> {

            if (!getConfig(CONFIG_SMS_ENABLE).toLowerCase().equals("true")) {
                logger.warning("SMSs are disabled. Will not send SMS");
                notification.setNotification_status(fromEnum(FAILED)).save();
                logResponse(systemUser, log, NOT_SUPPORTED, "SMSs are disabled. Will not send SMS");
                return;
            }

            //send message
            try {

                logger.fine("Preparing to send the SMS message.");
                NexmoClient nexmoClient = new NexmoClient.Builder()
                        .apiKey(getConfig(CONFIG_VONAGE_API_KEY))
                        .apiSecret(getConfig(CONFIG_VONAGE_API_SECRET))
                        .build();

                var textMessage = new TextMessage(getConfig(CONFIG_VONAGE_SMS_FROM), notification.getRecipient(), notification.getNotification());

                int numRetries = parseInt(getConfig(CONFIG_VONAGE_SMS_MAX_RETRIES));
                while (numRetries-- != 0) {
                    logger.info(format("[%s tries left] Submitting SMS to %s: %s", numRetries + 1, notification.getRecipient(), notification.getNotification()));
                    notification.setNotification_status(fromEnum(SENDING));
                    SmsSubmissionResponse smsSubmissionResponse = nexmoClient.getSmsClient().submitMessage(textMessage);

                    for (SmsSubmissionResponseMessage smsSubmissionResponseMessage : smsSubmissionResponse.getMessages()) {
                        logger.info(format("Processing response: %s", smsSubmissionResponseMessage));

                        if (notification.getRemote_reference() == null && smsSubmissionResponseMessage.getId() != null) {
                            notification.setRemote_reference(smsSubmissionResponseMessage.getId());
                        }

                        if (smsSubmissionResponseMessage.getStatus().equals(OK)) {
                            notification.setNotification_status(fromEnum(SENT));
                        } else if (smsSubmissionResponseMessage.isTemporaryError()) {
                            notification.setNotification_status(fromEnum(QUEUED));
                        } else {
                            notification.setNotification_status(fromEnum(FAILED));
                            notification.setSubmit_response(smsSubmissionResponseMessage.getErrorText());
                        }
                    }
                    if (!notification.getNotification_status().equals(fromEnum(QUEUED))) {
                        break; //we have a valid status, we can exit the retry loop
                    } else {
                        Thread.sleep(parseInt(getConfig(CONFIG_VONAGE_SMS_RETRY_WAIT_TIME)));
                    }
                }
                if (notification.getNotification_status().equals(fromEnum(QUEUED))) {
                    //If we exit retry loop while message is still queued then it will never send, its a failure
                    notification.setNotification_status(fromEnum(FAILED));
                    logResponse(systemUser, log, CONNECTION_FAILED, notification.getRemote_reference() +
                        ": " + notification.getNotification_status().getName());
                } else {
                    logResponse(systemUser, log, SUCCESS, notification.getRemote_reference() +
                        ": " + notification.getNotification_status().getName());
                }
                notification.save();

            } catch (Exception ex) {
                ex.printStackTrace();
                String responseMessage = "Send SMS failed! ".concat(ex.getMessage());
                logger.severe(responseMessage);
                if (notification.getNotification_status().equals(fromEnum(SENDING))) {
                    notification.setNotification_status(fromEnum(FAILED)).save();
                } else {
                    notification.save();
                }
                logResponse(systemUser, log, GENERAL_ERROR, responseMessage);
            }
        });
    }

    private static void logResponse(SymSystemUser systemUser, sym_request_response_log log,
                                    SymResponseCode responseCode, String responseMessage) {
        if (systemUser != null) { log.setAuth_user_id(systemUser.getAuthUserId()); }
        if (systemUser != null) { log.setSystem_user_id(systemUser.getUserId()); }
        log.setOutgoing_response(responseMessage);
        log.setOutgoing_response_time(new Date());
        log.setResponse_code(fromEnum(responseCode));
        log.save();
    }
}
