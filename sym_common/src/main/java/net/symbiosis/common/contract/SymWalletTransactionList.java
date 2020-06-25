package net.symbiosis.common.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.common.contract.base.DataContract;
import net.symbiosis.common.contract.symbiosis.SymWalletTransaction;
import net.symbiosis.core_lib.enumeration.SymResponseCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/***************************************************************************
 *                                                                         *
 * Created:     16 / 05 / 2019                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@XmlRootElement
public class SymWalletTransactionList extends DataContract<SymWalletTransactionList> implements Serializable {

    private ArrayList<SymWalletTransaction> walletTransactionData;
    private BigDecimal currentBalance;

    public SymWalletTransactionList(SymResponseCode symResponseCode) {
        this.symResponse = new SymResponse(symResponseCode);
    }

    public SymWalletTransactionList(SymResponseCode symResponseCode,
                                    ArrayList<SymWalletTransaction> walletTransactionData,
                                    BigDecimal currentBalance) {
        this.symResponse = new SymResponse(symResponseCode);
        this.walletTransactionData = walletTransactionData;
        this.currentBalance = currentBalance;
    }
}
