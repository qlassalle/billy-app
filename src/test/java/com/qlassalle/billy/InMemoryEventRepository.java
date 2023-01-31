package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.ports.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryEventRepository implements EventRepository {

    List<Event> events = new ArrayList<>();

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public List<Event> findAll() {
        return events;
    }

    @Override
    public List<Event> findAllFromStartDate(long epochSecond) {
        return List.of(Fixtures.buildFullEventWithFutureSale());
    }

    @Override
    public Optional<Event> findById(int id) {
        return events.stream()
                     .filter(e -> e.id() == id)
                     .findFirst();
    }
}
