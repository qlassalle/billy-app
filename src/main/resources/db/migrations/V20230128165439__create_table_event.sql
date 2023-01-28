CREATE TABLE IF NOT EXISTS event (
    id INTEGER primary key,
    name VARCHAR(255) NOT NULL,
    start_date INTEGER,
    end_date INTEGER,
    location VARCHAR(255),
    address TEXT NOT NULL,
    total_ticket_number INTEGER NOT NULL,
    max_tickets_per_user INTEGER NOT NULL,
    sale_start_date VARCHAR(20) NOT NULL,
    line_up TEXT,
    media_url TEXT
);