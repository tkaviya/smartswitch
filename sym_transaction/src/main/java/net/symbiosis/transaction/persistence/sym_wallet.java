package net.symbiosis.transaction.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.math.BigDecimal;

/***************************************************************************
 * *
 * Created:     22 / 10 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "wallet_id"))
@Cacheable(false)
public class sym_wallet extends sym_entity<sym_wallet> {
    @Column(nullable = false)
    private Long sym_user_id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_group_id")
    private sym_wallet_group wallet_group;
    @Column(nullable = false, scale = 3)
    private BigDecimal current_balance;

    public sym_wallet setCurrent_balance(BigDecimal current_balance) {
        this.current_balance = current_balance;
        return this;
    }

    public sym_wallet setWallet_admin_user(Long sym_user_id) {
        this.sym_user_id = sym_user_id;
        return this;
    }

    public sym_wallet setWallet_group(sym_wallet_group wallet_group) {
        this.wallet_group = wallet_group;
        return this;
    }
}
