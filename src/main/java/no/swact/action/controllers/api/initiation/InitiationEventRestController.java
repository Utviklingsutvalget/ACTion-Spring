package no.swact.action.controllers.api.initiation;

import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.services.InitiationEventService;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<InitiationEvent> all() {
        return initiationEventService.findAll();
    }

    @PreAuthorize("hasRole('INITIATION')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public InitiationEvent save(@RequestBody InitiationEvent initiationEvent) {
        return initiationEventService.save(initiationEvent);
    }

    public InitiationEvent update(@RequestBody InitiationEvent initiationEvent) {
        return initiationEventService.update(initiationEvent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InitiationEvent get(@PathVariable Long id) {
        return initiationEventService.findOne(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(RuntimeException e) {
        LOG.error("Error handling request", e);
        return e.getMessage();
    }
}
