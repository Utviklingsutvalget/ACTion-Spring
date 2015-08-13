package no.swact.action.services;

import no.swact.action.models.initiation.InitiationSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InitiationService {

    List<InitiationSchedule> findAll();

    InitiationSchedule save(InitiationSchedule initiationSchedule);

    InitiationSchedule findOne(Long id);
}
