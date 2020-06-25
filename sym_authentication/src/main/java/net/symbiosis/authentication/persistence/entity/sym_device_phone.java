package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/***************************************************************************
 * *
 * Created:     14 / 02 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "phone_id"))
public class sym_device_phone extends sym_device<sym_device_phone> {

    @Column(length = 15)
    private String imei1;
    @Column(length = 15)
    private String imei2;
    @Column(length = 16)
    private String imsi1;
    @Column(length = 16)
    private String imsi2;
    @Column(length = 12)
    private String msisdn1;
    @Column(length = 12)
    private String msisdn2;

    public sym_device_phone(sym_auth_user auth_user, Boolean is_active, Date registration_date, Date last_auth_date,
                            String imei1, String imei2, String imsi1, String imsi2, String msisdn1, String msisdn2) {
        super(auth_user, is_active, registration_date, last_auth_date);
        this.imei1 = imei1;
        this.imei2 = imei2;
        this.imsi1 = imsi1;
        this.imsi2 = imsi2;
        this.msisdn1 = msisdn1;
        this.msisdn2 = msisdn2;
    }

}
