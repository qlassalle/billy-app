package com.qlassalle.billy.domain;

import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;
import java.util.Optional;

public record Event(int id, String name, long startDate, long endDate, String location, String address,
                    int totalTicketNumber, int maxTicketsPerUser, String saleStartDate, List<String> lineUp,
                    String mediaUrl, List<SmartContractEvent> smartContractEvents) {

    public Event(int id, String name, long startDate, long endDate, String location, String address,
                 int totalTicketNumber, int maxTicketsPerUser, String saleStartDate, List<String> lineUp,
                 String mediaUrl, List<SmartContractEvent> smartContractEvents) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.address = address;
        this.totalTicketNumber = totalTicketNumber;
        this.maxTicketsPerUser = maxTicketsPerUser;
        this.saleStartDate = saleStartDate;
        this.lineUp = lineUp;
        this.mediaUrl = validateUrl(mediaUrl).orElse(null);
        this.smartContractEvents = smartContractEvents;
    }

    private Optional<String> validateUrl(String mediaUrl) {
        if (mediaUrl == null) {
            return Optional.empty();
        }

        var indexOfExtensionDot = mediaUrl.lastIndexOf(".");
        var extension = mediaUrl.substring(indexOfExtensionDot + 1);
        if (List.of("png", "mp4", "jpeg").contains(extension)) {
            return Optional.of(mediaUrl);
        }

        return Optional.empty();
    }
}
