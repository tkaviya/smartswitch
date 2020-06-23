package net.symbiosis.common.utilities.impl;

import net.symbiosis.common.contract.base.EnumEntityData;
import net.symbiosis.common.contract.symbiosis.SymCurrency;
import net.symbiosis.common.contract.symbiosis.SymFinancialInstitution;
import net.symbiosis.common.persistence.entity.enumeration.sym_currency;
import net.symbiosis.common.persistence.entity.enumeration.sym_financial_institution;
import net.symbiosis.common.utilities.service.ConverterService;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Service;

/***************************************************************************
 *                                                                         *
 * Created:     23 / 06 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Service(value = "commonConverterService")
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
	public EnumEntityData toDTO(sym_enum_entity sourceData) {
		if (sourceData == null) {
			return null;
		}
		EnumEntityData enumEntityData = new EnumEntityData();
		getModelMapper().map(sourceData, enumEntityData);
		return enumEntityData;
	}

	@Override
	public SymFinancialInstitution toDTO(sym_financial_institution sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymFinancialInstitution symFinancialInstitution = new SymFinancialInstitution();
		getModelMapper().map(sourceData, symFinancialInstitution);
		symFinancialInstitution.setInstitutionId(sourceData.getId());
		symFinancialInstitution.setInstitutionName(sourceData.getName());
		symFinancialInstitution.setInstitutionShortName(sourceData.getShort_name());
		symFinancialInstitution.setInstitutionType(sourceData.getInstitution_type().getName());
		return symFinancialInstitution;
	}

	@Override
	public SymCurrency toDTO(sym_currency sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymCurrency symCurrency = new SymCurrency();
		getModelMapper().map(sourceData, symCurrency);
		symCurrency.setCurrencyId(sourceData.getId());
		symCurrency.setCurrencyName(sourceData.getName());
		return symCurrency;
	}
}
