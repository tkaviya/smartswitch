package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

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
@AttributeOverride(name = "id", column = @Column(name = "session_id"))
public class sym_session extends sym_entity<sym_session> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_user_id")
    private sym_auth_user auth_user;
    @Column
    private String device_id;
    @Column(nullable = false)
    private String auth_token;
    @Column(nullable = false)
    private Date start_time;
    @Column
    private Date end_time;
}
