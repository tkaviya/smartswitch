package net.symbiosis.transaction.api.service;

import net.symbiosis.common.contract.SymWalletList;
import net.symbiosis.common.contract.SymWalletTransactionList;

/***************************************************************************
 *                                                                         *
 * Created:     01 / 05 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface TransactionRequestProcessor {

	SymWalletList getWallet(Long userId, String channel);

	SymWalletTransactionList getWalletHistory(Long userId, String channel);
}
