package net.symbiosis.common.contract.base;

import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.core_lib.enumeration.SymResponseCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/***************************************************************************
 * Created:     11 / 06 / 2016                                             *
 * Platform:    Mint Linux x86_64                                          *
 * Author:      Tsungai Kaviya                                             *
 ***************************************************************************/

@XmlRootElement
public abstract class DataContract<T extends DataContract> implements Serializable {

    protected SymResponse symResponse;

    public DataContract() {
    }

    public DataContract(SymResponseCode symResponseCode) {
        this.symResponse = new SymResponse(symResponseCode);
    }

    public SymResponse getSymResponse() {
        return symResponse;
    }

    @SuppressWarnings("unchecked")
    public T setSymResponse(SymResponse symResponse) {
        this.symResponse = symResponse;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setResponse(String response) {
        this.symResponse.setResponse_message(response);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSymResponseCode(SymResponseCode symResponseCode) {
        this.setSymResponse(new SymResponse(symResponseCode));
        return (T) this;
    }
}
