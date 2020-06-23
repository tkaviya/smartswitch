package net.symbiosis.common.utilities.service;

import net.symbiosis.common.contract.base.EnumEntityData;
import net.symbiosis.common.contract.symbiosis.SymCurrency;
import net.symbiosis.common.contract.symbiosis.SymFinancialInstitution;
import net.symbiosis.common.persistence.entity.enumeration.sym_currency;
import net.symbiosis.common.persistence.entity.enumeration.sym_financial_institution;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;
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

	EnumEntityData toDTO(sym_enum_entity enumEntity);

	SymFinancialInstitution toDTO(sym_financial_institution symFinancialInstitution);

	SymCurrency toDTO(sym_currency symCurrency);
}
