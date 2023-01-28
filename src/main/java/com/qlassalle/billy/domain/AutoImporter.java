package com.qlassalle.billy.domain;

import com.qlassalle.billy.CsvParser;
import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AutoImporter {

    private static final String CSV_PATH = "input/csv/organizers-data.csv";
    private final EventRepository eventRepository;
    private final CsvParser csvParser = new CsvParser();

    public AutoImporter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        importCsv();
    }

    private void importCsv() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(CSV_PATH)) {
            var csv = new String(inputStream.readAllBytes());
            var parsedLine = csvParser.parse(csv);
            parsedLine.forEach(event -> {
                System.out.println("saving");
                eventRepository.save(event);
            });
        } catch (IOException e) {
            // TODO: 28/01/2023 handle this
            throw new RuntimeException(e);
        }
    }
}
