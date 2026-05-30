package co.ba.richarddv.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ba.richarddv.reservation.entity.ReservationEntity;
import co.ba.richarddv.reservation.entity.ReservationStatus;
import co.ba.richarddv.reservation.exception.ReservationAlreadyCancelledException;
import co.ba.richarddv.reservation.exception.ReservationConflictException;
import co.ba.richarddv.reservation.exception.ReservationNotFoundException;
import co.ba.richarddv.reservation.repository.ReservationRepository;

/**
 * Application service for reservation business operations.
 */
@Service
public class ReservationService {

	private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

	private final ReservationRepository reservationRepository;

	/**
	 * Creates the service with the required repository dependency.
	 *
	 * @param reservationRepository persistence access for reservations
	 */
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	/**
	 * Creates a reservation only when no other active reservation exists for the same date and time.
	 *
	 * @param customerName guest name
	 * @param date         reservation date
	 * @param time         reservation time
	 * @param service      requested service description
	 * @return the persisted reservation
	 * @throws ReservationConflictException if an active reservation already occupies the slot
	 */
	@Transactional
	public ReservationEntity createReservation(
			String customerName,
			LocalDate date,
			LocalTime time,
			String service) {
		if (reservationRepository.existsByDateAndTimeAndStatus(date, time, ReservationStatus.ACTIVO)) {
			log.warn("Reservation conflict for date {} and time {}", date, time);
			throw new ReservationConflictException(date, time);
		}

		var reservation = new ReservationEntity();
		reservation.setCostumerName(customerName);
		reservation.setDate(date);
		reservation.setTime(time);
		reservation.setService(service);
		reservation.setStatus(ReservationStatus.ACTIVO);

		var saved = reservationRepository.save(reservation);
		log.info("Created reservation id {} for date {} at {}", saved.getId(), date, time);
		return saved;
	}

	/**
	 * Cancels an active reservation by its identifier.
	 *
	 * @param id reservation identifier
	 * @throws ReservationNotFoundException         if no reservation exists with the given id
	 * @throws ReservationAlreadyCancelledException if the reservation is already cancelled
	 */
	@Transactional
	public void cancelReservation(Long id) {
		var reservation = reservationRepository.findById(id)
				.orElseThrow(() -> {
					log.warn("Reservation not found for cancellation, id {}", id);
					return new ReservationNotFoundException(id);
				});

		if (reservation.getStatus() == ReservationStatus.CANCELADA) {
			log.warn("Attempt to cancel already cancelled reservation id {}", id);
			throw new ReservationAlreadyCancelledException(id);
		}

		reservation.setStatus(ReservationStatus.CANCELADA);
		reservationRepository.save(reservation);
		log.info("Cancelled reservation id {}", id);
	}

}
