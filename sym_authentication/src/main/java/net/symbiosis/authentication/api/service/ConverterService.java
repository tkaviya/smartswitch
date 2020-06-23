package net.symbiosis.authentication.api.service;

import net.symbiosis.authentication.persistence.entity.sym_auth_user;
import net.symbiosis.authentication.persistence.entity.sym_session;
import net.symbiosis.authentication.persistence.entity.sym_user;
import net.symbiosis.common.contract.symbiosis.SymSystemUser;
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

	SymSystemUser toDTO(sym_user symUser);

	SymSystemUser toDTO(sym_auth_user symAuthUser);

	SymSystemUser toDTO(sym_session symSession);
}
