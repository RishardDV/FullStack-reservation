import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { CreateReservationRequest, ReservationResponse } from "../model/reservation.model";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
@Injectable({ //inyeccion de dependencias
    providedIn: 'root' //se proporciona en el root del app
}) //Para cuando un componente la utilice

export class ReservationService {
    private readonly baseUrl = `${environment.apiUrl}/api/reservation`;

    private http = inject(HttpClient);

    getallReservations(): Observable<ReservationResponse[]> {
        return this.http.get<ReservationResponse[]>(this.baseUrl);
    }

    createReservation(request: CreateReservationRequest): Observable<ReservationResponse> {
        return this.http.post<ReservationResponse>(this.baseUrl, request);
    }

    cancelReservation(id: number): Observable<ReservationResponse> {
        return this.http.delete<ReservationResponse>(`${this.baseUrl}/${id}`);
    }

}

