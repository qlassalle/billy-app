package com.qlassalle.billy.application;

import com.qlassalle.billy.domain.EventService;
import com.qlassalle.billy.domain.model.output.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventService eventService;

    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getEvents() {
        var events = eventService.findAll();

        return events.stream()
                     .map(EventDTO::new)
                     .toList();
    }

    @GetMapping("/from/{epochSecond}")
    public List<EventDTO> getFromSaleStartDate(@PathVariable long epochSecond) {
        return eventService.findAllFromStartDate(epochSecond)
                           .stream()
                           .map(EventDTO::new)
                           .toList();
    }
}
