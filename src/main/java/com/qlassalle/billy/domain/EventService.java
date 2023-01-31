package com.qlassalle.billy.domain;

import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findAllFromStartDate(long epochSecond) {
        return eventRepository.findAllFromStartDate(epochSecond);
    }

    public Optional<Event> findById(int id) {
        return eventRepository.findById(id);
    }
}
