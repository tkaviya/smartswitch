package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_country;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;

/***************************************************************************
 * Created:     19 / 02 / 2017                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "company_id"))
@Cacheable(false)
public class sym_company extends sym_entity<sym_company> {
    @Column(nullable = false, unique = true)
    private String company_name;
    @Column
    private String address_line_1;
    @Column
    private String address_line_2;
    @Column
    private String address_city;
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_country_id")
    private sym_country address_country;
    @Column
    private String phone1;
    @Column
    private String phone2;
    @Column(unique = true)
    private String vat_number;
    @Column(unique = true)
    private String registration_number;

    @Override
    public String toString() {
        return company_name;
    }
}