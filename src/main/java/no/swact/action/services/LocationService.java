package no.swact.action.services;

import no.swact.action.models.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<Location> findAll();

    Location save(Location location);

    Location findOne(Long id);
}
