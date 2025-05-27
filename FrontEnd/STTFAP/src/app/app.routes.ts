import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { MenuComponent } from './pages/menu/menu.component';

export const routes: Routes = [
  { path: '', redirectTo: 'pages/login', pathMatch: 'full' },
  { path: 'pages/login', component: LoginComponent, pathMatch: 'full' },
  {path: 'pages/menu', component: MenuComponent}
];
