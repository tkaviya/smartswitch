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
@AttributeOverride(name = "id", column = @Column(name = "wallet_transfer_id"))
public class sym_wallet_transfer extends sym_entity<sym_wallet_transfer> {

    @Column(nullable = false)
    private Long auth_user_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "wallet_id")
    private sym_wallet recipient_wallet;
    @Basic(optional = false)
    private BigDecimal transfer_amount;
    @Basic(optional = false)
    private BigDecimal previous_balance;
    @Basic
    private BigDecimal new_balance;
    @Basic(optional = false)
    private Date transaction_time;
    @ManyToOne(optional = false)
    @JoinColumn(name = "response_code_id")
    private sym_response_code response_code;
}