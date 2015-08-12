package no.swact.action.services;

import no.swact.action.models.InitiationSchedule;
import no.swact.action.repositories.InitiationRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class InitiationServiceImpl implements InitiationService {

    @Inject
    private InitiationRepository initiationRepository;

    @Override
    public List<InitiationSchedule> findAll() {
        return initiationRepository.findAll();
    }

    @Override
    public InitiationSchedule save(final InitiationSchedule initiationSchedule) {
        return initiationRepository.save(initiationSchedule);
    }

    @Override
    public InitiationSchedule findOne(final Long id) {
        return initiationRepository.findOne(id);
    }
}
