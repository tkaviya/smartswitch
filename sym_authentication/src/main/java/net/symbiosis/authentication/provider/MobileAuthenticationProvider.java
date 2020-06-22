package net.symbiosis.authentication.provider;

/**************************************************************************
 *																	      *
 * Created:	 2016/01/01											    	  *
 * Author:	  Tich de Blak (Tsungai Kaviya)					    		  *
 *																		  *
 *************************************************************************/

import net.symbiosis.authentication.persistence.entity.device.sym_device_phone;
import net.symbiosis.authentication.persistence.entity.*;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.response.SymResponseObject;
import net.symbiosis.core_lib.structure.Pair;
import net.symbiosis.core_lib.utilities.CommonUtilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static net.symbiosis.authentication.AuthenticationHelper.getEmailTemplate;
import static net.symbiosis.authentication.provider.SymbiosisAuthenticator.registerUser;
import static net.symbiosis.common.configuration.NetworkUtilities.sendEmail;
import static net.symbiosis.common.contract.api.NotificationAPI.sendSMS;
import static net.symbiosis.common.mail.EMailer.CONTENT_TYPE_HTML;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
import static net.symbiosis.core_lib.enumeration.SymChannel.SMART_PHONE;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;
import static net.symbiosis.persistence.dao.EnumEntityRepoManager.findByName;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

public class MobileAuthenticationProvider extends SymChainAuthenticationProvider {

	private static final Logger logger = Logger.getLogger(MobileAuthenticationProvider.class.getSimpleName());
	private static final sym_channel CHANNEL = fromEnum(SMART_PHONE);

	@Override
	protected void initializeAuthenticationChain() {
		authenticationChain.put(CHANNEL, new ArrayList<>(singletonList(this::startSession)));
	}

	public MobileAuthenticationProvider(sym_request_response_log requestResponseLog, String username,
	                                    String pin, String deviceId) {
		super(requestResponseLog, CHANNEL);
		setAuthUsername(username);
		setAuthPassword(pin);
		setDeviceId(deviceId);
	}

	public MobileAuthenticationProvider(sym_request_response_log requestResponseLog, sym_auth_user authUser) {
		super(requestResponseLog, CHANNEL);
		setUser(authUser.getUser());
		setAuthUser(authUser);
	}

	public SymResponseObject<sym_auth_user> registerMobileUser(sym_user newUser, String imei, sym_auth_group authGroup, sym_company company) {

		logger.info(format("Performing MOBILE registration for %s %s", newUser.getFirst_name(), newUser.getLast_name()));

		SymResponseObject<sym_auth_user> registrationResponse = registerUser(newUser, fromEnum(SMART_PHONE),
				findByName(sym_auth_group.class, authGroup == null ? getConfig(CONFIG_DEFAULT_SMART_PHONE_AUTH_GROUP) : authGroup.getName()),
				null);

		if (registrationResponse.getResponseCode().getCode() != SUCCESS.getCode()) {
			return registrationResponse;
		}

		symAuthUser = registrationResponse.getResponseObject();
		symUser = registrationResponse.getResponseObject().getUser();

		if (company != null) {
			logger.info(format("Saving company for user %s", newUser.getUsername()));
			company.save();
		}

		logger.info(format("Creating wallet for user %s", newUser.getUsername()));
		sym_wallet wallet = new sym_wallet(
			newUser, findByName(sym_wallet_group.class, getConfig(CONFIG_DEFAULT_WALLET_GROUP)),
			company, new BigDecimal(0)
		).save();

		newUser.setWallet(wallet).save();

		if (!CommonUtilities.isNullOrEmpty(imei)) {
			List<sym_device_phone> existingPhones = getEntityManagerRepo().findWhere(sym_device_phone.class,
					asList(new Pair<>("imei1", imei), new Pair<>("imei2", imei)),
					false, false, true, false);
			if (existingPhones != null && existingPhones.size() > 0) {
				for (sym_device_phone existingPhone : existingPhones) {
					if (!existingPhone.getAuth_user().getId().equals(symAuthUser.getId())) {
						//new user using this device, disable old user
						existingPhone.setIs_active(false);
					}
				}
			}

			new sym_device_phone(symAuthUser, true, new Date(), new Date(),
				imei, null,null, null, null, null
			).save();
		}

		if (newUser.getEmail() != null) {

			logger.info("Sending " + SMART_PHONE.name() + " registration email");
			String emailTemplate = getEmailTemplate("authentication/app_reg_success.html")
					.replaceAll("%reg_fname%", newUser.getFirst_name())
					.replaceAll("%reg_lname%", newUser.getLast_name())
					.replaceAll("%reg_username%", newUser.getUsername())
					.replaceAll("%reg_userId%", String.valueOf(registrationResponse.getResponseObject().getId().intValue()))
					.replaceAll("%reg_authToken%", registrationResponse.getResponseObject().getAuth_token());

			sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[]{newUser.getEmail(),
					getConfig(CONFIG_EMAIL_ALERT_TO)}, "Welcome to " +
					getConfig(CONFIG_SYSTEM_NAME) + " Wallet Platform", emailTemplate, CONTENT_TYPE_HTML);
		}

