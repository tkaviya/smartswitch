package net.symbiosis.transaction.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_event_type;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/***************************************************************************
 *                                                                         *
 * Created:     29 / 04 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "wallet_transaction_id"))
public class sym_wallet_transaction extends sym_entity<sym_wallet_transaction> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "wallet_id")
    private sym_wallet wallet;
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_type_id")
    private sym_event_type event_type;
    @Column(nullable = false)
    private BigDecimal transaction_amount;
    @Column(nullable = false)
    private String transaction_description;
    @Column(nullable = false)
    private Long transaction_link_reference;
    @Column(nullable = false)
    private Date transaction_time;
}
