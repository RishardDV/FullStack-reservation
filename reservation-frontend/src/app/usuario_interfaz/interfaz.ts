import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuario-interfaz',
  imports: [RouterLink],
  templateUrl: './index.html',
  styleUrl: './style.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class UsuarioInterfaz {}