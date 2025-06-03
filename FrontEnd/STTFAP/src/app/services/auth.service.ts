import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  login(email: string, senha: string,): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { email, senha,});
  }

  register(nome: string, email: string, senha: string, tipoUsu: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, {nome, email, senha, tipoUsu})
  }
}
