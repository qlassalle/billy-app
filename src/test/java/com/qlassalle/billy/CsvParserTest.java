package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvParserTest {

    private final CsvParser parser = new CsvParser();

    @Test
    void parseCsvAndBuildObject() throws IOException {
        var csv = new String(getClass().getClassLoader()
                                       .getResourceAsStream("input/csv/event-with-all-fields.csv")
                                       .readAllBytes());
        var parsedLine = parser.parse(csv);
        assertThat(parsedLine.get(0)).isEqualTo(List.of(buildExpectedObjectWithOptional()).get(0));
    }

    @Test
    void parseCsvAndBuildObjectWithoutOptionalParameters() throws IOException {
        var csv = new String(getClass().getClassLoader()
                                       .getResourceAsStream("input/csv/event-without-optional-location.csv")
                                       .readAllBytes());
        var parsedLine = parser.parse(csv);
        assertThat(parsedLine).isEqualTo(List.of(buildExpectedObjectWithoutOptionalLocation()));
    }

    @Test
    void parseCsvAndBuildObjectWithoutOptionalLocationAndLineUp() throws IOException {
        var csv = new String(getClass().getClassLoader()
                                       .getResourceAsStream("input/csv/event-without-optional-location-and-line-up.csv")
                                       .readAllBytes());
        var parsedLine = parser.parse(csv);
        assertThat(parsedLine).isEqualTo(List.of(buildExpectedObjectWithoutOptionalLocationAndLineUp()));
    }

    private Event buildExpectedObjectWithOptional() {
        return new Event(1, "Mouse Party", 1657470600, 1657494000, "L'Astrolabe",
                         "1 Rue Alexandre Avisse 45000 Orléans", 500, 5, "01/07/2022",
                         List.of("Mehdi Maïzi", "Rad Cartier", "Squidji"), "https://photos.com/mouseparty.png");
    }

    private Event buildExpectedObjectWithoutOptionalLocation() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis",
                         10000, 6, "04/05/2022", List.of("Artist 1", "Artist 2"), "https://coldplay.com/coldplay_asset.mp4");
    }

    private Event buildExpectedObjectWithoutOptionalLocationAndLineUp() {
        return new Event(4, "Coldplay World Tour", 1659119400, 1659135600, null, "93200 Saint-Denis",
                         10000, 6, "04/05/2022", List.of(), "https://coldplay.com/coldplay_asset.mp4");
    }
}
