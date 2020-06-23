package net.symbiosis.common.persistence.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_enum_entity;

import javax.persistence.*;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "auth_group_role_id"))
@Cacheable(false)
public class sym_auth_group_role extends sym_enum_entity<sym_auth_group_role> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_group_id")
    private sym_auth_group auth_group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private sym_role role;
}
