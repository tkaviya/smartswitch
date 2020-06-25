package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/***************************************************************************
 * Created:     22 / 01 / 2017                                             *
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
public class SymCashoutAccount implements Serializable {

    private Long cashoutAccountId;
    private Long institutionId;
    private String accountNickName;
    private String accountName;
    private String accountNumber;
    private String accountBranchCode;
    private String accountPhone;
    private String accountEmail;
}