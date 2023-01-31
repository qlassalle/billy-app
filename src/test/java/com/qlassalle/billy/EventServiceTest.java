package com.qlassalle.billy;

import com.qlassalle.billy.domain.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qlassalle.billy.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {

    private InMemoryEventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepository = new InMemoryEventRepository();
        eventService = new EventService(eventRepository);
    }

    @Test
    void shouldSaveEvent() {
        var event = buildFullEvent();
        eventService.save(event);
        assertThat(eventRepository.events).hasSize(1);
    }

    @Test
    void shouldSetMediaURLToNullWhenInvalid() {
        var event = buildFullEventWithInvalidMediaUrl();
        eventService.save(event);
        var savedEvent = eventRepository.events.get(0);
        assertThat(savedEvent.mediaUrl()).isNull();
    }

    @Test
    void shouldReturnAllEvents() {
        var event = buildFullEventWithSmartContractData();
        eventRepository.events.add(event);

        var events = eventService.findAll();

        assertThat(events).isEqualTo(List.of(event));
    }
}
