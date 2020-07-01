package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.core_lib.interfaces.PrintableStringClass;

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
public class SymSystemUser implements Serializable, PrintableStringClass {

    protected Long sessionId;
    protected Long authUserId;
    protected Long userId;
    protected Long walletId;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String email;
    protected String msisdn;
    protected String phoneNumber;
    protected String companyName;
    protected Double walletBalance;
    protected String group;
    protected String deviceId;
    protected String authToken;
    protected Date lastLoginDate;
    protected String userStatus;
    protected String country;
    protected String language;
    protected Date dateOfBirth;

    public SymSystemUser(Long sessionId, Long authUserId, Long userId, Long walletId, String firstName, String lastName,
                         String username, String email, String msisdn, String phoneNumber, String companyName, Double walletBalance,
                         String group, String deviceId, String authToken, Date lastLoginDate) {
        this.sessionId = sessionId;
        this.authUserId = authUserId;
        this.userId = userId;
        this.walletId = walletId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.msisdn = msisdn;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.walletBalance = walletBalance;
        this.group = group;
        this.deviceId = deviceId;
        this.authToken = authToken;
        this.lastLoginDate = lastLoginDate;
    }

    public SymSystemUser setAuthToken(String authToken) { this.authToken = authToken; return this; }

    @Override
    public String toString() {
        return this.toPrintableString();
    }
}
