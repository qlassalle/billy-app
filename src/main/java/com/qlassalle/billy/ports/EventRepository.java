package com.qlassalle.billy.ports;

import com.qlassalle.billy.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event event);

    List<Event> findAll();

    List<Event> findAllFromStartDate(long epochSecond);

    Optional<Event> findById(int id);

    Event getById(int id);
}
