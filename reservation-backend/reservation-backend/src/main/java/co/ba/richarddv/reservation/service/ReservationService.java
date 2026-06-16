package co.ba.richarddv.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ba.richarddv.reservation.dto.CreateReservationRequest;
import co.ba.richarddv.reservation.dto.ReservationResponse;
import co.ba.richarddv.reservation.entity.ReservationStatus;
import co.ba.richarddv.reservation.exception.ReservationAlreadyCancelledException;
import co.ba.richarddv.reservation.exception.ReservationConflictException;
import co.ba.richarddv.reservation.exception.ReservationNotFoundException;
import co.ba.richarddv.reservation.mapper.ReservationMapper;
import co.ba.richarddv.reservation.repository.ReservationRepository;

/**
 * Application service for reservation business operations.
 */
@Service
public class ReservationService {

	private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

	private final ReservationRepository reservationRepository;
	private final ReservationMapper 	reservationMapper;

	/**
	 * Creates the service with its required collaborators.
	 *
	 * @param reservationRepository persistence gateway for reservations
	 * @param reservationMapper     entity/DTO mapper conexión al to
	 */	
	public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
		this.reservationRepository = reservationRepository;
		this.reservationMapper = reservationMapper;
	}

	/**
	 * Creates a reservation when the requested date and time slot is available.
	 *
	 * @param request validated creation payload
	 * @return the created reservation representation
	 * @throws ReservationConflictException if an active reservation already exists for the slot
	 */
	@Transactional
	public ReservationResponse createReservation(CreateReservationRequest request) {
		if (reservationRepository.existsByDateAndTimeAndStatus( //conecta al repositorio y verifica si existe algún registro para esta fecha, hora y estatus
				request.date(), request.time(), ReservationStatus.ACTIVO)) { //el request que le mandamos
			throw new ReservationConflictException(request.date(), request.time()); //Crea una clase para manejar errores
		}

		var entity = reservationMapper.toEntity(request); //Lo que hace es que el DTO De arriba, el protocolo de envío de datos lo convierte en entidad
		var saved = reservationRepository.save(entity); //GUardame los datos que tengo de esta entidad
		log.info("Created reservation id={} for date={} time={}", saved.getId(), saved.getDate(), saved.getTime());
		return reservationMapper.toResponse(saved); //Coge la entidad guardada, conviertela en respuesta y la reotrna en la función
	}

	/**
	 * Cancels an existing reservation by its identifier.
	 *
	 * @param id reservation identifier
	 * @throws ReservationNotFoundException         if no reservation exists with the given id
	 * @throws ReservationAlreadyCancelledException if the reservation is already cancelled
	 */
	@Transactional
	public ReservationResponse cancelReservation(Long id) {
		var entity = reservationRepository.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException(id)); //busca un id de  una reserva, si no pues excepción

		if (entity.getStatus() == ReservationStatus.CANCELADA) {
			throw new ReservationAlreadyCancelledException(id);
		} //Si esa reserva es cancelada, decir que ya se encuentra cancelada

		entity.setStatus(ReservationStatus.CANCELADA);
		var saved = reservationRepository.save(entity);
		return reservationMapper.toResponse(saved);

	}

	public List<ReservationResponse> getAllReservation(){ //lístame todas las reservas 
		return reservationRepository.findAll().stream() //Las busca en el repo
			.map(reservationMapper::toResponse) //Mapea eso conviertiendolo en una respuesta 
			.toList();
	}

}
