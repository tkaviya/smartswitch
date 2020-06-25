package net.symbiosis.transaction.api.service;

import net.symbiosis.common.contract.symbiosis.SymWallet;
import net.symbiosis.common.contract.symbiosis.SymWalletTransaction;
import net.symbiosis.transaction.persistence.sym_wallet;
import net.symbiosis.transaction.persistence.sym_wallet_transaction;
import org.modelmapper.ModelMapper;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

public interface ConverterService {
	ModelMapper getModelMapper();

	SymWallet toDTO(sym_wallet wallet);

	SymWalletTransaction toDTO(sym_wallet_transaction walletTransaction);
}
