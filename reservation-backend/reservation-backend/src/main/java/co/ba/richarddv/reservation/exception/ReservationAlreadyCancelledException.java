package co.ba.richarddv.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when attempting to cancel a reservation that is already cancelled.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservationAlreadyCancelledException extends ReservationBusinessException {

	/**
	 * Creates an exception for a reservation that is already cancelled.
	 *
	 * @param id reservation identifier
	 */
	public ReservationAlreadyCancelledException(Long id) {
		super("Reservation with id " + id + " is already cancelled");
	}

}
