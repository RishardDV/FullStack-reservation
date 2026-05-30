package co.ba.richarddv.reservation.exception;

/**
 * Base runtime exception for reservation business rule violations.
 */
public class ReservationBusinessException extends RuntimeException {

	/**
	 * Creates an exception with the given message.
	 *
	 * @param message human-readable description of the violation
	 */
	public ReservationBusinessException(String message) {
		super(message);
	}

}
