// src/app/pages/userregister/userregister.component.ts
import { Component }    from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule }  from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-user-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './userregister.component.html',
  styleUrls: ['./userregister.component.css']
})
export class UserRegisterComponent {
  name     = '';
  email    = '';
  password = '';

  onSubmit(): void {
    // Como o botão já está desabilitado quando inválido,
    // aqui podemos assumir que o form é válido
    console.log('Cadastro realizado com os seguintes dados:');
    console.log('Nome:', this.name);
    console.log('Email:', this.email);
    console.log('Senha:', this.password);
    alert('Cadastro realizado com sucesso!');
  }
}
