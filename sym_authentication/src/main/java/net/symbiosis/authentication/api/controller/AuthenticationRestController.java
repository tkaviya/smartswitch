package net.symbiosis.authentication.api.controller;

import io.swagger.annotations.ApiOperation;
import net.symbiosis.authentication.api.service.AuthenticationRequestProcessor;
import net.symbiosis.authentication.api.service.AuthenticationRestService;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymSystemUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.common.utilities.WebUtils.getRealParamValue;
import static net.symbiosis.core_lib.enumeration.SymChannel.fromString;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationRestController implements AuthenticationRestService {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private final AuthenticationRequestProcessor authenticationRequestProcessor;

	@Autowired
	public AuthenticationRestController(AuthenticationRequestProcessor authenticationRequestProcessor) {
		this.authenticationRequestProcessor = authenticationRequestProcessor;
	}


	@Override
	@PostMapping("/register/mobile")
	public SymSystemUserList registerMobileUser(@RequestParam("email") String email,
		                                        @RequestParam("msisdn") String msisdn,
		                                        @RequestParam("username") String username,
		                                        @RequestParam(value = "deviceId", required = false) String deviceId,
		                                        @RequestParam("companyName") String companyName,
		                                        @RequestParam("firstName") String firstName,
		                                        @RequestParam("lastName") String lastName,
		                                        @RequestParam("pin") String pin) {
		logger.info(format("Got mobile request to register new user %s %s", firstName, lastName));
		return authenticationRequestProcessor.registerMobileUser( getRealParamValue(email), getRealParamValue(msisdn),
				getRealParamValue(username), getRealParamValue(deviceId), getRealParamValue(companyName),
				getRealParamValue(firstName), getRealParamValue(lastName), getRealParamValue(pin));
	}

	@ApiOperation(value = "Register POS Machine", response = SymSystemUserList.class)
	@Override
	@PostMapping("/register/pos")
	public SymSystemUserList registerPosUser(@RequestParam("branchName") String branchName,
	                                      @RequestParam("machineName") String machineName,
	                                      @RequestParam("imei1") String imei1,
	                                      @RequestParam("imei2") String imei2,
	                                      @RequestParam("imsi1") String imsi1,
	                                      @RequestParam("imsi2") String imsi2,
	                                      @RequestParam("msisdn1") String msisdn1,
	                                      @RequestParam("msisdn2") String msisdn2,
	                                      @RequestParam("username") String username,
	                                      @RequestParam("pin") String pin) {
		return authenticationRequestProcessor.registerPosUser(branchName, machineName,
				imei1, imei2, imsi1, imsi2, msisdn1,msisdn2,username,pin);
	}

	@Override
	@PostMapping("/register/web")
	public SymSystemUserList registerWebUser(@RequestParam("email") String email,
	                                         @RequestParam("msisdn") String msisdn,
	                                         @RequestParam("msisdn2") String msisdn2,
	                                         @RequestParam("username") String username,
	                                         @RequestParam("deviceId") String deviceId,
	                                         @RequestParam("firstName") String firstName,
	                                         @RequestParam("lastName") String lastName,
	                                         @RequestParam("dateOfBirth") String dateOfBirth) {
		logger.info(format("Got request to register new user %s %s", firstName, lastName));
		return authenticationRequestProcessor.registerWebUser(email, msisdn, msisdn2, username,
				deviceId, firstName, lastName, dateOfBirth);
	}

	@Override
	@GetMapping("/search/user")
	public SymSystemUserList searchUser(@RequestParam("email") String email,
	                                    @RequestParam("msisdn") String msisdn,
	                                    @RequestParam("username") String username,
	                                    @RequestParam("firstName") String firstName,
	                                    @RequestParam("lastName") String lastName) {
		logger.info(format("Got request to search for user with details %s %s %s %s %s",
				email, msisdn, username, firstName, lastName));
		return authenticationRequestProcessor.searchUser(email, msisdn, username, firstName, lastName);
	}

	@Override
	@PutMapping("/confirm/user/{userId}")
	public SymResponse confirmRegistration(@PathVariable("userId") Long userId,
										   @RequestParam("authToken") String authToken,
										   @RequestParam("username") String username,
	                                       @RequestParam("deviceId") String deviceId,
	                                       @RequestParam("channel") String channel,
	                                       @RequestParam("password") String password) {
		logger.info(format("Got request to complete registration for user %s", username));
		return authenticationRequestProcessor.confirmRegistration(userId, authToken, deviceId, username, fromString(channel), password);
	}

	@Override
	@PostMapping("/session")
	public SymSystemUserList startSession(@RequestParam("username") String username,
	                                      @RequestParam("deviceId") String deviceId,
	                                      @RequestParam("channel") String channel,
	                                      @RequestParam("password") String password) {
		return authenticationRequestProcessor.startSession(username, deviceId, fromString(channel), password);
	}

	@Override
	@PutMapping("/session/{sessionId}/logout")
	public SymResponse endSession(@PathVariable("sessionId") Long sessionId,
	                              @RequestParam("deviceId") String deviceId,
	                              @RequestParam("authToken") String authToken) {
		return authenticationRequestProcessor.endSession(sessionId, deviceId, authToken);
	}
}
