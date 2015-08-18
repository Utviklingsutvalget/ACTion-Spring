package no.swact.action.controllers.api.initiation;

import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.models.initiation.InitiationSchedule;
import no.swact.action.services.InitiationEventService;
import no.swact.action.services.InitiationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/api/initiation")
public class InitiationRestController {

    private static final Logger LOG = LoggerFactory.getLogger(InitiationRestController.class);
    @Inject
    private InitiationService initiationService;
    @Inject
    private InitiationEventService initiationEventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, TreeMap<Long, List<InitiationEvent>>> all() {
        Map<String, TreeMap<Long, List<InitiationEvent>>> map = new HashMap<>();

        List<InitiationSchedule> all = initiationService.findAll();
        for (InitiationSchedule initiationSchedule : all) {
            LOG.info("Begninning work on: " + initiationSchedule.getLocation().getName());
            TreeMap<Long, List<InitiationEvent>> keyMap = createMapForSchedule(map, initiationSchedule);

            List<InitiationEvent> events = initiationSchedule.getEvents();
            addEventsToMapCategorized(keyMap, events);
        }
        LOG.info(String.valueOf(map));
        return map;

        /*
        all.stream().forEach(one -> Hibernate.initialize(one.getEvents()));
        return all;
         */
    }

    private TreeMap<Long, List<InitiationEvent>> createMapForSchedule(Map<String, TreeMap<Long, List<InitiationEvent>>> map, InitiationSchedule initiationSchedule) {
        String key = initiationSchedule.getLocation().getName();
        TreeMap<Long, List<InitiationEvent>> keyMap = new TreeMap<>();
        map.put(key, keyMap);
        return keyMap;
    }

    private void addEventsToMapCategorized(TreeMap<Long, List<InitiationEvent>> keyMap, List<InitiationEvent> events) {
        for (InitiationEvent event : events) {
            LOG.info("\tBeginning work on " + event.getTitle());
            ZonedDateTime dateTime = event.getDateTime();
            if(dateTime == null) {
                LOG.info("\tSkipping " + event.getTitle());
                continue;
            }
            long valueKey = dateTime.truncatedTo(ChronoUnit.DAYS).toEpochSecond();
            List<InitiationEvent> initiationEvents = addListIfNotExists(keyMap, valueKey);
            LOG.info("\t\tAdding event " + event.getTitle() + " to " + valueKey);
            initiationEvents.add(event);
        }
    }

    private List<InitiationEvent> addListIfNotExists(TreeMap<Long, List<InitiationEvent>> keyMap, long valueKey) {
        if(!keyMap.containsKey(valueKey)) {
            LOG.info("\t\tAdding map for " + valueKey);
            List<InitiationEvent> list = new ArrayList<>();
            keyMap.put(valueKey, list);
            return list;
        } else {
            return keyMap.get(valueKey);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public InitiationSchedule create(@RequestBody InitiationSchedule initiationSchedule) {
        return initiationService.save(initiationSchedule);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InitiationSchedule get(@PathVariable Long id) {
        return initiationService.findOne(id);
    }

    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    public List<InitiationEvent> getEvents(@PathVariable Long id) {
        LOG.info("Getting events for schedule by id: " + id);
        List<InitiationEvent> allEventsForScheduleId = initiationEventService.findAllEventsForScheduleId(id);
        LOG.info("Got " + allEventsForScheduleId.size() + " events");
        return allEventsForScheduleId;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(RuntimeException e) {
        LOG.error("Error handling request", e);
        return e.getMessage();
    }
}
