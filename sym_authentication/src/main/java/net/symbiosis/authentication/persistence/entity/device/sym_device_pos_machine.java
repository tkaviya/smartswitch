package net.symbiosis.authentication.persistence.entity.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.authentication.persistence.entity.sym_auth_user;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/***************************************************************************
 * *
 * Created:     22 / 10 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "pos_machine_id"))
public class sym_device_pos_machine extends sym_device<sym_device_pos_machine> {

    @Column(nullable = false)
    private String branch_name;
    @Column(nullable = false)
    private String machine_name;
    @Column(nullable = false, length = 16)
    private String imei1;
    @Column(length = 16)
    private String imei2;
    @Column(length = 16)
    private String imsi1;
    @Column(length = 16)
    private String imsi2;
    @Column(length = 12)
    private String msisdn1;
    @Column(length = 12)
    private String msisdn2;

    public sym_device_pos_machine(sym_auth_user auth_user, Boolean is_active, Date registration_date,
                                  Date last_auth_date, String branch_name, String machine_name, String imei1,
                                  String imei2, String imsi1, String imsi2, String msisdn1, String msisdn2) {
        super(auth_user, is_active, registration_date, last_auth_date);
        this.branch_name = branch_name;
        this.machine_name = machine_name;
        this.imei1 = imei1;
        this.imei2 = imei2;
        this.imsi1 = imsi1;
        this.imsi2 = imsi2;
        this.msisdn1 = msisdn1;
        this.msisdn2 = msisdn2;
    }
}
