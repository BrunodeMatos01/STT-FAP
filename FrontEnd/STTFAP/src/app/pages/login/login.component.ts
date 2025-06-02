import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router'; // ✅ IMPORTAR ISSO

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule], // ✅ ADICIONAR RouterModule AQUI
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  senha: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const dadosLogin = {
      email: this.email,
      senha: this.senha
    };

    this.http.post('http://localhost:8080/api/login', dadosLogin)
      .subscribe({
        next: (res) => {
          console.log('Login realizado com sucesso!', res);
          this.router.navigate(['/pages/menu']);
        },
        error: (err) => {
          console.error('Erro ao fazer login:', err);
        }
      });
  }
}
 