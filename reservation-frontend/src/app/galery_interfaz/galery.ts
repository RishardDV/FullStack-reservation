import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-galery-interfaz',
  imports: [RouterLink],
  templateUrl: './galery.html',
  styleUrl: './style.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class galeryInteraz {}