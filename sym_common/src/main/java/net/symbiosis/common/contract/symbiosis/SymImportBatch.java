package net.symbiosis.common.contract.symbiosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class SymImportBatch implements Serializable {

    private String voucherProviderBatchId;
    private Long voucherId;
    private BigDecimal voucherValue;
    private String voucherProvider;
    private String serviceProvider;
    private String voucherType;
    private String filename;
    private Date importDateTime;
    private Long numberOfVouchers;
}
