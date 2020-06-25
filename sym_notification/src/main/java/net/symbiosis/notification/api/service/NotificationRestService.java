package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
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
//	@GetMapping("/authedHello")
//	default String authedHello(SymSystemUser systemUser) { return "Hello " + principal.getUserInfo().getPreferredUsername(); }
    SymResponse sendSMS(SymSystemUser systemUser, String channel, String msisdn, String message);
	SymResponse resendSMS(SymSystemUser systemUser, String channel, Long notificationId);
}
