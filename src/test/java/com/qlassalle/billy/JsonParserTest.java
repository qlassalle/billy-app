package com.qlassalle.billy;


import com.qlassalle.billy.domain.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qlassalle.billy.ParserFixtures.buildSmartContractEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonParserTest {

    private final JsonParser jsonParser = new JsonParser();

    @Test
    void shouldParseJsonFile() throws IOException {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("input/json/smart-contracts-data.json")) {
            var json = new String(inputStream.readAllBytes());
            var smartContractData = jsonParser.parse(json);
            assertThat(smartContractData).isEqualTo(buildSmartContractEvent());
        }
    }
}
