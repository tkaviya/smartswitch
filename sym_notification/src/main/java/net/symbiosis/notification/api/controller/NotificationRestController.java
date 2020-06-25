package net.symbiosis.notification.api.controller;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

import io.swagger.annotations.ApiOperation;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.notification.api.service.NotificationRequestProcessor;
import net.symbiosis.notification.api.service.NotificationRestService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.common.utilities.WebUtils.getRealParamValue;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*")
public class NotificationRestController implements NotificationRestService {

    private static final Logger logger = Logger.getLogger(NotificationRestController.class.getSimpleName());
    private final NotificationRequestProcessor notificationRequestProcessor;

    public NotificationRestController(NotificationRequestProcessor notificationRequestProcessor) {
        this.notificationRequestProcessor = notificationRequestProcessor;
    }

//    @Override
//    @PostMapping("/oauth/v2/callback")
//    public SymResponse oath2Callback(@AuthenticationPrincipal OidcUser principal) {
//	    logger.info(format("Got principal data for %s", principal.getUserInfo().getFullName());
//	    return null;
//    }

    @Override
    @ApiOperation(value = "Send SMS Message to specified phone number", response = SymResponse.class)
//    @RolesAllowed("ROLE_SMS_SEND")
    @PostMapping("/sms")
    public SymResponse sendSMS(@RequestParam("systemUser") SymSystemUser systemUser,
                               @RequestParam("channel") String channel,
                               @RequestParam("msisdn") String msisdn,
                               @RequestParam("message") String message) {
        logger.info(format("Got request to send SMS from user %s:%s to %s : %s", systemUser.getUsername(), channel, msisdn, message));
        return notificationRequestProcessor.sendSMS(systemUser, getRealParamValue(channel),
                                                    getRealParamValue(msisdn), getRealParamValue(message));
    }

    @Override
    @ApiOperation(value = "Resend SMS Message by notification ID", response = SymResponse.class)
//    @RolesAllowed("ROLE_SMS_SEND")
    @PostMapping("/sms/{notificationId}/resend")
    public SymResponse resendSMS(@RequestParam("systemUser") SymSystemUser systemUser,
                                 @RequestParam("channel") String channel,
                                 @PathVariable("notificationId") Long notificationId) {
        logger.info(format("Got request to resend SMS %s from user %s:%s", notificationId, systemUser.getUsername(), channel));
        return notificationRequestProcessor.resendSMS(systemUser, getRealParamValue(channel),
                                                    getRealParamValue(notificationId));
    }
}
