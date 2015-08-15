package no.swact.action.controllers.api;

import no.swact.action.models.Location;
import no.swact.action.services.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    @Inject
    private LocationService locationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Location> all() {
        return locationService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Location create(@RequestBody Location location) {
        return locationService.save(location);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Location get(@PathVariable Long id) {
        return locationService.findOne(id);
    }
}
