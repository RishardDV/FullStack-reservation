package co.ba.richarddv.reservation.exception;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Thrown when attempting to create a reservation for a date and time that is already taken.
 */
public class ReservationConflictException extends ReservationBusinessException {

	/**
	 * Creates an exception for a conflicting reservation slot.
	 *
	 * @param date reservation date
	 * @param time reservation time
	 */
	public ReservationConflictException(LocalDate date, LocalTime time) {
		super("A reservation already exists for date " + date + " and time " + time);
	}

}
