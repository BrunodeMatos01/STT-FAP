import { Component, AfterViewInit, ElementRef, ViewChildren, QueryList, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements AfterViewInit {
  showMenu = false;

  menuItens = [
    { titulo: 'Cadastro de Usuário', rota: '/cadastro-usuario' },
    { titulo: 'Cadastro de Extintores', rota: '/cadastro-extintores' },
    { titulo: 'Criação de Serviço', rota: '/criacao-servico' },
    { titulo: 'Painel de Indicadores', rota: '/painel-indicadores' },
  ];

  nome = '';
  email = '';

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }

  closeMenu() {
    this.showMenu = false;
  }

  onSubmit() {
    if (this.nome && this.email) {
      alert(`Obrigado pelo cadastro, ${this.nome}!`);
      this.nome = '';
      this.email = '';
    }
  }

  @ViewChildren('fadeIn') fadeElements!: QueryList<ElementRef>;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId) && 'IntersectionObserver' in window) {
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.classList.add('visible');
            observer.unobserve(entry.target);
          }
        });
      }, { threshold: 0.2 });

      this.fadeElements.forEach(el => observer.observe(el.nativeElement));
    }
  }
}
