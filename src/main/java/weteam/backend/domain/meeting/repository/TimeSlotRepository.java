package weteam.backend.domain.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.domain.meeting.entity.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
