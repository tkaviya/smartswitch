package net.symbiosis.authentication.api.impl;

import net.symbiosis.authentication.api.service.AuthenticationRequestProcessor;
import net.symbiosis.authentication.api.service.ConverterService;
import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymSystemUserList;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.core_lib.enumeration.SymChannel;
import net.symbiosis.core_lib.structure.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.INPUT_INCOMPLETE_REQUEST;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;
import static net.symbiosis.core_lib.utilities.CommonUtilities.isNullOrEmpty;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service
public class AuthenticationRequestProcessorImpl implements AuthenticationRequestProcessor {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private final ConverterService converterService;

	@Autowired
	public AuthenticationRequestProcessorImpl(ConverterService converterService) {
		this.converterService = converterService;
	}

	@Override
	public SymSystemUserList searchUser(String email, String msisdn, String username, String firstName, String lastName) {
		logger.info(format("Search for systemUser email=%s, msisdn=%s, username=%s, firstName=%s, lastName=%s",
				email, msisdn, username, firstName, lastName));

		if (isNullOrEmpty(username) && isNullOrEmpty(email) && isNullOrEmpty(msisdn) &&
				isNullOrEmpty(firstName) && isNullOrEmpty(lastName)) {
			SymSystemUserList response = new SymSystemUserList(INPUT_INCOMPLETE_REQUEST);
			response.getSymResponse()
					.setResponse_message("Cannot search for user with no search parameters (email/msisdn/username/firstName/lastName)");
			return response;
		}

		ArrayList<SymSystemUser> systemUsers = new ArrayList<>();

		ArrayList<Pair<String, ?>> searchTerms = new ArrayList<>();

		if (!isNullOrEmpty(username)) {
			searchTerms.add(new Pair<>("username", username));
		}
		if (!isNullOrEmpty(email)) {
			searchTerms.add(new Pair<>("email", email));
		}
		if (!isNullOrEmpty(msisdn)) {
			searchTerms.add(new Pair<>("msisdn", msisdn));
		}
		if (!isNullOrEmpty(msisdn)) {
			searchTerms.add(new Pair<>("msisdn2", msisdn));
		}
		if (!isNullOrEmpty(firstName)) {
			searchTerms.add(new Pair<>("first_name", firstName));
		}
		if (!isNullOrEmpty(lastName)) {
			searchTerms.add(new Pair<>("last_name", lastName));
		}

		getEntityManagerRepo().findWhere(sym_user.class, searchTerms,
				false, true, true, false)
				.forEach(v -> systemUsers.add(converterService.toDTO(v)));

		logger.info(format("Returning %s systemUsers", systemUsers.size()));

		return new SymSystemUserList(SUCCESS, systemUsers);

	}

	@Override
	public SymSystemUserList registerMobileUser(String email, String msisdn, String username, String imei, String companyName, String firstName, String lastName, String pin) {
		return null;
	}

	@Override
	public SymSystemUserList registerPosUser(String branchName, String machineName, String imei1, String imei2, String imsi1, String imsi2, String msisdn1, String msisdn2, String username, String pin) {
		return null;
	}

	@Override
	public SymSystemUserList registerWebUser(String email, String msisdn, String msisdn2, String username, String deviceId, String firstName, String lastName, String dateOfBirth) {
		return null;
	}

	@Override
	public SymResponse confirmRegistration(Long userId, String authToken, String deviceId, String username, SymChannel fromString, String password) {
		return null;
	}

	@Override
	public SymSystemUserList startSession(String username, String deviceId, SymChannel fromString, String password) {
		return null;
	}

	@Override
	public SymResponse endSession(Long sessionId, String deviceId, String authToken) {
		return null;
	}
}
