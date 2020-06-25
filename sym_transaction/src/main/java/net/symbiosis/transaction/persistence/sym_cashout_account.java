package net.symbiosis.transaction.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

/***************************************************************************
 * Created:     19 / 02 / 2017                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "cashout_account_id"))
@Cacheable(false)
public class sym_cashout_account extends sym_entity<sym_cashout_account> {
    @Column(nullable = false)
    private Long sym_user_id;
    @Column(nullable = false)
    private Long institution_id;
    @Column(nullable = false)
    private String account_nick_name;
    private String account_name;
    @Column(nullable = false)
    private String account_number;
    private String account_branch_code;
    private String account_phone;
    private String account_email;
    private boolean is_active;

    @Override
    public String toString() {
        return account_nick_name;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}