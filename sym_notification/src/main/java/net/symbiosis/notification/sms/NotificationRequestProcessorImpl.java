package net.symbiosis.notification.sms;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import net.symbiosis.common.configuration.ThreadPoolManager;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.api.AuthenticationAPI;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.common.persistence.entity.enumeration.sym_distribution_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.enumeration.SymDistributionChannel;
import net.symbiosis.core_lib.enumeration.SymResponseCode;
import net.symbiosis.notification.api.RequestProcessor;
import net.symbiosis.notification.api.service.NotificationRequestProcessor;
import net.symbiosis.notification.persistence.log.sym_notification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

import static com.nexmo.client.sms.MessageStatus.OK;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.common.utilities.ValidationHelper.validateChannel;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
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
    private static NotificationRequestProcessor instance = null;

    public NotificationRequestProcessorImpl() {
        instance = this;
    }

    @Override
    public SymResponse sendSMS(String authToken, String channel, String msisdn, String message) {

        logger.info(format("Got request to send SMS by user %s:%s to %s. Message: \"%s\"", authToken, channel, msisdn, message));

        sym_distribution_channel distributionChannel = fromEnum(SymDistributionChannel.SMS);
        if (!distributionChannel.getIs_enabled()) {
            logger.warning(format("Distribution channel %s is disabled! Will not process.", SymDistributionChannel.SMS.name()));
            return new SymResponse(NOT_SUPPORTED);
        }

        String incomingRequest = format("authUserId=%s|channel=%s|msisdn=%s|message=%s", authToken, channel, msisdn, message);

        //validate channel
        var channelResponse = validateChannel(channel);
        if (!channelResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Send SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        var log = new sym_request_response_log(channelResponse.getResponseObject(), fromEnum(SMS_NOTIFICATION), incomingRequest);

        //validate auth user
        var authUserResponse = AuthenticationAPI.validateAuth(authToken);
        if (!authUserResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Send SMS failed! %s", authUserResponse.getMessage());
            logger.warning(responseMessage);
            logResponse(authUserResponse.getResponseObject().getAuthUserId(),
                authUserResponse.getResponseObject().getUserId(),
                log, authUserResponse.getResponseCode(), responseMessage);
            return new SymResponse(authUserResponse.getResponseCode());
        }

        var authUser = authUserResponse.getResponseObject();

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
            logResponse(authUserResponse.getResponseObject().getAuthUserId(),
                    authUserResponse.getResponseObject().getUserId(),
                    log, INPUT_INVALID_REQUEST, responseMessage);
            return new SymResponse(INPUT_INVALID_REQUEST);
        }

        sym_notification notification = new sym_notification(
            authUserResponse.getResponseObject().getAuthUserId(), phoneNumber, fromEnum(SMS),
            null, fromEnum(SENDING), new Date(), message, null
        ).save();

        submitVonageSMS(notification, authUser, log);

        return new SymResponse(SUCCESS);
    }

    @Override
    public SymResponse resendSMS(String authToken, String channel, Long notificationId) {
        logger.info(format("Got request to resend SMS %s by user %s:%s", notificationId, authToken, channel));

        sym_distribution_channel distributionChannel = fromEnum(SymDistributionChannel.SMS);
        if (!distributionChannel.getIs_enabled()) {
            logger.warning(format("Distribution channel %s is disabled! Will not process.", SymDistributionChannel.SMS.name()));
            return new SymResponse(NOT_SUPPORTED);
        }

        String incomingRequest = format("authUserId=%s|channel=%s|notificationId=%s", authToken, channel, notificationId);

        //validate channel
        var channelResponse = validateChannel(channel);
        if (!channelResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Resend SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        var log = new sym_request_response_log(channelResponse.getResponseObject(), fromEnum(SMS_NOTIFICATION), incomingRequest).save();

        var notification = getEntityManagerRepo().findById(sym_notification.class, notificationId);
        if (notification == null || !notification.getNotification_type().getName().equals(SMS.name())) {
            String responseMessage = format("Resend SMS failed! %s", channelResponse.getMessage());
            logger.warning(responseMessage);
            return new SymResponse(channelResponse.getResponseCode());
        }

        //validate auth user
        var authUserResponse = AuthenticationAPI.validateAuth(authToken);
        if (!authUserResponse.getResponseCode().equals(SUCCESS)) {
            String responseMessage = format("Send SMS failed! %s", authUserResponse.getMessage());
            logger.warning(responseMessage);
            logResponse(authUserResponse.getResponseObject().getAuthUserId(),
                    authUserResponse.getResponseObject().getUserId(),
                    log, authUserResponse.getResponseCode(), responseMessage);
            return new SymResponse(authUserResponse.getResponseCode());
        }

        submitVonageSMS(notification, authUserResponse.getResponseObject(), log);
        return new SymResponse(SUCCESS);
    }

    //    @Override
    public static SymResponse resendSMS(sym_notification notification, SymSystemUser systemUser, sym_request_response_log log) {
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
