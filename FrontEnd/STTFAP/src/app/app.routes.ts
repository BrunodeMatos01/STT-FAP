  import { Routes } from '@angular/router';
  import { LoginComponent } from './pages/login/login.component';
  import { MenuComponent } from './pages/menu/menu.component';
  import { RegisterComponent } from './pages/register/register.component';

  export const routes: Routes = [
    { path: '', redirectTo: 'pages/login', pathMatch: 'full' },
    { path: 'pages/login', component: LoginComponent, pathMatch: 'full' },
    {path: 'pages/menu', component: MenuComponent},
    {path: 'pages/register', component: RegisterComponent}
  ];
