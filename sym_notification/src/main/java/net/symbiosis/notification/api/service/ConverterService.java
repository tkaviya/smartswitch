package net.symbiosis.notification.api.service;

import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.notification.persistence.log.sym_notification;
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

	SymNotification toDTO(sym_notification notification);
}
