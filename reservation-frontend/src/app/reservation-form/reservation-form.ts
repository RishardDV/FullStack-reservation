import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AVAILABLE_SERVICES } from '../constants/services.constants';
import { CreateReservationRequest } from '../model/reservation.model';
import { ReservationService } from '../service/reservation.service';
import { Toast } from '../toast/toast';

@Component({
  selector: 'app-reservation-form',
  imports: [ReactiveFormsModule, RouterLink, Toast],
  templateUrl: './reservation-form.html',
  styleUrl: './reservation-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReservationForm {
  private readonly formBuilder = inject(FormBuilder);
  private readonly reservationService = inject(ReservationService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  protected readonly services = AVAILABLE_SERVICES;
  protected readonly returnPath =
    this.route.snapshot.queryParamMap.get('from') === 'usuario'
      ? '/principal'
      : '/admin';
  protected readonly returnLabel =
    this.returnPath === '/principal' ? 'Volver al inicio' : 'Volver al listado';
  protected readonly submitting = signal(false);
  protected readonly toastMessage = signal<string | null>(null);

  protected readonly form = this.formBuilder.nonNullable.group({
    nombreCliente: ['', [Validators.required, Validators.minLength(2)]],
    fecha: ['', Validators.required],
    hora: ['', Validators.required],
    servicio: ['', Validators.required],
  });

  protected submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { nombreCliente, fecha, hora, servicio } = this.form.getRawValue();
    const request: CreateReservationRequest = {
      customerName: nombreCliente,
      date: fecha,
      time: this.formatTime(hora),
      service: servicio,
    };

    this.submitting.set(true);
    this.toastMessage.set(null);

    this.reservationService.createReservation(request).subscribe({
      next: () => {
        this.submitting.set(false);
        this.form.reset();
        this.router.navigate([this.returnPath]);
      },
      error: (error: HttpErrorResponse) => {
        this.submitting.set(false);
        this.toastMessage.set(this.resolveErrorMessage(error));
      },
    });
  }

  protected dismissToast(): void {
    this.toastMessage.set(null);
  }

  protected fieldError(controlName: keyof typeof this.form.controls): string | null {
    const control = this.form.controls[controlName];

    if (!control.touched || !control.errors) {
      return null;
    }

    if (control.errors['required']) {
      return 'Este campo es obligatorio.';
    }

    if (control.errors['minlength']) {
      return 'Debe tener al menos 2 caracteres.';
    }

    return 'Valor no válido.';
  }

  protected isInvalid(controlName: keyof typeof this.form.controls): boolean {
    const control = this.form.controls[controlName];
    return control.invalid && control.touched;
  }

  private formatTime(time: string): string {
    return time.length === 5 ? `${time}:00` : time;
  }

  private resolveErrorMessage(error: HttpErrorResponse): string {
    const backendMessage =
      typeof error.error === 'object' &&
      error.error !== null &&
      'message' in error.error &&
      typeof error.error.message === 'string'
        ? error.error.message
        : null;

    if (backendMessage) {
      return backendMessage;
    }

    if (error.status === 409) {
      return 'Ya existe una reserva para esa fecha y hora.';
    }

    return 'No se pudo crear la reserva. Inténtalo de nuevo.';
  }
}
