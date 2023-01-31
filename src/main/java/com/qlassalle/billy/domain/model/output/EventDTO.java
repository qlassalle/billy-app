package com.qlassalle.billy.domain.model.output;

import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.time.Instant;
import java.util.List;

public class EventDTO {

    public int eventId;
    public String title;
    public String startDateTime;
    public String endDateTime;
    public String address;
    public String locationName;
    public int totalTicketsCount;
    public String assetUrl;
    public List<String> lineUp;
    public List<TicketCollection> ticketCollections;

    public static class TicketCollection {
        public String collectionName;
        public String scAddress;
        public String collectionAddress;
        public int pricePerToken;
        public int maxMintPerUser;
        public int saleSize;

        public TicketCollection(SmartContractEvent smartContractEvent) {
            this.collectionName = smartContractEvent.getCollectionName();
            this.scAddress = smartContractEvent.getSmartContract().getCrowdsale();
            this.collectionAddress = smartContractEvent.getSmartContract().getCollection();
            this.pricePerToken = smartContractEvent.getSmartContract().getSaleParams().getPricePerToken();
            this.maxMintPerUser = smartContractEvent.getSmartContract().getSaleParams().getMaxMintPerUser();
            this.saleSize = smartContractEvent.getSmartContract().getSaleParams().getSaleSize();
        }
    }

    public EventDTO(Event event) {
        this.eventId = event.id();
        this.title = event.name();
        this.startDateTime = formatDate(event.startDate());
        this.endDateTime = formatDate(event.endDate());
        this.address = event.address();
        this.locationName = event.location();
        this.totalTicketsCount = event.totalTicketNumber();
        this.assetUrl = event.mediaUrl();
        this.lineUp = event.lineUp().isEmpty() ? null : event.lineUp();
        this.ticketCollections = event.smartContractEvents().stream().map(TicketCollection::new).toList();
    }

    /**
     * Format a date from epoch in seconds to this format 2022-07-10T16:30:00. We do a substring - 1 to remove the Z
     * from instant
     */
    private String formatDate(long epoch) {
        var asString = Instant.ofEpochSecond(epoch).toString();

        return asString.substring(0, asString.length() - 1);
    }
}
