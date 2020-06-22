package net.symbiosis.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_auth_group;
import net.symbiosis.common.persistence.entity.enumeration.sym_channel;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "auth_user_id"))
@Cacheable(false)
public class sym_auth_user extends sym_entity<sym_auth_user> {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sym_user_id")
    private sym_user user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "channel_id")
    private sym_channel channel;
    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_group_id")
    private sym_auth_group auth_group;
    @Basic
    private String device_id;
    @Basic
    private String auth_token;
    @Basic(optional = false)
    private Date registration_date;
    @Basic
    private Date last_auth_date;
    @Basic
    private Date last_login_date;

    public sym_auth_user setUser(sym_user user) {
        this.user = user;
        return this;
    }

    public sym_auth_user setChannel(sym_channel channel) {
        this.channel = channel;
        return this;
    }

    public sym_auth_user setAuth_group(sym_auth_group group) {
        this.auth_group = group;
        return this;
    }

    public sym_auth_user setDevice_id(String device_id) {
        this.device_id = device_id;
        return this;
    }

    public sym_auth_user setAuth_token(String auth_token) {
        this.auth_token = auth_token;
        return this;
    }

    public sym_auth_user setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
        return this;
    }

    public sym_auth_user setLast_auth_date(Date last_auth_date) {
        this.last_auth_date = last_auth_date;
        return this;
    }

    public sym_auth_user setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
        return this;
    }
}
