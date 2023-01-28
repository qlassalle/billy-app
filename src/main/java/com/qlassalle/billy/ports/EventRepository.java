package com.qlassalle.billy.ports;

import com.qlassalle.billy.domain.Event;

public interface EventRepository {

    void save(Event event);
}
