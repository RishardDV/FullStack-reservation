package co.ba.richarddv.reservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.ba.richarddv.reservation.dto.CreateReservationRequest;
import co.ba.richarddv.reservation.dto.ReservationResponse;
import co.ba.richarddv.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping //Todo lo de la ruta de arriba
    public ReservationResponse createReservation(@RequestBody CreateReservationRequest request){ //Request body todo lo del cuerpo info
        return reservationService.createReservation(request);
    }
    @DeleteMapping("/{id}")
    public ReservationResponse cancelReservation(@PathVariable("id") long id){
        return reservationService.cancelReservation(id);
    }

    @GetMapping
    public List<ReservationResponse>getAllReservation(){
        return reservationService.getAllReservation();
    }

}
