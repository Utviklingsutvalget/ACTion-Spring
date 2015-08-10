package no.swact.action.services;

import no.swact.action.models.Location;
import no.swact.action.repositories.LocationRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class LocationServiceImpl implements LocationService {

    @Inject
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location save(final Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location findOne(final Long id) {
        return locationRepository.findOne(id);
    }
}
