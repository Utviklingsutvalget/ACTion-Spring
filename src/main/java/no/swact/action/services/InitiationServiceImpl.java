package no.swact.action.services;

import no.swact.action.models.Location;
import no.swact.action.models.initiation.InitiationSchedule;
import no.swact.action.repositories.InitiationRepository;
import no.swact.action.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitiationServiceImpl implements InitiationService {

    @Autowired
    private InitiationRepository initiationRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<InitiationSchedule> findAll() {
        return initiationRepository.findAll();
    }

    @Override
    public InitiationSchedule save(final InitiationSchedule initiationSchedule) {
        Location location = initiationSchedule.getLocation();
        Location locationFromDb = locationRepository.findOne(location.getId());
        initiationSchedule.setLocation(locationFromDb);
        return initiationRepository.save(initiationSchedule);
    }

    @Override
    public InitiationSchedule findOne(final Long id) {
        return initiationRepository.findOne(id);
    }
}
