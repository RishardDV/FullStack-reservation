package co.ba.richarddv.reservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ba.richarddv.reservation.entity.ReservationEntity;
import co.ba.richarddv.reservation.entity.ReservationStatus;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

	boolean existsByDateAndTime(LocalDate date, LocalTime time);

	boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}
 