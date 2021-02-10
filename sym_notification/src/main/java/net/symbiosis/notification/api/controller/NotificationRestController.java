package net.symbiosis.notification.api.controller;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

import io.swagger.annotations.ApiOperation;
import net.symbiosis.common.contract.SymList;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymResponseData;
import net.symbiosis.common.contract.api.AuthenticationAPI;
import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.core_lib.enumeration.SymChannel;
import net.symbiosis.core_lib.response.SymResponseObject;
import net.symbiosis.notification.api.service.NotificationRequestProcessor;
import net.symbiosis.notification.api.service.NotificationRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.common.utilities.WebUtils.getRealParamValue;

@RestController
@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class NotificationRestController implements NotificationRestService {

    private final DiscoveryClient discoveryClient;

    private static final Logger logger = Logger.getLogger(NotificationRestController.class.getSimpleName());
    private final NotificationRequestProcessor notificationRequestProcessor;

    @Autowired
    public NotificationRestController(NotificationRequestProcessor notificationRequestProcessor,
                                      DiscoveryClient discoveryClient) {
        this.notificationRequestProcessor = notificationRequestProcessor;
        this.discoveryClient = discoveryClient;
    }

//    @Override
//    @PostMapping("/oauth/v2/callback")
//    public SymResponse oath2Callback(@AuthenticationPrincipal OidcUser principal) {
//	    logger.info(format("Got principal data for %s", principal.getUserInfo().getFullName());
//	    return null;
//    }

    private final SymSystemUser getAuthenticatedUser() {
        return AuthenticationAPI.validateAuth(null).getResponseObject();
    }

    private final SymChannel getDefaultChannel() {
        return SymChannel.SYSTEM;
    }

    @Override
    @ApiOperation(value = "Get SMS Messages with notificationIds between specified start and end IDs", response = SymResponseObject.class)
//    @RolesAllowed("ROLE_NOTIFICATION_HISTORY")
    @GetMapping("/sms")
    public SymList<SymNotification> getSMSs(@RequestParam("startId") Long startId,
                                           @RequestParam("endId") Long endId) {
        logger.info(format("Got request to get SMSs between ID %s and %s from user %s on channel %s",
                startId, endId, getAuthenticatedUser().getUsername(), getDefaultChannel().name()));
        return notificationRequestProcessor.getSMSs(getAuthenticatedUser(), getRealParamValue(getDefaultChannel().name()),
                getRealParamValue(startId),getRealParamValue(endId));
    }

    @Override
    @ApiOperation(value = "Get SMS Message with specified notificationId", response = SymResponseObject.class)
//    @RolesAllowed("ROLE_NOTIFICATION_HISTORY")
    @GetMapping("/sms/{notificationId}")
    public SymResponseData<SymNotification> getSMS(@PathVariable("notificationId") Long notificationId) {
        logger.info(format("Got request to get SMS with ID %s from user %s on channel %s",
                notificationId, getAuthenticatedUser().getUsername(), getDefaultChannel().name()));
        return notificationRequestProcessor.getSMS(getAuthenticatedUser(), getDefaultChannel().name(),
                                                    getRealParamValue(notificationId));
    }

    @Override
    @ApiOperation(value = "Send SMS Message to specified phone number", response = SymResponse.class)
//    @RolesAllowed("ROLE_NOTIFICATION_SEND")
    @PostMapping("/sms")
    public SymResponse sendSMS(@RequestParam("msisdn") String msisdn,
                               @RequestParam("message") String message) {
        logger.info(format("Got request to send SMS from user %s:%s to %s : %s", getAuthenticatedUser().getUsername(),
                getDefaultChannel().name(), msisdn, message));
        return notificationRequestProcessor.sendSMS(getAuthenticatedUser(), getDefaultChannel().name(),
                                                    getRealParamValue(msisdn), getRealParamValue(message));
    }

    @Override
    @ApiOperation(value = "Resend SMS Message by notification ID", response = SymResponse.class)
//    @RolesAllowed("ROLE_NOTIFICATION_SEND")
    @PostMapping("/sms/{notificationId}/resend")
    public SymResponse resendSMS(@PathVariable("notificationId") Long notificationId) {
        logger.info(format("Got request to resend SMS %s from user %s:%s", notificationId, getAuthenticatedUser(),
                getDefaultChannel().name()));
        return notificationRequestProcessor.resendSMS(getAuthenticatedUser(), getDefaultChannel().name(),
                                                    getRealParamValue(notificationId));
    }
}
