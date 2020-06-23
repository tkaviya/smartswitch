package net.symbiosis.authentication.api.service;

import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymSystemUserList;
import org.springframework.web.bind.annotation.GetMapping;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface AuthenticationRestService {
	@GetMapping("/hello")
	default String hello() { return "Hello from Authentication Service"; }
	SymSystemUserList registerMobileUser(String email, String msisdn, String username, String imei,
	                                     String companyName, String firstName, String lastName, String pin);
	SymSystemUserList registerPosUser(String branchName, String machineName, String imei1, String imei2, String imsi1,
	                                  String imsi2, String msisdn1, String msisdn2, String username, String pin);
	SymSystemUserList registerWebUser(String email, String msisdn, String msisdn2, String username, String deviceId,
	                                  String firstName, String lastName, String dateOfBirth);
	SymSystemUserList searchUser(String email, String msisdn, String username, String firstName, String lastName);
	SymResponse confirmRegistration(Long userId, String authToken, String deviceId, String channel, String username, String password);
	SymSystemUserList startSession(String authToken, String username, String channel, String password);
	SymResponse endSession(Long sessionId, String deviceId, String authToken);
}
