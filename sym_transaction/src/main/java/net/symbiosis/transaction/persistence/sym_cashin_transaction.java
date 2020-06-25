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
 * Created:     23 / 04 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "cashin_transaction_id"))
public class sym_cashin_transaction extends sym_entity<sym_cashin_transaction> {
    @Column(nullable = false)
    private Long auth_user_id;
    @Column(nullable = false)
    private Long sym_user_id;
    @Column
    private String request_reference;
    @Column
    private String remote_reference;
    @Column(nullable = false)
    private BigDecimal transaction_amount;
    @Column(nullable = false)
    private BigDecimal previous_balance;
    @Column
    private BigDecimal new_balance;
    @Column(nullable = false)
    private Date transaction_time;
    @ManyToOne
    @JoinColumn(name = "response_code_id")
    private sym_response_code response_code;

    public sym_cashin_transaction(Long auth_user_id, Long sym_user_id, BigDecimal transaction_amount,
                                  BigDecimal previous_balance, Date transaction_time) {
        this.auth_user_id = auth_user_id;
        this.sym_user_id = sym_user_id;
        this.transaction_amount = transaction_amount;
        this.previous_balance = previous_balance;
        this.transaction_time = transaction_time;
    }

    public sym_cashin_transaction setRemote_reference(String user_reference) {
        this.remote_reference = user_reference;
        return this;
    }

    public sym_cashin_transaction setNew_balance(BigDecimal new_balance) {
        this.new_balance = new_balance;
        return this;
    }

    public sym_cashin_transaction setResponse_code(sym_response_code transaction_status) {
        this.response_code = transaction_status;
        return this;
    }

    public sym_cashin_transaction setRequest_reference(String request_reference) {
        this.request_reference = request_reference;
        return this;
    }
}