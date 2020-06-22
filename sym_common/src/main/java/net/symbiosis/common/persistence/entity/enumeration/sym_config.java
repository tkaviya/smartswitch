package net.symbiosis.common.persistence.entity.enumeration;

import lombok.NoArgsConstructor;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.*;

/***************************************************************************
 *                                                                         *
 * Created:     26 / 04 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "config_id")),
    @AttributeOverride(name = "name", column = @Column(name = "config_name", unique = true))
})
@Cacheable(false)
public class sym_config extends sym_enum_entity<sym_config> {
    @Column(nullable = false)
    private String config_description;
    @Column(nullable = false)
    private String config_value;

    public sym_config(String name, String config_description, String config_value) {
        this.name = name;
        this.config_description = config_description;
        this.config_value = config_value;
    }

    public String getConfig_description() { return config_description; }

    public void setConfig_description(String config_description) { this.config_description = config_description; }

    public String getConfig_value() { return config_value; }

    public void setConfig_value(String config_value) { this.config_value = config_value; }
}
