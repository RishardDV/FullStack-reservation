package co.ba.richarddv.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a reservation cannot be found by its identifier.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ReservationNotFoundException extends ReservationBusinessException {

	/**
	 * Creates an exception for a missing reservation.
	 *
	 * @param id reservation identifier
	 */
	public ReservationNotFoundException(Long id) {
		super("Reservation not found with id " + id);
	}

}
