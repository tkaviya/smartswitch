package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.symbiosis.core_lib.interfaces.PrintableStringClass;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

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
public class SymWallet implements Serializable, PrintableStringClass {

    private Long walletId;
    private Long accountAdminUserId;
    private Long walletGroupId;
    private BigDecimal currentBalance = new BigDecimal(0.0);
    private Long systemUserId;

    @Override
    public String toString() {
        return this.toPrintableString();
    }
}
