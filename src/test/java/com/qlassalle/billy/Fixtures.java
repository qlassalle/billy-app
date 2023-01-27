package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;

import java.util.List;

public class Fixtures {

    static Event buildFullEvent() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe",
                         "1 Rue Alexandre Avisse 45000 Orléans", 500, 5, "01/07/2022",
                         List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos.com/mouseparty.png");
    }
}
