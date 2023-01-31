package com.qlassalle.billy.domain;

import com.qlassalle.billy.ports.EventRepository;
import com.qlassalle.billy.ports.SmartContractEventRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImportService {

    private static final String CSV_PATH = "organizers-data.csv";
    private static final String JSON_PATH = "smart-contracts-data.json";

    private final EventRepository eventRepository;
    private final SmartContractEventRepository smartContractEventRepository;
    private final CsvParser csvParser = new CsvParser();
    private final JsonParser jsonParser = new JsonParser();

    public ImportService(EventRepository eventRepository, SmartContractEventRepository smartContractEventRepository) {
        this.eventRepository = eventRepository;
        this.smartContractEventRepository = smartContractEventRepository;
    }

    public void importCsv() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(CSV_PATH)) {
            var csv = new String(inputStream.readAllBytes());
            var parsedLine = csvParser.parse(csv);
            parsedLine.forEach(eventRepository::save);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save events from CSV", e);
        }
    }

    public void importJson() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(JSON_PATH)) {
            var json = new String(inputStream.readAllBytes());
            var parsedLine = jsonParser.parse(json);
            parsedLine.forEach(smartContractEventRepository::save);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save events from JSON", e);
        }
    }
}
