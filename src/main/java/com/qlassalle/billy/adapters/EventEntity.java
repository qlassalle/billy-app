package com.qlassalle.billy.adapters;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
