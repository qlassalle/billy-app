package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.qlassalle.billy.Fixtures.buildExpectedObjectWithoutOptionalLocation;
import static com.qlassalle.billy.Fixtures.buildExpectedObjectWithoutOptionalLocationAndLineUp;
import static org.assertj.core.api.Assertions.assertThat;

class CsvParserTest {

    private final CsvParser parser = new CsvParser();

    @Test
    void parseCsvAndBuildObject() throws IOException {
        var csv = new String(getClass().getClassLoader()
                                       .getResourceAsStream("input/csv/event-with-all-fields.csv")
                                       .readAllBytes());
        var parsedLine = parser.parse(csv);
        assertThat(parsedLine).isEqualTo(List.of(Fixtures.buildFullEvent()));
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
}
