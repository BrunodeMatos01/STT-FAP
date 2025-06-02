// src/app/pages/register/register.component.ts
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,              // ⚠️ marca como standalone
  imports: [FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  nome: string = '';
  email: string = '';
  senha: string = '';
  confirmarSenha: string = '';

  onRegister() {
    if (this.senha !== this.confirmarSenha) {
      alert('As senhas não coincidem!');
      return;
    }

    console.log('Usuário registrado:', {
      nome: this.nome,
      email: this.email,
      senha: this.senha
    });

    alert('Registro realizado com sucesso!');
    // Você pode redirecionar, por exemplo:
    // this.router.navigate(['/pages/login']);
  }
}
