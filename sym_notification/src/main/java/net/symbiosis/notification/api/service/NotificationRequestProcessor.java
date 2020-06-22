package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.SymResponse;

/***************************************************************************
 *                                                                         *
 * Created:     01 / 05 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface NotificationRequestProcessor {
	SymResponse sendSMS(String authToken, String channel, String msisdn, String message);
	SymResponse resendSMS(String authToken, String channel, Long notificationId);
}
