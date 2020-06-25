package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@XmlRootElement
public class SymWalletGroup implements Serializable {

    private Long walletGroupId;
    private String name;
    private Boolean enabled;
    private Double defaultDiscount;
}
