package com.qlassalle.billy;

import com.qlassalle.billy.domain.Event;
import com.qlassalle.billy.ports.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventRepository implements EventRepository {

    List<Event> events = new ArrayList<>();

    @Override
    public void save(Event event) {
        events.add(event);
    }
}
