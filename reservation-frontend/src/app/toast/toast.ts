import {
  ChangeDetectionStrategy,
  Component,
  input,
  output,
} from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.html',
  styleUrl: './toast.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  host: {
    role: 'alert',
    'aria-live': 'assertive',
  },
})
export class Toast {
  message = input.required<string>();
  type = input<'error' | 'success'>('error');
  dismiss = output<void>();

  protected close(): void {
    this.dismiss.emit();
  }
}
