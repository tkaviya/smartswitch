package net.symbiosis.authentication.provider;

import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.log.sym_request_response_log;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.util.Collections.singletonList;

/***************************************************************************
 *                                                                         *
 * Created:     17 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public class OpenIDAuthenticationProvider extends SymChainAuthenticationProvider {

	private static final Logger logger = Logger.getLogger(OpenIDAuthenticationProvider.class.getSimpleName());
	private static sym_channel CHANNEL;

	public OpenIDAuthenticationProvider(sym_request_response_log requestResponseLog, sym_channel channel,
	                                    String accessToken, String deviceId) {
		super(requestResponseLog, channel);
		setAuthToken(accessToken);
		setDeviceId(deviceId);
		CHANNEL = channel;
	}

//	public SymResponseObject<sym_auth_user> registerUser(sym_channel channel, String deviceId, sym_auth_group authGroup, sym_company company) {
//
//		logger.info(format("Performing %s registration for deviceId %s using OpenID", channel.getName(), deviceId));
//
//		String state = new BigInteger(130, new SecureRandom()).toString(32);
//
//		String url = format("%s?" +
//				"response_type=code&" +
//				"client_id=%s.apps.googleusercontent.com&" +
//				"scope=openid%20email&" +
//				"redirect_uri=%s&" +
//				"state=security_token%3%s/login&" +
//				"login_hint=jsmith@example.com&" +
//				"nonce=0394852-3190485-2490358&" +
//				"hd=example.com",
//				getConfig(CONFIG_GOOGLE_OPENID_BASE_URL),
//				getConfig(CONFIG_GOOGLE_OPENID_CLIENT_ID),
//				getConfig(CONFIG_GOOGLE_OPENID_REDIRECT_URL), state,
//				getConfig(CONFIG_GOOGLE_OPENID_REDIRECT_URL)
//		);
//	}

	@Override
	protected void initializeAuthenticationChain() {
		authenticationChain.put(CHANNEL, new ArrayList<>(singletonList(this::startSession)));
	}
}
