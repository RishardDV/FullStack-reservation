package co.ba.richarddv.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Payload to create a new reservation.
 *
 * @param customerName guest full name
 * @param date         reservation date
 * @param time         reservation time
 * @param service      requested service description
 */
public record CreateReservationRequest(
		@NotBlank String customerName,
		@NotNull LocalDate date,
		@NotNull LocalTime time,
		@NotBlank String service) {
}
