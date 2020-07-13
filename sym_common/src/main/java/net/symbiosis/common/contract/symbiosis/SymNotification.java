package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/***************************************************************************
 *                                                                         *
 * Created:     09 / 07 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
public class SymNotification implements Serializable {
	private Long notificationId;
	private Long symUserId;
	private Long authUserId;
	private String recipient;
	private String notificationType;
	private String remoteReference;
	private String notificationStatus;
	private Long notificationTime;
	private String notification;
	private String submitResponse;
}
