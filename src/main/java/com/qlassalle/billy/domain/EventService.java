package com.qlassalle.billy.domain;

import com.qlassalle.billy.domain.model.SmartContractEvent;
import com.qlassalle.billy.domain.model.input.UpdateEventRequest;
import com.qlassalle.billy.ports.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findAllFromStartDate(long epochSecond) {
        return eventRepository.findAllFromStartDate(epochSecond);
    }

    public Optional<Event> findById(int id) {
        return eventRepository.findById(id);
    }

    public Event update(UpdateEventRequest updateEventRequest) {
        var eventToUpdate = eventRepository.getById(updateEventRequest.getId());

        var eventSmartContracts = new ArrayList<>(eventToUpdate.smartContractEvents());
        var smartContractToUpdate = eventSmartContracts
                                                 .stream()
                                                 .filter(sc -> sc.getId() == updateEventRequest.getSmartContractData()
                                                                                               .getId())
                                                 .findFirst();

        smartContractToUpdate.ifPresent(eventSmartContracts::remove);

        var updatedSmartContract = new SmartContractEvent(smartContractToUpdate.get().getId(),
                                                          smartContractToUpdate.get().getEventId(),
                                                          updateEventRequest.getSmartContractData().getCollectionName(), smartContractToUpdate.get().getSmartContract());

        var updatedSmartContracts = new ArrayList<>(eventSmartContracts);
        updatedSmartContracts.add(updatedSmartContract);

        var upatedEvent = new Event(eventToUpdate.id(), updateEventRequest.getTitle(), eventToUpdate.startDate(),
                                    eventToUpdate.endDate(), eventToUpdate.location(), eventToUpdate.address(),
                                    eventToUpdate.totalTicketNumber(), eventToUpdate.maxTicketsPerUser(),
                                    eventToUpdate.saleStartDate(), updateEventRequest.getLineUp(),
                                    updateEventRequest.getMediaUrl(), updatedSmartContracts);

        return eventRepository.save(upatedEvent);
    }
}
