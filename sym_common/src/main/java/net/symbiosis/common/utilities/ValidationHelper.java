package net.symbiosis.common.utilities;

import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.common.persistence.entity.enumeration.sym_deposit_type;
import net.symbiosis.core_lib.response.SymResponseObject;

import java.math.BigDecimal;

import static java.lang.String.format;
import static net.symbiosis.common.utilities.SymValidator.isValidPlainText;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.*;
import static net.symbiosis.persistence.dao.EnumEntityRepoManager.findByName;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/***************************************************************************
 * *
 * Created:     14 / 01 / 2017                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

public class ValidationHelper {

    public static SymResponseObject<sym_channel> validateChannel(String channel) {

        if (channel == null) {
            return new SymResponseObject<sym_channel>(INPUT_INVALID_REQUEST).setMessage("channel cannot be null");
        }
        else if (!isValidPlainText(channel)) {
            return new SymResponseObject<sym_channel>(INPUT_INVALID_REQUEST).setMessage("channel name is not valid");
        }

        sym_channel symChannel = findByName(sym_channel.class, channel);
        if (symChannel == null) {
            return new SymResponseObject<sym_channel>(DATA_NOT_FOUND).setMessage(format("Channel %s was not found", channel));
        }
        return new SymResponseObject<>(SUCCESS, symChannel);
    }

    public static SymResponseObject<sym_deposit_type> validateDepositType(Long depositTypeId) {
        if (depositTypeId == null) {
            return new SymResponseObject<sym_deposit_type>(INPUT_INCOMPLETE_REQUEST)
                    .setMessage("depositTypeId cannot be null");
        }
        sym_deposit_type depositType = getEntityManagerRepo().findById(sym_deposit_type.class, depositTypeId);
        if (depositType == null) {
            return new SymResponseObject<sym_deposit_type>(INPUT_INVALID_REQUEST)
                    .setMessage(format("Deposit type with id %d was not found", depositTypeId));
        }

        return new SymResponseObject<>(SUCCESS, depositType);
    }

    public static SymResponseObject<BigDecimal> validateAmount(BigDecimal amount) {

        if (amount == null) {
            return new SymResponseObject<BigDecimal>(INPUT_INCOMPLETE_REQUEST).setMessage("amount cannot be null");
        }

        if (amount.compareTo(new BigDecimal(0.0)) < 1) {
            return new SymResponseObject<BigDecimal>(INPUT_INVALID_AMOUNT).setMessage("Amount must be greater than 0");
        }

        return new SymResponseObject<>(SUCCESS, amount);
    }
}
