package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;

import java.util.List;

public class Fixtures {

    static Event buildFullEvent() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe",
                         "1 Rue Alexandre Avisse 45000 Orléans", 500, 5, "01/07/2022",
                         List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos.com/mouseparty.png");
    }

    static Event buildExpectedObjectWithoutOptionalLocation() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis",
                         10000, 6, "04/05/2022", List.of("Artist 1", "Artist 2"), "https://coldplay.com/coldplay_asset.mp4");
    }

    static Event buildExpectedObjectWithoutOptionalLocationAndLineUp() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis",
                         10000, 6, "04/05/2022", List.of(), "https://coldplay.com/coldplay_asset.mp4");
    }
}
