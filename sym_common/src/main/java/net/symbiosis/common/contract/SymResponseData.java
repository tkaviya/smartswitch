package net.symbiosis.common.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.contract.base.DataContract;
import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.core_lib.enumeration.SymResponseCode;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/***************************************************************************
 *                                                                         *
 * Created:     13 / 07 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@XmlRootElement
@XmlSeeAlso({
	DataContract.class,
	SymNotification.class
})
public class SymResponseData<T> extends DataContract<SymList<T>> {
	protected T responseData;

	public SymResponseData(SymResponseCode symResponseCode) {
		super(symResponseCode);
	}

	public SymResponseData(SymResponseCode symResponseCode, T responseData) {
		super(symResponseCode);
		this.responseData = responseData;
	}
}
