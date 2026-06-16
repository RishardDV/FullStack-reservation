import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [ //Caracteristicas que necesitamos nosotros
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient()  //Permite hacer peticiones HTTP a nuestro backend
  ]
};


