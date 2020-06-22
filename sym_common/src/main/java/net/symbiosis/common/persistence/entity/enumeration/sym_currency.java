package net.symbiosis.common.persistence.entity.enumeration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/***************************************************************************
 * Created:     22 / 01 / 2017                                             *
 * Platform:    Windows 8.1                                                *
 * Author:      Tsungai Kaviya                                             *
 * Copyright:   T3ra Tech                                                  *
 * Website:     http://www.t3ratech.com                                    *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@Getter @Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "currency_id")),
        @AttributeOverride(name = "name", column = @Column(name = "currency_name", unique = true))
})
public class sym_currency extends sym_enum_entity<sym_currency> {
    @Column(unique = true, nullable = false, length = 3)
    private String iso_4217_code;
    @Column(unique = true, nullable = false, length = 3)
    private String iso_4217_num;

    public sym_currency(String name, Boolean enabled, String iso_4217_code, String iso_4217_num) {
        super(name, enabled);
        this.iso_4217_code = iso_4217_code;
        this.iso_4217_num = iso_4217_num;
    }
}
