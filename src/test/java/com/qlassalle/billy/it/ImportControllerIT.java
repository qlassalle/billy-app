package com.qlassalle.billy.it;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ImportControllerIT extends IntegrationTest {

    @Autowired
    private DataSource dataSource;

    @LocalServerPort
    private int port;

    @Test
    void shouldImportJsonOnStartup() {
        Table smartContractEventTable = new Table(dataSource, "smart_contract_event");
        Table eventTable = new Table(dataSource, "event");

        given().when()
               .get("http://localhost:" + port + "/import")
               .then()
               .assertThat()
               .statusCode(200);

        assertThat(eventTable).hasNumberOfRows(5);
        assertThat(smartContractEventTable).hasNumberOfRows(8);
    }
}
