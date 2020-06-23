package net.symbiosis.authentication.api.impl;

import net.symbiosis.authentication.api.service.ConverterService;
import net.symbiosis.authentication.persistence.entity.sym_auth_user;
import net.symbiosis.authentication.persistence.entity.sym_session;
import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Service;

import static net.symbiosis.common.persistence.dao.implementation.SymConfigDaoImpl.getConfig;
import static net.symbiosis.core_lib.enumeration.DBConfigVars.CONFIG_DEFAULT_COUNTRY_CODE;
import static net.symbiosis.core_lib.utilities.CommonUtilities.format10DigitPhoneNumber;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service(value = "authConverterService")
public class ConverterServiceImpl implements ConverterService {

	private static ModelMapper modelMapper = null;

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
	public SymSystemUser toDTO(sym_user symUser) {
		if (symUser == null) {
			return null;
		}
		SymSystemUser symSystemUser = new SymSystemUser();
		getModelMapper().map(symUser, symSystemUser);
		symSystemUser.setUserId(symUser.getId());
		symSystemUser.setPhoneNumber(format10DigitPhoneNumber(symUser.getMsisdn(), getConfig(CONFIG_DEFAULT_COUNTRY_CODE)));
		symSystemUser.setWalletId(symUser.getWallet().getId());
		symSystemUser.setCompanyName(symUser.getWallet().getCompany().getCompany_name());
		symSystemUser.setWalletBalance(symUser.getWallet().getCurrent_balance().doubleValue());
		symSystemUser.setCountry(symUser.getCountry().getName());
		symSystemUser.setLanguage(symUser.getLanguage().getName());
		symSystemUser.setUserStatus(symUser.getUser_status().getName());
		return symSystemUser;
	}

	@Override
	public SymSystemUser toDTO(sym_auth_user sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymSystemUser symSystemUser = toDTO(sourceData.getUser());
		symSystemUser.setAuthUserId(sourceData.getId());
		symSystemUser.setGroup(sourceData.getAuth_group().getName());
		return symSystemUser;
	}

	@Override
	public SymSystemUser toDTO(sym_session sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymSystemUser symSystemUser = toDTO(sourceData.getAuth_user());
		symSystemUser.setSessionId(sourceData.getId());
		symSystemUser.setUserId(sourceData.getAuth_user().getUser().getId());
		symSystemUser.setAuthToken(sourceData.getAuth_token());
		return symSystemUser;
	}
}
