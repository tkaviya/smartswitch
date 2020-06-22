package net.symbiosis.common.contract.api;

import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.core_lib.response.SymResponseObject;

import java.util.Date;

import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;

/***************************************************************************
 *                                                                         *
 * Created:     17 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public class AuthenticationAPI {

	public static SymResponseObject<SymSystemUser> validateAuth(String authToken) {

		var user = new SymSystemUser(1L, 1L, 1L, 1L, "Tsungai", "Kaviya", "admin",
			"tsungai.kaviya@gmail.com", "263785107830", null, "T3raTech Solutions", 0.0,
			"SUPER_USER", null, null, new Date());

		return new SymResponseObject<>(SUCCESS, user);
	}
}
