import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,             
  imports: [FormsModule, RouterModule, HttpClientModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  nome: string = '';
  email: string = '';
  senha: string = '';
  tipoUsu: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  onRegister() {

    const user = {
      nome: this.nome,
      email: this.email,
      senha: this.senha,
      tipoUsu: this.tipoUsu
    };

    this.http.post('http://localhost:8080/api/register', user). subscribe({
      next: () => {
        alert('Registro realizado com sucesso!');
        this.router.navigate(['/pages/login']);
      },
      error: (err) => {
        console.error('Erro ao cadastrar usuario', err);
        alert('Erro no registro. Tente novamente.');
      }
    })
    
  }
}
