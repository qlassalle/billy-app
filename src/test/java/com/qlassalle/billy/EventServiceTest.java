package com.qlassalle.billy;

import com.qlassalle.billy.domain.EventService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qlassalle.billy.Fixtures.buildFullEvent;
import static com.qlassalle.billy.Fixtures.buildFullEventWithSmartContractData;
import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {

    private final InMemoryEventRepository eventRepository = new InMemoryEventRepository();
    private final EventService eventService = new EventService(eventRepository);

    @Test
    void shouldSaveEvent() {
        var event = buildFullEvent();
        eventService.save(event);
        assertThat(eventRepository.events).hasSize(1);
    }

    @Test
    void shouldReturnAllEvents() {
        var event = buildFullEventWithSmartContractData();
        eventRepository.events.add(event);

        var events = eventService.findAll();

        assertThat(events).isEqualTo(List.of(event));
    }
}