		if (newUser.getMsisdn() != null) {

			logger.info("Sending " + SMART_PHONE.name() + " registration SMS");
			String smsMessage = getConfig(CONFIG_SMS_MOBILE_REGISTRATION)
					.replaceAll("%sys_name%", getConfig(CONFIG_SYSTEM_NAME))
					.replaceAll("%username%", symUser.getUsername());

			sendSMS(parseLong(getConfig(CONFIG_SYSTEM_USER_ID)), SMART_PHONE.name(), newUser.getMsisdn(), smsMessage);
		}

		return registrationResponse;
	}

	public SymResponseObject<sym_auth_user> validatePin(sym_auth_user authUser, String pin) {
		return SymbiosisAuthenticator.validatePin(authUser, pin);
	}

	public SymResponseObject<sym_auth_user> changePin(sym_auth_user authUser, String newPin, boolean validatePrevious, String oldPin) {

		SymResponseObject<sym_auth_user> passResponse =
				SymbiosisAuthenticator.changePin(authUser, newPin, validatePrevious, oldPin);

		if (passResponse.getResponseCode().equals(SUCCESS)) {

			if (authUser.getUser().getEmail() != null) {

				logger.info("Sending " + SMART_PHONE.name() + " pin reset email");
				String emailTemplate = getEmailTemplate("authentication/pin_change_success.html")
						.replaceAll("%auth_fname%", authUser.getUser().getFirst_name())
						.replaceAll("%auth_lname%", authUser.getUser().getLast_name())
						.replaceAll("%auth_username%", authUser.getUser().getUsername())
						.replaceAll("%auth_pin%", newPin);

				sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[]{authUser.getUser().getEmail(),
						getConfig(CONFIG_EMAIL_ALERT_TO)}, "Pin Changed on " +
						getConfig(CONFIG_SYSTEM_NAME) + " Wallet Platform", emailTemplate, CONTENT_TYPE_HTML);
			}

			if (authUser.getUser().getMsisdn() != null) {

				logger.info("Sending " + SMART_PHONE.name() + " pin reset SMS");
				String smsMessage = getConfig(CONFIG_SMS_PIN_RESET)
						.replaceAll("%sys_name%", getConfig(CONFIG_SYSTEM_NAME))
						.replaceAll("%auth_pin%", newPin
						.replaceAll("%username%", symUser.getUsername()));

				sendSMS(parseLong(getConfig(CONFIG_SYSTEM_USER_ID)), SMART_PHONE.name(), authUser.getUser().getMsisdn(), smsMessage);
			}
		}

		return passResponse;
	}
}
