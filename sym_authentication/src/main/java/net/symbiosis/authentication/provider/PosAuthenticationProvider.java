package net.symbiosis.authentication.provider;

/* *************************************************************************
 * Created:     2016/01/01                                                 *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
*/

import net.symbiosis.authentication.persistence.entity.sym_auth_user;
import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.response.SymResponseObject;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static net.symbiosis.authentication.AuthenticationHelper.getEmailTemplate;
import static net.symbiosis.authentication.provider.SymbiosisAuthenticator.assignChannel;
import static net.symbiosis.common.configuration.NetworkUtilities.sendEmail;
import static net.symbiosis.common.contract.api.NotificationAPI.sendSMS;
import static net.symbiosis.common.mail.EMailer.CONTENT_TYPE_HTML;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
import static net.symbiosis.core_lib.enumeration.SymChannel.POS_MACHINE;
import static net.symbiosis.core_lib.enumeration.SymChannel.SMART_PHONE;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;
import static net.symbiosis.core_lib.utilities.ReferenceGenerator.GenPin;
import static net.symbiosis.persistence.dao.EnumEntityRepoManager.findByName;

public class PosAuthenticationProvider extends SymChainAuthenticationProvider {

	private static final Logger logger = Logger.getLogger(PosAuthenticationProvider.class.getSimpleName());
    private static final sym_channel CHANNEL = fromEnum(POS_MACHINE);

	@Override
	protected void initializeAuthenticationChain() {
		authenticationChain.put(CHANNEL, new ArrayList<>(singletonList(this::validatePin)));
	}

	public PosAuthenticationProvider(sym_request_response_log requestResponseLog,
	                                 String deviceId, String username, String pin) {
		super(requestResponseLog, CHANNEL);
        setDeviceId(deviceId);
        setAuthUsername(username);
        setAuthPassword(pin);
    }

    public SymResponseObject<sym_auth_user> assignPOSChannel(sym_user user, sym_auth_group authGroup) {

        logger.info(format("Performing POS registration for %s %s", user.getFirst_name(), user.getLast_name()));

        SymResponseObject<sym_auth_user> registrationResponse = assignChannel(user, fromEnum(POS_MACHINE),
            findByName(sym_auth_group.class, authGroup == null ? getConfig(CONFIG_DEFAULT_POS_MACHINE_AUTH_GROUP) : authGroup.getName()),
            null);

        if (registrationResponse.getResponseCode().getCode() != SUCCESS.getCode()) {
            return registrationResponse;
        }

        symAuthUser = registrationResponse.getResponseObject();
        symUser = registrationResponse.getResponseObject().getUser();

        String newPin = GenPin();
        logger.info("Setting channel pin to " + newPin);
        SymbiosisAuthenticator.changePin(symAuthUser, newPin, false, null);

        logger.info("Sending POS registration email");
        if (symUser.getEmail() != null) {
            String emailTemplate = getEmailTemplate("authentication/pos_reg_success.html")
                    .replaceAll("%reg_fname%", user.getFirst_name())
                    .replaceAll("%reg_lname%", user.getLast_name())
                    .replaceAll("%reg_username%", user.getUsername())
                    .replaceAll("%reg_pin%", newPin);

            sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[]{user.getEmail(),
                    getConfig(CONFIG_EMAIL_ALERT_TO)}, "Welcome to " +
                    getConfig(CONFIG_SYSTEM_NAME), emailTemplate, CONTENT_TYPE_HTML);
        }

        if (symUser.getMsisdn() != null) {

	        logger.info("Sending POS registration SMS");
	        String smsMessage = getConfig(CONFIG_SMS_POS_REGISTRATION)
			        .replaceAll("%sys_name%", getConfig(CONFIG_SYSTEM_NAME))
			        .replaceAll("%username%", symUser.getUsername())
			        .replaceAll("%pin%", newPin);

	        sendSMS(parseLong(getConfig(CONFIG_SYSTEM_USER_ID)), SMART_PHONE.name(), symUser.getMsisdn(), smsMessage);
        }

        return registrationResponse;
    }

    public SymResponseObject<sym_auth_user> changePin(sym_auth_user authUser,
                                                      String newPin, boolean validatePrevious, String oldPin) {

        SymResponseObject<sym_auth_user> passResponse =
                SymbiosisAuthenticator.changePin(authUser, newPin, validatePrevious, oldPin);

        if (passResponse.getResponseCode().equals(SUCCESS)) {
            logger.info("Sending pin changed email");

            String emailTemplate = getEmailTemplate("authentication/pin_change_success.html")
                    .replaceAll("%auth_fname%", authUser.getUser().getFirst_name())
                    .replaceAll("%auth_lname%", authUser.getUser().getLast_name())
                    .replaceAll("%auth_username%", authUser.getUser().getUsername())
                    .replaceAll("%auth_pin%", newPin);

            sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[] {authUser.getUser().getEmail(),
                      getConfig(CONFIG_EMAIL_ALERT_TO)}, "Pin changed on " +
                      getConfig(CONFIG_SYSTEM_NAME), emailTemplate, CONTENT_TYPE_HTML);

        }

        return passResponse;
    }
}
