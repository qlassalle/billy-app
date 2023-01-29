CREATE TABLE IF NOT EXISTS smart_contract_event (
    id INTEGER primary key AUTO_INCREMENT,
    event_id INTEGER,
    collection_name VARCHAR(255) NOT NULL,
    smart_contract TEXT NOT NULL,
    FOREIGN KEY fk_event(event_id) REFERENCES event(id)
);
