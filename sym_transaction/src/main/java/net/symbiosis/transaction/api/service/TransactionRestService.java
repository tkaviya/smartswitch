package net.symbiosis.transaction.api.service;

import net.symbiosis.common.contract.SymWalletList;
import net.symbiosis.common.contract.SymWalletTransactionList;
import org.springframework.web.bind.annotation.GetMapping;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

public interface TransactionRestService {
	@GetMapping("/hello")
	default String hello() { return "Hello from Transaction Service"; }
	SymWalletList getWallet(Long userId, String channel);
	SymWalletTransactionList getWalletHistory(Long userId, String channel);
}
