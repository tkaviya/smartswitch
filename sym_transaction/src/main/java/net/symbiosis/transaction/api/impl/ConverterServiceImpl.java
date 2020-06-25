package net.symbiosis.transaction.api.impl;

import net.symbiosis.common.contract.symbiosis.SymWallet;
import net.symbiosis.common.contract.symbiosis.SymWalletTransaction;
import net.symbiosis.transaction.api.service.ConverterService;
import net.symbiosis.transaction.persistence.sym_wallet;
import net.symbiosis.transaction.persistence.sym_wallet_transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Service;

import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.common.utilities.SymTransformer.dateToString;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_DEFAULT_CURRENCY_SYMBOL;
import static net.symbiosis.core_lib.utilities.CommonUtilities.formatBigDecimalToMoney;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service(value = "transactionConverterService")
public class ConverterServiceImpl implements ConverterService {

	private static ModelMapper modelMapper = null;

	@Override
	public ModelMapper getModelMapper() {
		if (modelMapper == null) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STRICT)
					.setFieldMatchingEnabled(true)
					.setFullTypeMatchingRequired(true)
					.setAmbiguityIgnored(true)
					.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
					.setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
					.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
		}
		return modelMapper;
	}

	@Override
	public SymWallet toDTO(sym_wallet sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymWallet symWallet = new SymWallet();
		getModelMapper().map(sourceData, symWallet);
		symWallet.setWalletId(sourceData.getId());
		symWallet.setSystemUserId(sourceData.getSym_user_id());
		symWallet.setWalletGroupId(sourceData.getWallet_group().getId());
		return symWallet;
	}

	@Override
	public SymWalletTransaction toDTO(sym_wallet_transaction sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymWalletTransaction symWalletTransaction = new SymWalletTransaction();
		getModelMapper().map(sourceData, symWalletTransaction);
		symWalletTransaction.setWalletTransactionId(sourceData.getId());
		symWalletTransaction.setWalletId(sourceData.getWallet().getId());
		symWalletTransaction.setEventType(sourceData.getEvent_type().getName());
		symWalletTransaction.setTransactionAmount(
			formatBigDecimalToMoney(sourceData.getTransaction_amount(), getConfig(CONFIG_DEFAULT_CURRENCY_SYMBOL))
		);
		symWalletTransaction.setTransactionTime(dateToString(sourceData.getTransaction_time()));
		return symWalletTransaction;
	}

}
