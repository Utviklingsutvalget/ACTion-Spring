package no.swact.action.controllers.api.initiation;

import no.swact.action.models.initiation.InitiationSchedule;
import no.swact.action.services.InitiationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/initiation")
public class InitiationRestController {

    private static final Logger LOG = LoggerFactory.getLogger(InitiationRestController.class);
    @Inject
    private InitiationService initiationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<InitiationSchedule> all() {
        return initiationService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public InitiationSchedule create(@RequestBody InitiationSchedule initiationSchedule) {
        return initiationService.save(initiationSchedule);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InitiationSchedule get(@PathVariable Long id) {
        return initiationService.findOne(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(RuntimeException e) {
        LOG.error("Error handling request", e);
        return e.getMessage();
    }
}
