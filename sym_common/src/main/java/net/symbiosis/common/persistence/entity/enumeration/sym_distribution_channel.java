package net.symbiosis.common.persistence.entity.enumeration;

import lombok.NoArgsConstructor;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/***************************************************************************
 * *
 * Created:     22 / 10 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "distribution_channel_id"))
public class sym_distribution_channel extends sym_enum_entity<sym_distribution_channel> {
    public sym_distribution_channel(String name, Boolean enabled) {
        super(name, enabled);
    }
}
