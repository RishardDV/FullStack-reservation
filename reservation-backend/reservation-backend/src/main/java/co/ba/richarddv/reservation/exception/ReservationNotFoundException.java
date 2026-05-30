package co.ba.richarddv.reservation.exception;

/**
 * Thrown when a reservation cannot be found by its identifier.
 */
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
