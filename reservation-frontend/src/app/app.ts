import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({ //Decorador que define el componente
  selector: 'app-root', //Nombre del componente que se va a usar en el HTML
  imports: [RouterOutlet], //Importaciones que necesitamos nosotros 
  templateUrl: './app.html', //Template HTML que se va a usar en el componente
  styleUrl: './app.css' //Estilos CSS que se van a usar en el componente
})
export class App {  //esta clase hace referencia al componente
  protected readonly title = signal('reservation-frontend');
}
