package co.ba.richarddv.reservation.entity;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Será referencia 
@Table(name = "reservations") //crea la tabla en la base de datos con el nombre reservations
public class ReservationEntity {
    
    @Id //Es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Codigo autogenerado por parte de la base de datos
    private Long id; //Genera el valor de la clave primaria automáticamente
    
    private String costumerName;
    
    private LocalDate date;

    private LocalTime time;  

    private String service;

    private ReservationStatus status;


    public ReservationEntity() {
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    

    

}
