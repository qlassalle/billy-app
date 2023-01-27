package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvInputTest {

    private final CsvInputParser parser = new CsvInputParser();

    @Test
    void parseCsvAndBuildObject() throws IOException {
        var csv = new String(getClass().getClassLoader()
                                       .getResourceAsStream("input/csv/event-with-all-fields.csv")
                                       .readAllBytes());
        var parsedLine = parser.parse(csv);
        assertThat(parsedLine).isEqualTo(List.of(buildExpectedObjectWithOptional()));
    }

//    @Test
//    void parseCsvAndBuildObjectWithoutOptionalParameters() throws IOException {
//        var csv = new String(getClass().getClassLoader()
//                                       .getResourceAsStream("input/csv/event-without-optional-parameters.csv")
//                                       .readAllBytes());
//        var parsedLine = parser.parse(csv);
//        assertThat(parsedLine).isEqualTo(List.of(buildExpectedObjectWithOptional()));
//    }

    private Event buildExpectedObjectWithOptional() {
        return new Event(1, "Mouse Party" , 1657470600, 1657494000, "L'Astrolabe","1 Rue Alexandre Avisse 45000 Orléans",500,5,"01/07/2022",
                List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos.com/mouseparty.png");
    }

//    private Event buildExpectedObjectWithoutOptional() {
//        return new Event(1, "Mouse Party" , "10/07/2022 18:30", "11/07/2022 01:00", "L'Astrolabe","1 Rue Alexandre Avisse 45000 Orléans",500,5,"01/07/2022",
//                         List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos.com/mouseparty.png");
//    }

    // all fields set
    // no location
    // other optional field?
}
