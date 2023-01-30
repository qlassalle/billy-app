package com.qlassalle.billy.it;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerIT extends IntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    @Sql("classpath:/sql/insert_event.sql")
    void shouldRetrieveAllEvents() throws IOException {
        String expectedJson = new String(getClass().getClassLoader()
                                                   .getResourceAsStream("output/events.json")
                                                   .readAllBytes());

        var response = given().when()
                              .get("http://localhost:" + port + "/events")
                              .then()
                              .assertThat()
                              .statusCode(200)
                              .contentType(ContentType.JSON)
                              .extract()
                              .body()
                              .asString();

        assertThat(response).isEqualTo(expectedJson);
    }

    @Test
    @Sql("classpath:/sql/insert_event_without_lineup.sql")
    void shouldRetrieveEventsAndKeepNullFields() throws IOException {
        String expectedJson = new String(getClass().getClassLoader()
                                                   .getResourceAsStream("output/events_no_lineup.json")
                                                   .readAllBytes());

        var response = given().when()
                              .get("http://localhost:" + port + "/events")
                              .then()
                              .assertThat()
                              .statusCode(200)
                              .contentType(ContentType.JSON)
                              .extract()
                              .body()
                              .asString();

        assertThat(response).isEqualTo(expectedJson);
    }
}
