package com.qlassalle.billy.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CsvParser {

    private static final String LINE_UP_SEPARATOR = "-";
    private static final String CSV_FIELD_SEPARATOR = ",";

    public List<Event> parse(String csv) {
        return Arrays.stream(csv.split("\n"))
                     .skip(1)
                     .map(this::buildEvent)
                     .toList();
    }

    private Event buildEvent(String line) {
        var fields = line.split(CSV_FIELD_SEPARATOR);
        var startDate = dateFromString(fields[2]);
        var endDate = dateFromString(fields[3]);
        var lineUp = buildLineUp(fields[9]);
        var optionalLocation = fields[4].isEmpty() ? null : fields[4];

        return new Event(parseInt(fields[0]), fields[1], startDate, endDate, optionalLocation, fields[5], parseInt(fields[6]),
                         parseInt(fields[7]), fields[8], lineUp, fields[10], List.of());
    }

    private static List<String> buildLineUp(String lineUp) {
        if (lineUp.isEmpty()) {
            return List.of();
        }

        return Arrays.stream(lineUp.split(LINE_UP_SEPARATOR)).toList();
    }

    private long dateFromString(String date) {
        var convertibleDate = date.split(" ");
        var parsedDate = convertibleDate[0].split("/");
        var parsedTime = convertibleDate[1].split(":");

        return LocalDateTime.of(parseInt(parsedDate[2]),
                                parseInt(parsedDate[1]),
                                parseInt(parsedDate[0]),
                                parseInt(parsedTime[0]),
                                parseInt(parsedTime[1]))
                            .atZone(ZoneId.of("Europe/Paris"))
                            .toEpochSecond();
    }
}
