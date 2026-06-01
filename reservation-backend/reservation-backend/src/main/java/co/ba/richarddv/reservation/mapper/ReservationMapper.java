package co.ba.richarddv.reservation.mapper;

import org.springframework.stereotype.Component;

import co.ba.richarddv.reservation.dto.CreateReservationRequest;
import co.ba.richarddv.reservation.dto.ReservationResponse;
import co.ba.richarddv.reservation.entity.ReservationEntity;
import co.ba.richarddv.reservation.entity.ReservationStatus;

/**
 * Maps reservation entities to DTOs and vice versa.
 */
@Component
public class ReservationMapper {

	/**
	 * Builds a new active reservation entity from a creation request.
	 *
	 * @param request validated creation payload
	 * @return a transient entity ready to persist
	 */
	public ReservationEntity toEntity(CreateReservationRequest request) {
		var entity = new ReservationEntity(); //Coge request , cada uno de sus atributos y le asigna el valor 
		entity.setCostumerName(request.customerName());
		entity.setDate(request.date());
		entity.setTime(request.time());
		entity.setService(request.service());
		entity.setStatus(ReservationStatus.ACTIVO);
		return entity;
	}

	/**
	 * Converts a persisted reservation entity into an API response.
	 *
	 * @param entity persisted reservation
	 * @return reservation representation for callers
	 */
	public ReservationResponse toResponse(ReservationEntity entity) {
		return new ReservationResponse(
				entity.getId(),
				entity.getCostumerName(),
				entity.getDate(),
				entity.getTime(),
				entity.getService(),
				entity.getStatus());
	}

}
