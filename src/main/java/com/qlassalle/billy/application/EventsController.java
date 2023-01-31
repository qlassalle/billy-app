package com.qlassalle.billy.application;

import com.qlassalle.billy.domain.EventService;
import com.qlassalle.billy.domain.model.input.UpdateEventRequest;
import com.qlassalle.billy.domain.model.output.EventDTO;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public EventDTO update(@RequestBody UpdateEventRequest updateEventRequest) {
        return new EventDTO(eventService.update(updateEventRequest));
    }
}
