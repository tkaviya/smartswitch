package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.core_lib.interfaces.PrintableStringClass;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/***************************************************************************
 * *
 * Created:     22 / 10 / 2016                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@XmlRootElement
public class SymWalletTransaction implements Serializable, PrintableStringClass {
    private Long walletTransactionId;
    private Long walletId;
    private String eventType;
    private String transactionAmount;
    private String transactionDescription;
    private String transactionTime;

    @Override
    public String toString() {
        return this.toPrintableString();
    }
}
