package com.qlassalle.billy.ports;

import com.qlassalle.billy.domain.Event;

import java.util.List;

public interface EventRepository {

    void save(Event event);

    List<Event> findAll();

    List<Event> findAllFromStartDate(long epochSecond);
}
