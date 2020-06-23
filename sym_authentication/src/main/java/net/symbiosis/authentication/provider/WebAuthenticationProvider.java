package net.symbiosis.authentication.provider;

/* *************************************************************************
 * Created:     2016/01/01                                                 *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 */

import net.symbiosis.authentication.persistence.entity.*;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.response.SymResponseObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static net.symbiosis.authentication.helper.AuthenticationHelper.getEmailTemplate;
import static net.symbiosis.authentication.provider.SymbiosisAuthenticator.registerUser;
import static net.symbiosis.common.configuration.NetworkUtilities.sendEmail;
import static net.symbiosis.common.mail.EMailer.CONTENT_TYPE_HTML;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.*;
import static net.symbiosis.core_lib.enumeration.SymChannel.WEB;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;
import static net.symbiosis.persistence.dao.EnumEntityRepoManager.findByName;

public class WebAuthenticationProvider extends SymChainAuthenticationProvider {

    private static final Logger logger = Logger.getLogger(WebAuthenticationProvider.class.getSimpleName());
    private static final sym_channel CHANNEL = fromEnum(WEB);

    @Override
    protected void initializeAuthenticationChain() {
        authenticationChain.put(CHANNEL, new ArrayList<>(singletonList(this::startSession)));
    }

    public WebAuthenticationProvider(sym_request_response_log requestResponseLog, String username, String password, String deviceId) {
        super(requestResponseLog, CHANNEL);
        setAuthUsername(username);
        setAuthPassword(password);
        setDeviceId(deviceId);
    }

    public WebAuthenticationProvider(sym_request_response_log requestResponseLog, sym_auth_user authUser) {
        super(requestResponseLog, CHANNEL);
        setUser(authUser.getUser());
        setAuthUser(authUser);
    }

    public SymResponseObject<sym_auth_user> registerWebUser(sym_user newUser,
                                                            sym_company newCompany, sym_auth_group authGroup, sym_wallet wallet) {

        logger.info(format("Performing WEB registration for %s %s", newUser.getFirst_name(), newUser.getLast_name()));

        var registrationResponse = registerUser(newUser, fromEnum(WEB),
            findByName(sym_auth_group.class, authGroup == null ? getConfig(CONFIG_DEFAULT_WEB_AUTH_GROUP) : authGroup.getName()),
            null);

        if (registrationResponse.getResponseCode().equals(SUCCESS)) {
            return registrationResponse;
        }

        symAuthUser = registrationResponse.getResponseObject();
        symUser = registrationResponse.getResponseObject().getUser();

        logger.info(format("Saving user company %s", newCompany.getCompany_name()));
        newCompany.save();

        if (wallet == null) {
            logger.info(format("Creating wallet for user %s", newUser.getUsername()));
            wallet = new sym_wallet(
                newUser, findByName(sym_wallet_group.class, getConfig(CONFIG_DEFAULT_WALLET_GROUP)),
                newCompany, new BigDecimal(0)
            ).save();
        } else {
            logger.info(format("Setting user wallet to %s for user %s", wallet.getId(), newUser.getUsername()));
        }

        newUser.setWallet(wallet).save();

        logger.info("Sending WEB registration email");
        String emailTemplate = getEmailTemplate("authentication/web_reg_success.html")
            .replaceAll("%reg_lname%", newUser.getLast_name())
            .replaceAll("%reg_username%", newUser.getUsername())
            .replaceAll("%reg_userId%", String.valueOf(registrationResponse.getResponseObject().getId().intValue()))
            .replaceAll("%reg_authToken%", registrationResponse.getResponseObject().getAuth_token());

        sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[]{newUser.getEmail(),
                  getConfig(CONFIG_EMAIL_ALERT_TO)}, "Welcome to " +
                  getConfig(CONFIG_SYSTEM_NAME) + " Control Center!", emailTemplate, CONTENT_TYPE_HTML);

        return registrationResponse;
    }

    public SymResponseObject<sym_auth_user> changePassword(sym_auth_user authUser,
                                                           String newPassword, boolean validatePrevious, String oldPassword) {

        SymResponseObject<sym_auth_user> passResponse =
                SymbiosisAuthenticator.changePassword(authUser, newPassword, validatePrevious, oldPassword);

        if (passResponse.getResponseCode().equals(SUCCESS)) {
            logger.info("Sending password changed email");

            String emailTemplate = getEmailTemplate("authentication/pass_change_success.html")
                .replaceAll("%auth_fname%", authUser.getUser().getFirst_name())
                .replaceAll("%auth_lname%", authUser.getUser().getLast_name())
                .replaceAll("%auth_username%", authUser.getUser().getUsername())
                .replaceAll("%auth_password%", newPassword);

            sendEmail(getConfig(CONFIG_SYSTEM_NAME), new String[]{authUser.getUser().getEmail(),
                      getConfig(CONFIG_EMAIL_ALERT_TO)}, "Password Changed on " +
                      getConfig(CONFIG_SYSTEM_NAME) + " Control Center", emailTemplate, CONTENT_TYPE_HTML);

        }
        return passResponse;
    }
}
