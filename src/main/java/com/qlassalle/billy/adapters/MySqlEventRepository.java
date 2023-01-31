package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.EventEntity;
import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * This method is awful and this logic should be performed in a SQL query
     */
    @Override
    public List<Event> findAllFromStartDate(long epochSecond) {
        return eventRepository.findAll()
                              .stream()
                              .filter(e -> e.getSmartContractEvents()
                                            .stream()
                                            .filter(sc -> sc.getSmartContract()
                                                            .getSaleParams()
                                                            .getStartTime() >= epochSecond)
                                            .toList()
                                            .size() > 0)
                              .map(this::toModel)
                              .toList();
    }

    private Event toModel(EventEntity event) {
        var lineUp = Optional.ofNullable(event.getLineUp())
                             .map(artists -> Arrays.stream(artists.split(","))
                                                   .map(String::trim)
                                                   .filter(artist -> !artist.isEmpty())
                                                   .collect(Collectors.toList()))
                             .orElse(Collections.emptyList());

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
