package no.swact.action.services;

import no.swact.action.models.initiation.InitiationEvent;
import no.swact.action.repositories.InitiationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitiationEventServiceImpl implements InitiationEventService {

    @Autowired
    private InitiationEventRepository initiationEventRepository;

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
        return initiationEventRepository.save(initiationEvent);
    }
}
