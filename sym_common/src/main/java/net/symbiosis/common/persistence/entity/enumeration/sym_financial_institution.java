package net.symbiosis.common.persistence.entity.enumeration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @AttributeOverride(name = "id", column = @Column(name = "institution_id")),
    @AttributeOverride(name = "name", column = @Column(name = "institution_name", unique = true))
})
@Cacheable(false)
public class sym_financial_institution extends sym_enum_entity<sym_financial_institution> {
    @Column(unique = true)
    private String short_name;
    @Column(unique = true)
    private String integration_id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_type_id")
    private sym_financial_institution_type institution_type;
    @Column(unique = true)
    private String swift_code;
    @Column
    private String physical_address;
    @Column
    private String contact_phone;
    @Column
    private String contact_fax;
    @Column
    private String contact_email;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private sym_country country;
    @Column(nullable = false, scale = 3)
    private BigDecimal current_balance;
    @Column
    private String service_id;
    @Column
    private String product_id;
}
