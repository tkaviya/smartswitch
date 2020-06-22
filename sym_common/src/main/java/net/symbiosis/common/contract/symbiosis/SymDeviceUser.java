package net.symbiosis.common.contract.symbiosis;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getDeviceVersionId() {
        return deviceVersionId;
    }

    public void setDeviceVersionId(String deviceVersionId) {
        this.deviceVersionId = deviceVersionId;
    }
}
