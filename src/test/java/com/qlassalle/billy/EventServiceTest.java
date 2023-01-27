package com.qlassalle.billy;

import com.qlassalle.billy.domain.EventService;
import org.junit.jupiter.api.Test;

import static com.qlassalle.billy.Fixtures.buildFullEvent;
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
}
