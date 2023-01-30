package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.EventEntity;
import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MySqlEventRepository implements EventRepository {

    private final JpaEventRepository eventRepository;

    public MySqlEventRepository(JpaEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void save(Event event) {
        eventRepository.save(toEntity(event));
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll().stream().map(this::toModel).toList();
    }

    private Event toModel(EventEntity event) {
        var lineUp = Stream.of(event.getLineUp().split(","))
                           .map(String::trim)
                           .filter(artist -> !"".equals(artist))
                           .collect(Collectors.toList());

        return new Event(event.getId(), event.getName(), event.getStartDate(), event.getEndDate(),
                         event.getLocation(), event.getAddress(), event.getTotalTicketNumber(),
                         event.getMaxTicketsPerUser(), event.getSaleStartDate(), lineUp, event.getMediaUrl(),
                         SmartContractEventEntityMapper.map(event.getSmartContractEvents()));
    }

    private EventEntity toEntity(Event event) {
        String lineUp = String.join(",", event.lineUp());
        if ("".equals(lineUp)) {
            lineUp = null;
        }

        return new EventEntity(event.id(), event.name(), event.startDate(), event.endDate(), event.location(),
                               event.address(), event.totalTicketNumber(), event.maxTicketsPerUser(),
                               event.saleStartDate(), lineUp, event.mediaUrl());
    }
}
