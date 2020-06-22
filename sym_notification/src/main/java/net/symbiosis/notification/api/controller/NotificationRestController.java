package net.symbiosis.notification.api.controller;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

import net.symbiosis.common.contract.SymResponse;
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

    @Override
    @PostMapping("/sms")
    public SymResponse sendSMS(@RequestParam("authToken") String authToken,
                               @RequestParam("channel") String channel,
                               @RequestParam("msisdn") String msisdn,
                               @RequestParam("message") String message) {
        logger.info(format("Got request to send SMS from user %s:%s to %s : %s", authToken, channel, msisdn, message));
        return notificationRequestProcessor.sendSMS(getRealParamValue(authToken), getRealParamValue(channel),
                                                    getRealParamValue(msisdn), getRealParamValue(message));
    }

    @Override
    @PostMapping("/sms/{notificationId}/resend")
    public SymResponse resendSMS(@RequestParam("authToken") String authToken,
                                 @RequestParam("channel") String channel,
                                 @PathVariable("notificationId") Long notificationId) {
        logger.info(format("Got request to resend SMS %s from user %s:%s", notificationId, authToken, channel));
        return notificationRequestProcessor.resendSMS(getRealParamValue(authToken), getRealParamValue(channel),
                                                    getRealParamValue(notificationId));
    }
}
