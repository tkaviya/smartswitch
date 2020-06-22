package net.symbiosis.common.contract.api;

import net.symbiosis.common.contract.SymResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static net.symbiosis.common.configuration.NetworkUtilities.sendEmailAlert;
import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_API_ASYNC_TIMEOUT;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_SMS_API_URL;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.*;
import static net.symbiosis.core_lib.utilities.CommonUtilities.throwableAsString;

/***************************************************************************
 *                                                                         *
 * Created:     16 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public class NotificationAPI {

	private static final Logger logger = Logger.getLogger(NotificationAPI.class.getSimpleName());
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_2)
			.followRedirects(HttpClient.Redirect.ALWAYS)
			.connectTimeout(Duration.ofSeconds(parseInt(getConfig(CONFIG_API_ASYNC_TIMEOUT))))
			.build();

	public static SymResponse sendSMS(Long authUserId, String channel, String msisdn, String message) {

		var requestString = format("authUserId=%s&channel=%s&msisdn=%s&message=%s", authUserId, channel, msisdn, message);
		logger.info("Posting to Send SMS (Notification) API %s : %s");

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(requestString))
					.uri(URI.create(getConfig(CONFIG_SMS_API_URL)))
					.build();

			CompletableFuture<HttpResponse<String>> response =
					httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

			String result = response.thenApply(HttpResponse::body).get(parseInt(getConfig(CONFIG_API_ASYNC_TIMEOUT)), TimeUnit.SECONDS);

			logger.info(format("HTTP Response %s : %s : %s",
				response.get().statusCode(),
				response.get().body(),
				result
			));
			if (response.get().statusCode() >= 200 && response.get().statusCode() < 300) {
				return new SymResponse(SUCCESS).setResponse_message(result);
			} else if (response.get().statusCode() >= 300 && response.get().statusCode() < 400) {
				return new SymResponse(CONNECTION_FAILED).setResponse_message(result);
			} else if (response.get().statusCode() >= 400 && response.get().statusCode() < 500) {
				return new SymResponse(INPUT_INVALID_REQUEST).setResponse_message(result);
			} else if (response.get().statusCode() >= 500 && response.get().statusCode() < 600) {
				return new SymResponse(GENERAL_ERROR).setResponse_message(result);
			} else {
				return new SymResponse(GENERAL_ERROR).setResponse_message(result);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.severe("Failed to Send SMS: " + ex.getMessage());
			sendEmailAlert("Failed to Send SMS", "Failed to Send SMS with parameters "
				+ requestString + "\r\n" + throwableAsString(ex)
			);
			return new SymResponse(GENERAL_ERROR).setResponse_message(ex.getMessage());
		}
	}
}
