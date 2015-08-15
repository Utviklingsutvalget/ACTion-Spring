package no.swact.action.services;

import no.swact.action.models.initiation.InitiationEvent;

import java.util.List;

public interface InitiationEventService {

    List<InitiationEvent> findAll();

    InitiationEvent findOne(Long id);

    InitiationEvent save(InitiationEvent initiationEvent);

    List<InitiationEvent> findAllEventsForScheduleId(Long id);

    InitiationEvent update(InitiationEvent initiationEvent);
}
