package com.qlassalle.billy;

import com.qlassalle.billy.domain.model.SaleCurrency;
import com.qlassalle.billy.domain.model.SaleParams;
import com.qlassalle.billy.domain.model.SmartContract;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;

public class ParserFixtures {

    static List<SmartContractEvent> buildSmartContractEvent() {
        return List.of(buildFirstSmartContractEvent(), buildSecondSmartContractEvent());
    }

    private static SmartContractEvent buildFirstSmartContractEvent() {
        var saleParams = new SaleParams(false, List.of(), 4, 5, 500,
                                        new SaleCurrency(null), 1656626400,
                                        1657490400);
        var smartContract = new SmartContract("KT1AKqxCJH9EPimNm1wo1BEgG9bFRgptJwkk",
                                              "KT1Apf8CPkYBe3bRuTCET6A4NhnosX2BAnp9",
                                              "KT1Aer6TxNwoMJejoqsNP8TEN7J6STgMtJcA", saleParams);
        return new SmartContractEvent(0, 1, "Mouse On", smartContract);
    }

    private static SmartContractEvent buildSecondSmartContractEvent() {
        var saleParams = new SaleParams(false, List.of(), 0, 2, 100,
                                        new SaleCurrency(null), 1727775935,
                                        1658181600);
        var smartContract = new SmartContract("KT1BKqxCJH9EPimNm1wo1BEgG9bFRgptJwop",
                                              "KT1Gpf8CPkYBe3bRuTCET6A4NhnosX2BAnp6",
                                              "KT1Itr6TxNwoMJejoqsNP8TEN7J6STgMtJcP", saleParams);
        return new SmartContractEvent(0, 2, "Web 3 Classic Collection", smartContract);
    }
}
