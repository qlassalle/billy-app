package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.domain.model.SaleCurrency;
import com.qlassalle.billy.domain.model.SaleParams;
import com.qlassalle.billy.domain.model.SmartContract;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;

public class Fixtures {

    static Event buildFullEvent() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe", "1 Rue Alexandre Avisse 45000 " +
                "Orléans", 500, 5, "01/07/2022", List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos" +
                ".com/mouseparty.png", List.of());
    }

    static Event buildFullEventWithInvalidMediaUrl() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe", "1 Rue Alexandre Avisse 45000 " +
                "Orléans", 500, 5, "01/07/2022", List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos" +
                                 ".com/mouseparty.glb", List.of());
    }

    static Event buildExpectedObjectWithoutOptionalLocation() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis", 10000, 6, "04" +
                "/05/2022", List.of("Artist 1", "Artist 2"), "https://coldplay.com/coldplay_asset.mp4", List.of());
    }

    static Event buildExpectedObjectWithoutOptionalLocationAndLineUp() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis", 10000, 6, "04" +
                "/05/2022", List.of(), "https://coldplay.com/coldplay_asset.mp4", List.of());
    }

    static Event buildFullEventWithSmartContractData() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe", "1 Rue Alexandre Avisse 45000 " +
                "Orléans", 500, 5, "01/07/2022", List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos" +
                                 ".com/mouseparty.png", List.of(buildFirstSmartContractEvent()));
    }

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
        return new SmartContractEvent(1, "Mouse On", smartContract);
    }

    private static SmartContractEvent buildSecondSmartContractEvent() {
        var saleParams = new SaleParams(false, List.of(), 0, 2, 100,
                                        new SaleCurrency(null), 1657404000,
                                        1658181600);
        var smartContract = new SmartContract("KT1BKqxCJH9EPimNm1wo1BEgG9bFRgptJwop",
                                              "KT1Gpf8CPkYBe3bRuTCET6A4NhnosX2BAnp6",
                                              "KT1Itr6TxNwoMJejoqsNP8TEN7J6STgMtJcP", saleParams);
        return new SmartContractEvent(2, "Web 3 Classic Collection", smartContract);
    }


}
