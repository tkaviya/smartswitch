package net.symbiosis.common.persistence.entity.enumeration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@NoArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "country_id"))
public class sym_country extends sym_enum_entity<sym_country> {
    @Column(nullable = false, length = 2)
    private String iso_code_2;
    @Column(nullable = false, length = 3)
    private String iso_code_3;
    @Column(nullable = false, length = 6)
    private String dialing_code;

    public sym_country(String name, Boolean enabled, String iso_code_2, String iso_code_3, String dialing_code) {
        super(name, enabled);
        this.iso_code_2 = iso_code_2;
        this.iso_code_3 = iso_code_3;
        this.dialing_code = dialing_code;
    }
}
