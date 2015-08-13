package no.swact.action.services;

import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.models.initiation.InitiationSchedule;
import no.swact.action.repositories.InitiationEventRepository;
import no.swact.action.repositories.InitiationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitiationEventServiceImpl implements InitiationEventService {

    @Autowired
    private InitiationEventRepository initiationEventRepository;
    @Autowired
    private InitiationRepository initiationRepository;

    @Override
    public List<InitiationEvent> findAll() {
        return initiationEventRepository.findAll();
    }

    @Override
    public InitiationEvent findOne(final Long id) {
        return initiationEventRepository.findOne(id);
    }

    @Override
    public InitiationEvent save(final InitiationEvent initiationEvent) {
        List<Long> collect = initiationEvent.getSchedules()
                .stream()
                .map(InitiationSchedule::getId)
                .collect(Collectors.toList());
        List<InitiationSchedule> schedulesFromDatabase = initiationRepository.findAll(collect);
        initiationEvent.setSchedules(schedulesFromDatabase);

        return initiationEventRepository.save(initiationEvent);
    }
}
