package com.qlassalle.billy.adapters.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    private int id;
    private String name;
    private long startDate;
    private long endDate;
    private String location;
    private String address;
    private int totalTicketNumber;
    private int maxTicketsPerUser;
    private String saleStartDate;
    private String lineUp;
    private String mediaUrl;
    @OneToMany
    @JoinColumn(name = "event_id")
    private List<SmartContractEventEntity> smartContractEvents;

    public EventEntity() {
    }

    public EventEntity(int id, String name, long startDate, long endDate, String location, String address,
                       int totalTicketNumber, int maxTicketsPerUser, String saleStartDate, String lineUp,
                       String mediaUrl) {
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
        this.mediaUrl = mediaUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalTicketNumber() {
        return totalTicketNumber;
    }

    public int getMaxTicketsPerUser() {
        return maxTicketsPerUser;
    }

    public String getSaleStartDate() {
        return saleStartDate;
    }

    public String getLineUp() {
        return lineUp;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public List<SmartContractEventEntity> getSmartContractEvents() {
        return smartContractEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventEntity that = (EventEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
