package com.qlassalle.billy.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlassalle.billy.domain.model.input.UpdateEventRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventsControllerIT extends IntegrationTest {

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

    @Test
    @Sql("classpath:/sql/insert_events_and_smart_contract_events_in_the_future.sql")
    void shouldRetrieveEventsAfterStartDate() throws IOException {
        String expectedJson = new String(getClass().getClassLoader()
                                                   .getResourceAsStream("output/events_from_sale_start_date.json")
                                                   .readAllBytes());

        var response = given().when()
                              .get("http://localhost:" + port + "/events/from/{epochSecond}", Instant.now()
                                                                                                     .getEpochSecond())
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
    @Sql("classpath:/sql/insert_all_events_and_smart_contract_events.sql")
    void shouldRetrieveEventById() throws IOException {
        String expectedJson = new String(getClass().getClassLoader()
                                                   .getResourceAsStream("output/event_by_id.json")
                                                   .readAllBytes());

        var response = given().when()
                              .get("http://localhost:" + port + "/events/1")
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
    @Sql("classpath:/sql/insert_event.sql")
    void shouldUpdateEvent() throws IOException {
        String expectedJson = new String(getClass().getClassLoader()
                                                   .getResourceAsStream("output/event_updated.json")
                                                   .readAllBytes());

        String updatedJson = new String(getClass().getClassLoader()
                                                  .getResourceAsStream("input/api/update_event.json")
                                                  .readAllBytes());

        var updatedData = new ObjectMapper().readValue(updatedJson, UpdateEventRequest.class);

        var response = given().when()
                              .header("Content-type", "application/json")
                              .body(updatedData)
                              .put("http://localhost:" + port + "/events/1")
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
