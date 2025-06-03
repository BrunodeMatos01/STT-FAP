import { bootstrapApplication } from '@angular/platform-browser';
import { RegisterComponent } from './app/pages/register/register.component';
import { provideHttpClient } from '@angular/common/http';

bootstrapApplication(RegisterComponent, {
  providers: [provideHttpClient()]
});
  