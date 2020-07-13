package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.SymList;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymResponseData;
import net.symbiosis.common.contract.symbiosis.SymNotification;
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
	SymList<SymNotification> getSMSs(Long startId, Long endId);
	SymResponseData<SymNotification> getSMS(Long notificationId);
	SymResponse sendSMS(String msisdn, String message);
	SymResponse resendSMS(Long notificationId);
}
