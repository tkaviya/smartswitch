package net.symbiosis.transaction.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.persistence.entity.enumeration.sym_deposit_type;
import net.symbiosis.common.persistence.entity.enumeration.sym_financial_institution;
import net.symbiosis.common.persistence.entity.enumeration.sym_response_code;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
@AttributeOverride(name = "id", column = @Column(name = "incoming_payment_id"))
public class sym_incoming_payment extends sym_entity<sym_incoming_payment> {

	private BigDecimal payment_amount;
	@ManyToOne(optional = false)
	@JoinColumn(name = "deposit_type_id")
	private sym_deposit_type deposit_type;
	@Column(nullable = false)
	private Long sym_user_id;
	@Column(nullable = false)
	private Long wallet_id;
	private String depositor_reference;
	private Date payment_time;
    @Basic(optional = false)
    private Date time_loaded;
	@ManyToOne
	@JoinColumn(name = "institution_id")
	private sym_financial_institution financial_institution;
	private String bank_reference;
	private String bank_statement_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "response_code_id")
    private sym_response_code response_code;

	public sym_incoming_payment setDeposit_type(sym_deposit_type deposit_type) {
		this.deposit_type = deposit_type;
		return this;
	}

	public sym_incoming_payment setTime_loaded(Date time_loaded) {
		this.time_loaded = time_loaded;
		return this;
	}

    public sym_incoming_payment setResponse_code(sym_response_code response_code) {
        this.response_code = response_code;
        return this;
    }
}
