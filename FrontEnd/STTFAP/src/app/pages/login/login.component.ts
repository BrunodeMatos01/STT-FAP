import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  senha: string = '';

  constructor(private http: HttpClient) {}

  onSubmit() {
    const dadosLogin = {
      email: this.email,
      senha: this.senha
    };

    this.http.post('http://localhost:3000/api/login', dadosLogin)
      .subscribe({
        next: (res) => {
          console.log('Login realizado com sucesso!', res);
        },
        error: (err) => {
          console.error('Erro ao fazer login:', err);
        }
      });
  }
}
