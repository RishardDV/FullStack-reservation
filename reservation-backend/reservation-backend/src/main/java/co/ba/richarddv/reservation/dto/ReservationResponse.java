package co.ba.richarddv.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import co.ba.richarddv.reservation.entity.ReservationStatus;

/**
 * Representation of a reservation returned by the API and service layer.
 *
 * @param id           reservation identifier
 * @param customerName guest full name
 * @param date         reservation date
 * @param time         reservation time
 * @param service      requested service description
 * @param status       current reservation status
 */
public record ReservationResponse(
		Long id,
		String customerName,
		LocalDate date,
		LocalTime time,
		String service,
		ReservationStatus status) {
}
