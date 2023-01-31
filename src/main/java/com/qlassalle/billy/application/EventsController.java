package com.qlassalle.billy.application;

import com.qlassalle.billy.domain.EventService;
import com.qlassalle.billy.domain.model.output.EventDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<EventDTO> get(@PathVariable int id) {
        return eventService.findById(id)
                           .map(EventDTO::new);
    }
}
