package co.ba.richarddv.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Payload to create a new reservation.
 *
 * @param customerName guest full name
 * @param date         reservation date
 * @param time         reservation time
 * @param service      requested service description
 */
public record CreateReservationRequest(
		String customerName,
		LocalDate date,
		LocalTime time,
		String service
	) {
}
