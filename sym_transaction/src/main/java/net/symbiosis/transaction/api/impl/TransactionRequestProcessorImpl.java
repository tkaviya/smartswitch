package net.symbiosis.transaction.api.impl;

import net.symbiosis.common.contract.SymWalletList;
import net.symbiosis.common.contract.SymWalletTransactionList;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import net.symbiosis.common.contract.symbiosis.SymWalletTransaction;
import net.symbiosis.common.persistence.log.sym_request_response_log;
import net.symbiosis.core_lib.structure.Pair;
import net.symbiosis.transaction.api.service.ConverterService;
import net.symbiosis.transaction.api.service.TransactionRequestProcessor;
import net.symbiosis.transaction.persistence.sym_wallet;
import net.symbiosis.transaction.persistence.sym_wallet_transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static net.symbiosis.common.persistence.helper.SymEnumHelper.fromEnum;
import static net.symbiosis.core_lib.enumeration.SymChannel.fromString;
import static net.symbiosis.core_lib.enumeration.SymEventType.WALLET_HISTORY;
import static net.symbiosis.core_lib.enumeration.SymResponseCode.SUCCESS;
import static net.symbiosis.persistence.helper.DaoManager.getEntityManagerRepo;

/***************************************************************************
 *                                                                         *
 * Created:     24 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service
public class TransactionRequestProcessorImpl implements TransactionRequestProcessor {
	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private final ConverterService converterService;

	@Autowired
	public TransactionRequestProcessorImpl(ConverterService converterService) {
		this.converterService = converterService;
	}

	@Override
	public SymWalletList getWallet(SymSystemUser systemUser, String channel) {
		logger.info(format("Getting wallet for user %s from channel %s", systemUser.getUsername(), channel));

		sym_request_response_log requestResponseLog = new sym_request_response_log(
			fromEnum(fromString(channel)), fromEnum(WALLET_HISTORY), systemUser.getUserId(), systemUser.getAuthUserId(),
				format("username=%s|channel=%s", systemUser.getUsername(), channel)
		).save();

		var wallet = getEntityManagerRepo().findWhere(sym_wallet.class,
				singletonList(new Pair<>("sym_user_id", systemUser.getUserId()))).get(0);

		requestResponseLog.setResponse_code(fromEnum(SUCCESS))
				.setOutgoing_response(wallet.toString())
				.setOutgoing_response_time(new Date())
				.save();

		logger.info(format("Returning wallet with Id %s: %s", wallet.getId(), wallet.toString()));
		return new SymWalletList(SUCCESS, converterService.toDTO(wallet));
	}

	@Override
	public SymWalletTransactionList getWalletHistory(SymSystemUser systemUser, String channel) {
		logger.info(format("Getting wallet transactions for user %s from channel %s", systemUser.getUsername(), channel));

		sym_request_response_log requestResponseLog = new sym_request_response_log(
			fromEnum(fromString(channel)), fromEnum(WALLET_HISTORY), systemUser.getUserId(), systemUser.getAuthUserId(),
				format("username=%s|channel=%s", systemUser.getUsername(), channel)
		).save();

		ArrayList<SymWalletTransaction> walletTransactions = new ArrayList<>();
		getEntityManagerRepo().findWhere(sym_wallet_transaction.class, singletonList(
				new Pair<>("sym_wallet.sym_user_id", systemUser.getUserId())
		)).forEach(v -> walletTransactions.add(converterService.toDTO(v)));

		var wallet = getEntityManagerRepo().findWhere(sym_wallet.class,
				singletonList(new Pair<>("sym_user_id", systemUser.getUserId()))).get(0);

		requestResponseLog.setResponse_code(fromEnum(SUCCESS))
				.setOutgoing_response(walletTransactions.toString())
				.setOutgoing_response_time(new Date())
				.save();

		logger.info(format("Returning %s wallet transactions", walletTransactions.size()));
		return new SymWalletTransactionList(SUCCESS, walletTransactions, wallet.getCurrent_balance());

	}
}
