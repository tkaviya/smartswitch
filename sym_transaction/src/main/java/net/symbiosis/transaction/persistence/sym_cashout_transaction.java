package net.symbiosis.transaction.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/***************************************************************************
 * Created:     19 / 02 / 2017                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "cashout_transaction_id"))
public class sym_cashout_transaction extends sym_entity<sym_cashout_transaction> {
    @Column(nullable = false)
    private Long auth_user_id;
    @Column
    private String user_reference;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cashout_account_id")
    private sym_cashout_account cashout_account;
    @Basic(optional = false)
    private BigDecimal transaction_amount;
    @Basic(optional = false)
    private BigDecimal previous_balance;
    @Basic
    private BigDecimal new_balance;
    @Basic(optional = false)
    private Date transaction_time;
    @ManyToOne
    @JoinColumn(name = "response_code_id")
    private sym_response_code response_code;
}