package net.symbiosis.common.contract;

import net.symbiosis.common.contract.base.DataContract;
import net.symbiosis.common.contract.symbiosis.SymNotification;
import net.symbiosis.core_lib.enumeration.SymResponseCode;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

/***************************************************************************
 * Created:     11 / 06 / 2016                                             *
 * Platform:    Mint Linux x86_64                                          *
 * Author:      Tsungai Kaviya                                             *
 ***************************************************************************/

@XmlRootElement
@XmlSeeAlso({
    DataContract.class,
    SymNotification.class
})
public class SymList<T> extends DataContract<SymList<T>> {

    protected ArrayList<T> responseData;

    public SymList() {
    }

    public SymList(SymResponseCode symResponseCode) {
        this.symResponse = new SymResponse(symResponseCode);
    }

    public SymList(SymResponseCode symResponseCode, ArrayList<T> responseData) {
        this.symResponse = new SymResponse(symResponseCode);
        this.responseData = responseData;
    }

    public ArrayList<T> getResponseData() {
        return responseData;
    }

    public void setResponseData(ArrayList<T> responseData) {
        this.responseData = responseData;
    }
}
