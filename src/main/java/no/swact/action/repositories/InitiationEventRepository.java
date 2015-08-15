package no.swact.action.repositories;

import no.swact.action.models.initiation.InitiationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiationEventRepository extends JpaRepository<InitiationEvent, Long> {
}
