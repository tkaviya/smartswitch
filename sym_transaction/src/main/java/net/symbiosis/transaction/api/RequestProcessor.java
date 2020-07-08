package net.symbiosis.transaction.api;

import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.enumeration.SymResponseCode;

import java.util.Date;

import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;

/***************************************************************************
 *                                                                         *
 * Created:     11 / 05 / 2018                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface RequestProcessor {

    default void logResponse(Long authUserId, Long symUserId, sym_request_response_log requestResponseLog, sym_response_code responseCode, String responseMessage) {
        if (authUserId != null) { requestResponseLog.setAuth_user_id(authUserId); }
        if (symUserId != null) { requestResponseLog.setSystem_user_id(symUserId); }
        requestResponseLog.setOutgoing_response(responseMessage);
        requestResponseLog.setOutgoing_response_time(new Date());
        requestResponseLog.setResponse_code(responseCode);
        requestResponseLog.save();
    }

    default void logResponse(Long authUserId, Long symUserId, sym_request_response_log requestResponseLog, sym_response_code responseCode) {
        logResponse(authUserId, symUserId, requestResponseLog, responseCode, responseCode.getResponse_message());
    }

    default void logResponse(Long authUserId, Long symUserId, sym_request_response_log requestResponseLog, SymResponseCode responseCode, String responseMessage) {
        logResponse(authUserId, symUserId, requestResponseLog, fromEnum(responseCode), responseMessage);
    }

    default void logResponse(Long authUserId, Long symUserId, sym_request_response_log requestResponseLog, SymResponseCode responseCode) {
        logResponse(authUserId, symUserId, requestResponseLog, fromEnum(responseCode));
    }
}
