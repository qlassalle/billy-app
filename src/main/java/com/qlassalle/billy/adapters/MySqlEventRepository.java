package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.EventEntity;
import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Component;

@Component
public class MySqlEventRepository implements EventRepository {

    private final JpaEventRepository eventRepostitory;

    public MySqlEventRepository(JpaEventRepository eventRepostitory) {
        this.eventRepostitory = eventRepostitory;
    }

    @Override
    public void save(Event event) {
        eventRepostitory.save(toEntity(event));
    }

    private EventEntity toEntity(Event event) {
        String lineUp = String.join(",", event.lineUp());
        return new EventEntity(event.id(), event.name(), event.startDate(), event.endDate(), event.location(),
                               event.address(), event.totalTicketNumber(), event.maxTicketsPerUser(),
                               event.saleStartDate(), lineUp, event.mediaUrl());
    }
}
