package no.swact.action.repositories;

import no.swact.action.models.InitiationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiationRepository extends JpaRepository<InitiationSchedule, Long> {}
