package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.SymList;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymResponseData;
import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;

/***************************************************************************
 *                                                                         *
 * Created:     01 / 05 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface NotificationRequestProcessor {

	SymResponseData<SymNotification> getSMS(SymSystemUser systemUser, String channel, Long notificationId);
	SymList<SymNotification> getSMSs(SymSystemUser systemUser, String channel, Long startId, Long endId);
	SymResponse sendSMS(SymSystemUser systemUser, String channel, String msisdn, String message);
	SymResponse resendSMS(SymSystemUser systemUser, String channel, Long notificationId);
}
