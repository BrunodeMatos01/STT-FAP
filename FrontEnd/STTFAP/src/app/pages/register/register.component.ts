  import { Component } from '@angular/core';
  import { FormsModule } from '@angular/forms';
  import { Router, RouterModule } from '@angular/router';
  import { HttpClient, HttpClientModule } from '@angular/common/http';

  @Component({
    selector: 'app-register',
    standalone: true,
    imports: [FormsModule, RouterModule, HttpClientModule],
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
  })
  export class RegisterComponent {
    nome = '';
    email = '';
    senha = '';
    tipoUsu = '';

    constructor(private router: Router, private http: HttpClient) {}

    pagesLogin() {
      this.router.navigate(['/pages/login']);
    }

    onRegister() {
      const user = {
        nome: this.nome,
        email: this.email,
        senha: this.senha,  
        tipoUsu: this.tipoUsu
      };

      this.http.post('http://localhost:8080/api/register', user, { responseType: 'text' }).subscribe({
        next: () => {
          alert('Registro realizado com sucesso!');
          this.router.navigate(['/login']); 
        },
        error: (err) => {
          console.error('Erro ao cadastrar usuário:', err);
          alert('Erro no registro. Tente novamente.');
        }
      });
    }
  }
