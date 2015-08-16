package no.swact.action.controllers.api.initiation;

import no.swact.action.models.exceptions.ResourceNotFoundException;
import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.services.InitiationEventService;
import no.swact.action.services.InitiationService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/initiation/events")
public class InitiationEventRestController {

    private static final Logger LOG = LoggerFactory.getLogger(InitiationEventRestController.class);
    @Autowired
    private InitiationEventService initiationEventService;
    @Autowired
    private InitiationService initiationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<InitiationEvent> all() {
        return initiationEventService.findAll();
    }

    @PreAuthorize("hasRole('INITIATION')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public InitiationEvent save(@RequestBody InitiationEvent initiationEvent) {
        if(initiationEvent.getId() != null) {
            throw new RuntimeException("Received initiation event id");
        }
        return initiationEventService.save(initiationEvent);
    }

    @PreAuthorize("hasRole('INITIATION')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public InitiationEvent update(@PathVariable Long id, @RequestBody InitiationEvent initiationEvent) {
        InitiationEvent fromDb = initiationEventService.findOne(id);
        if(fromDb == null) {
            throw new ResourceNotFoundException("Ingen event funnet");
        }
        return initiationEventService.save(initiationEvent);
    }

    @PreAuthorize("hasRole('INITIATION')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        InitiationEvent fromDb = initiationEventService.findOne(id);
        fromDb.getSchedules().clear();
        initiationEventService.delete(fromDb);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InitiationEvent get(@PathVariable Long id) {
        InitiationEvent one = initiationEventService.findOne(id);
        Hibernate.initialize(one.getSchedules());
        return one;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Exception handleBadRequest(RuntimeException e) {
        LOG.error("Error handling request", e);
        return e;
    }
}
