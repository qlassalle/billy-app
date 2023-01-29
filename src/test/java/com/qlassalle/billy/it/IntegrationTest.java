package com.qlassalle.billy.it;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 * Dirties context should always be avoided, but as the import takes place during bean initialization and both tests
 * share the same context, Spring will reuse the same context and therefore not reinstantiate the already created
 * beans, therefore not performing the import. Using @DirtiesContext here allows us to trigger the creation of a new
 * context every time, and therefore a new import.
 */
@DirtiesContext
public class IntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "smart_contract_event", "event");
    }
}
