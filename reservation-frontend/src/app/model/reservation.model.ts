export enum ReservationStatus {
  ACTIVO = 'ACTIVO',
  CANCELADA = 'CANCELADA',
}

export interface CreateReservationRequest {
  customerName: string;
  date: string;
  time: string;
  service: string;
}

export interface ReservationResponse {
  id: number;
  customerName: string;
  date: string;
  time: string;
  service: string;
  status: ReservationStatus;
}
