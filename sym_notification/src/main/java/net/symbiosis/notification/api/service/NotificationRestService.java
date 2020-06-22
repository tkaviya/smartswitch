package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.SymResponse;
import org.springframework.web.bind.annotation.GetMapping;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

public interface NotificationRestService {
	@GetMapping("/hello")
	default String hello() { return "Hello from Notification Service"; }
    SymResponse sendSMS(String authToken, String channel, String msisdn, String message);
	SymResponse resendSMS(String authToken, String channel, Long notificationId);
}
