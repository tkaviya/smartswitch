package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

/***************************************************************************
 * *
 * Created:     14 / 02 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Cacheable(false)
public abstract class sym_device<E extends sym_device> extends sym_entity<E> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_user_id")
    protected sym_auth_user auth_user;
    @Column(nullable = false, columnDefinition = "bit default true")
    protected Boolean is_active = true;
    @Column(nullable = false)
    protected Date registration_date;
    @Column(nullable = false)
    protected Date last_auth_date;
}
