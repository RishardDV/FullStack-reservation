import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import {
  ReservationResponse,
  ReservationStatus,
} from '../model/reservation.model';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-reservations-list',
  imports: [RouterLink],
  templateUrl: './reservations-list.html',
  styleUrl: './reservations-list.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReservationsList {
  private readonly reservationService = inject(ReservationService);

  protected readonly reservations = signal<ReservationResponse[]>([]); //Objetos pendientes de cualquier cambio   
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);
  protected readonly cancellingId = signal<number | null>(null);
  protected readonly ReservationStatus = ReservationStatus;//Evita problemas para modificar
  constructor() {
    this.loadReservations();
  }

  protected loadReservations(): void {
    this.loading.set(true);
    this.error.set(null);

    this.reservationService.getallReservations().subscribe({ //Cualquier cambio de estado se coloca en la consulta de todas las reservas
      next: (reservations) => { // se ejecuta cuando todo salga bien
        this.reservations.set(reservations);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('No se pudieron cargar las reservas.');
        this.loading.set(false);
      },
    });
  }

  protected cancelReservation(id: number): void {
    this.cancellingId.set(id);

    this.reservationService.cancelReservation(id).subscribe({
      next: (updated) => {
        this.reservations.update((list) =>
          list.map((reservation) =>
            reservation.id === updated.id ? updated : reservation,
          ), //Lo que sucede es que cuando llegue la respuesta a la lista de reservas actualiza el elemento de esa lista
        );
        this.cancellingId.set(null);
      },
      error: () => {
        this.error.set('No se pudo cancelar la reserva.');
        this.cancellingId.set(null);
      },
    });
  }

  protected canCancel(reservation: ReservationResponse): boolean {
    return reservation.status === ReservationStatus.ACTIVO;
  }

  protected statusLabel(status: ReservationStatus): string {
    return status === ReservationStatus.ACTIVO ? 'Activa' : 'Cancelada';
  }
}
