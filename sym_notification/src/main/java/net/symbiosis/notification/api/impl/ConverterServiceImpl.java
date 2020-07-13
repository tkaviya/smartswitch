package net.symbiosis.notification.api.impl;


import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.notification.api.service.ConverterService;
import net.symbiosis.notification.persistence.log.sym_notification;
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

@Service(value = "notificationConverterService")
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
	public SymNotification toDTO(sym_notification sourceData) {
		if (sourceData == null) {
			return null;
		}
		SymNotification symNotification = new SymNotification();
		getModelMapper().map(sourceData, symNotification);
		symNotification.setNotificationId(sourceData.getId());
		symNotification.setNotificationType(sourceData.getNotification_type().getName());
		symNotification.setNotificationStatus(sourceData.getNotification_status().getName());
		return symNotification;
	}
}
