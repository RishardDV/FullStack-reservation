import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'admin',
    pathMatch: 'full',
  },
  {
    path: 'admin',
    loadComponent: () =>
      import('./reservations-list/reservations-list').then(
        (m) => m.ReservationsList,
      ),
  },
  {
    path: 'principal',
    loadComponent: () =>
      import('./usuario_interfaz/interfaz').then(
        (m) => m.UsuarioInterfaz,
      ),
  },
  {
    path: 'nueva_reserva',
    loadComponent: () =>
      import('./reservation-form/reservation-form').then(
        (m) => m.ReservationForm,
      ),
  },
  
  {
    path: 'galery',
    loadComponent: () =>
      import('./galery_interfaz/galery').then(
        (m) => m.galeryInteraz,
      ),
  },
];
