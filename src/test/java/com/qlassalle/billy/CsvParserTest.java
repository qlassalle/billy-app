package com.qlassalle.billy;

import com.qlassalle.billy.domain.CsvParser;
import com.qlassalle.billy.domain.Event;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static com.qlassalle.billy.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class CsvParserTest {

    private final CsvParser parser = new CsvParser();

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("buildTestCases")
    void parseCsvAndBuildObject(String testName, String path, Event event) throws IOException {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            var csv = new String(inputStream.readAllBytes());
            var parsedLine = parser.parse(csv);
            assertThat(parsedLine).isEqualTo(List.of(event));
        }
    }

    private static Stream<Arguments> buildTestCases() {
        return Stream.of(
                Arguments.of("Line with all fields", "input/csv/event-with-all-fields.csv", buildFullEvent()),
                Arguments.of("No location", "input/csv/event-without-optional-location.csv", buildExpectedObjectWithoutOptionalLocation()),
                Arguments.of("No line up", "input/csv/event-without-optional-location-and-line-up.csv", buildExpectedObjectWithoutOptionalLocationAndLineUp())
        );
    }
}
