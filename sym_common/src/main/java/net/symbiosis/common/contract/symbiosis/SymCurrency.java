package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/***************************************************************************
 * Created:     30 / 01 / 2018                                             *
 * Platform:    Windows 8.1                                                *
 * Author:      Tsungai Kaviya                                             *
 * Copyright:   T3ra Tech                                                  *
 * Website:     http://www.t3ratech.com                                    *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 ***************************************************************************/

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@XmlRootElement
public class SymCurrency implements Serializable {
    private Long currencyId;
    private String currencyName;
    private String iso4217Code;
    private String iso4217Num;
}
