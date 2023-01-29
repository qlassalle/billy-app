package com.qlassalle.billy.it;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;

@SpringBootTest
class SmartContractServiceImportIT extends IntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldImportJsonOnStartup() {
        Table table = new Table(dataSource, "smart_contract_event");

        assertThat(table).hasNumberOfRows(8);
    }
}
