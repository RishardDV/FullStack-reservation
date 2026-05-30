package co.ba.richarddv.reservation.exception;

/**
 * Thrown when attempting to cancel a reservation that is already cancelled.
 */
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
