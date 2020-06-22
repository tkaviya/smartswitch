package net.symbiosis.common.persistence.entity.enumeration;

import lombok.NoArgsConstructor;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/***************************************************************************
 *                                                                         *
 * Created:     12 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "notification_status_id"))
public class sym_notification_status extends sym_enum_entity<sym_notification_status> {
	public sym_notification_status(String name, Boolean enabled) { super(name, enabled);}
}
