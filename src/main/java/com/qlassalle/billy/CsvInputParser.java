package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CsvInputParser {

    public List<Event> parse(String csv) {
        return Arrays.stream(csv.split("\n"))
                     .skip(1)
                     .map(this::buildEvent)
                     .toList();
    }

    private Event buildEvent(String line) {
        var fields = line.split(",");
        var startDate = dateFromString(fields[2]);
        var endDate = dateFromString(fields[3]);
        var lineUp = Arrays.stream(fields[9].split("-")).toList();
        return new Event(parseInt(fields[0]), fields[1], startDate, endDate, fields[4], fields[5], parseInt(fields[6]),
                         parseInt(fields[7]), fields[8], lineUp, fields[10]);
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
