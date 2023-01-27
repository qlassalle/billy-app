package com.qlassalle.billy.domain;

import java.util.List;

public record Event(int id, String name, long startDate, long endDate, String location, String address,
                    int totalTicketNumber, int maxTicketsPerUser, String saleStartDate, List<String> lineUp, String mediaUrl) {
}
