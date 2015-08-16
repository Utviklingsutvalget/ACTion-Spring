package no.swact.action.services;

import no.swact.action.models.UploadedImage;
import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.models.initiation.InitiationSchedule;
import no.swact.action.repositories.InitiationEventRepository;
import no.swact.action.repositories.InitiationRepository;
import no.swact.action.repositories.UploadedImageRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitiationEventServiceImpl implements InitiationEventService {

    private static final Logger LOG = LoggerFactory.getLogger(InitiationEventServiceImpl.class);
    @Autowired
    private InitiationEventRepository initiationEventRepository;
    @Autowired
    private InitiationRepository initiationRepository;
    @Autowired
    private UploadedImageRepository imageRepository;

    @Override
    public List<InitiationEvent> findAll() {
        return initiationEventRepository.findAll();
    }

    @Override
    public InitiationEvent findOne(final Long id) {
        InitiationEvent one = initiationEventRepository.findOne(id);
        Hibernate.initialize(one.getSchedules());
        return one;
    }

    @Override
    public InitiationEvent save(final InitiationEvent initiationEvent) {
        attachImage(initiationEvent);
        attachSchedules(initiationEvent);
        List<InitiationSchedule> schedules = initiationEvent.getSchedules();
        InitiationEvent save = initiationEventRepository.save(initiationEvent);
        //reattachSchedules(initiationEvent, schedules);
        LOG.info("Schedules after save: " + save.getSchedules().size());
        return save;
    }

    private void reattachSchedules(InitiationEvent initiationEvent, List<InitiationSchedule> schedules) {
        schedules.forEach(initiationSchedule -> initiationSchedule.getEvents().add(initiationEvent));
        initiationRepository.save(schedules);
    }

    private void attachImage(final InitiationEvent initiationEvent) {
        UploadedImage image = initiationEvent.getImage();
        if (image != null) {
            String id = image.getId();
            LOG.info("Resetting image from id: " + id);
            UploadedImage one = imageRepository.findOne(id);
            LOG.info("Image ID is: " + one.getId());
            initiationEvent.setImage(one);
        }
    }

    private void attachSchedules(final InitiationEvent initiationEvent) {
        LOG.info("Schedules before: " + initiationEvent.getSchedules().size());
        List<Long> collect = initiationEvent.getSchedules()
                .stream()
                .map((initiationSchedule) -> {
                    Long id = initiationSchedule.getId();
                    LOG.info(id.toString());
                    return id;
                })
                .collect(Collectors.toList());
        LOG.info("Schedules IDs: " + collect);
        List<InitiationSchedule> schedulesFromDatabase = initiationRepository.findAll(collect);
        LOG.info("Schedules found: " + schedulesFromDatabase.size());
        initiationEvent.setSchedules(schedulesFromDatabase);
    }

    @Override
    public List<InitiationEvent> findAllEventsForScheduleId(final Long id) {
        return initiationRepository.getOne(id).getEvents();
    }

    @Override
    public InitiationEvent update(final InitiationEvent initiationEvent) {
        return save(initiationEvent);
    }

    @Override
    public void delete(InitiationEvent fromDb) {
        initiationEventRepository.delete(fromDb);
    }
}
