package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/***************************************************************************
 * *
 * Created:     21 / 10 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@XmlRootElement
public class SymDeviceUser implements Serializable {
    protected String company;
    protected Long walletId;
    protected String group;
    protected String authToken;
    protected Date lastLoginDate;
    protected String deviceVersionId;

    public SymDeviceUser(String group, String authToken, Date lastLoginDate, String deviceVersionId) {
        this.group = group;
        this.authToken = authToken;
        this.lastLoginDate = lastLoginDate;
        this.deviceVersionId = deviceVersionId;
    }
}
