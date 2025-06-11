## 1. Alinhamento do Stack Tecnológico

1. **Consistência entre Frontend e Backend**

   * No topo do documento, você lista **Angular + TypeScript** no frontend e **Node.js + Java** no backend. Porém, em memórias anteriores (nos últimos dias) era mencionado que o sistema seria desenvolvido com **Spring Boot** (Java) e **React**. Seria interessante decidir um único fluxo tecnológico para evitar retrabalho.

     * **Opção A**: Manter Angular (frontend) + Spring Boot (backend) + PostgreSQL.
     * **Opção B**: Migrar para React (frontend) + Spring Boot (backend) + PostgreSQL.
     * **Opção C**: Se quiser usar Node.js como backend (por exemplo, Express + TypeORM + MySQL), então pode escolher Angular ou React no frontend, mas cuide de em qual linguagem ficará a maior parte da lógica de negócio.

2. **Banco de Dados**

   * No documento constam ícones de MySQL. Mas até então, nas memórias você planejava PostgreSQL. Escolher um único banco (MySQL ou PostgreSQL) e padronizar a configuração evitará divergências de modelagem (p. ex., sintaxe de tipos de dados, stored procedures, views etc.).

3. **Ferramentas e IDEs**

   * Você inclui tanto WebStorm/IntelliJ IDEA quanto VS Code; OK manter ambos para equipes híbridas, mas defina quais serão usadas para cada parte do projeto (por exemplo, backend em IntelliJ e frontend em VS Code).

---

## 2. Estruturação do Documento e Informações Adicionais

Para que o projeto fique mais completo e seja facilmente entendido por qualquer membro da equipe, sugiro adicionar as seguintes seções abaixo da descrição atual:

1. **Objetivos e Escopo (Escopo do MVP)**

   * Definir claramente quais funcionalidades são essenciais na versão mínima viável (MVP). Por exemplo:

     * Cadastro de ocorrência (Admin e Técnico).
     * Visualização de ocorrências em mapa.
     * Autenticação (Login e redefinição de senha).
     * Painel administrativo com filtros básicos.
   * Funcionalidades secundárias podem entrar em sprints posteriores (envio de alertas por SMS, integração com APIs externas, etc.).

2. **Requisitos Funcionais Detalhados**

   * Para cada item de “Funcionalidades Principais”, descreva pelo menos uma user story ou requisito, por exemplo:

     1. **UC-01: Cadastrar Ocorrência**

        * “Como *Agente de Campo*, quero cadastrar uma ocorrência de acidente urbano, informando data, hora, tipo, coordenadas (GPS) e fotos, para que a equipe de gestão possa acompanhar em tempo real.”
     2. **UC-02: Filtrar Ocorrências**

        * “Como *Gestor*, quero filtrar ocorrências por data, tipo e localização, para gerar relatórios e mapear pontos críticos.”

3. **Requisitos Não Funcionais**

   * **Segurança:** uso de JWT ou OAuth2 para autenticação, criptografia de senhas, proteção contra ataques (CSRF, XSS).
   * **Desempenho:** tempo máximo de resposta para pesquisas / filtros (por exemplo, < 500 ms para retornar até 100 ocorrências).
   * **Escalabilidade:** possibilidade de escalar horizontalmente se a base de dados crescer.
   * **Disponibilidade:** definir SLA mínimo (por exemplo, ≥ 99,5 % de uptime).
   * **Compatibilidade móvel:** garantir responsividade ou ter uma versão mobile (React Native, PWA ou serviço híbrido).

4. **Modelo Conceitual / Diagrama de Entidades**

   * Ao menos um diagrama de entidade-relacionamento (ER) que mostre as principais tabelas:

     * **Usuário** (id, nome, e-mail, perfil \[Admin/Técnico], senha hashed, etc.)
     * **Unidade Operacional** (id, nome, tipo, localização, responsável)
     * **EPI/EPC** (id, descrição, categoria de risco, validades)
     * **Ocorrência** (id, tipo \[acidente/interdição/falha], data\_hora, localização (lat,lng), fotos, status)
     * **Serviço** (id, tipo de serviço, lista de EPIs, técnico designado, datas de início/fim, controle de prorrogação)
     * **Inspeção** (id, extintor\_id, data\_inspeção, checklist, fotos, observações)
     * **Relatório / FAP / PT** (id, tipo, status, data\_emissão, data\_expiração, assinaturas).

5. **Fluxos de Navegação (Wires / Wireframes Simplificados)**

   * Incluir esboços (mesmo que simples) mostrando a sequência de telas para cada perfil. Exemplo:

     * **Admin → Tela de Login → Dashboard Admin → Menu Cadastro de Unidades → Formulário de Cadastro → Confirmação**
     * **Técnico → Tela de Login → Lista de Serviços Designados → Detalhes do Serviço → Checklist de Inspeção → Upload de Fotos → Concluir Serviço**

6. **Arquitetura de Software**

   * **Diagrama de camadas** (cliente, servidor, serviços externos, banco de dados).
   * **API REST**: liste os endpoints principais com verbos HTTP, por exemplo:

     * `POST /api/auth/login`
     * `GET /api/usuarios` (Admin)
     * `POST /api/ocorrencias` (Técnico/Admin)
     * `GET /api/ocorrencias?dataInicio=&dataFim=&tipo=&localidade=`
     * `PUT /api/servicos/{id}/prorrogar`
     * `POST /api/inspecoes/{id}/fotos`
   * **Módulos React/Angular**: dividir em módulos (ex.: módulo unidades, módulo ocorrências, módulo inspeções, módulo usuários).

7. **Plano de Implantação / Infraestrutura**

   * Escolher onde a aplicação ficará hospedada (por ex., AWS EC2 + RDS, Heroku, DigitalOcean).
   * Processo de CI/CD (GitHub Actions, GitLab CI, Jenkins).
   * Configuração de variáveis de ambiente (URLs, credenciais do banco, chaves de API para mapas).
   * **Containers (Docker)**: ter um `Dockerfile`/`docker-compose.yml` para facilitar a orquestração entre frontend, backend e banco de dados.

8. **Controle de Versão e Issues**

   * Definir convensão de branching (p.ex., `main`, `develop`, `feature/...`, `hotfix/...`).
   * Fluxo de review de código (pull requests no GitHub ou GitLab).
   * Quadro de tarefas (GitHub Projects, Trello, Jira) organizando épicos, histórias e tarefas.

9. **Roadmap e Cronograma de Desenvolvimento**

   * **Sprint 1 (2 semanas):**

     * Configuração inicial do repositório, CI/CD, containerização.
     * Autenticação básica (login, logout, redefinição de senha).
     * CRUD de Usuários (Admin).
   * **Sprint 2 (2 semanas):**

     * CRUD de Unidades Operacionais e Responsáveis.
     * Modelagem e CRUD de EPIs/EPCs.
     * Testes unitários iniciais (backend).
   * **Sprint 3 (2 semanas):**

     * Cadastro de Ocorrências (upload de fotos).
     * Implementação de filtros e mapa de visualização.
     * Primeira versão do Dashboard Admin (gráficos simples).
   * **Sprint 4 (2 semanas):**

     * Módulo de Serviços (designação de técnicos, EPIs, prorrogação).
     * Checklist de Inspeção (web/mobile).
   * **Sprint 5 (2 semanas):**

     * Geração de Relatórios (PDF/FIESC).
     * Agendamento de envio de relatórios por e-mail.
     * Ajustes finais, soltar versão Beta para testes internos.

---

## 3. Comentários sobre as Telas e Perfis

### 3.1. Tela de Login / Redefinição de Senha (Ambos)

* **Observação**: garantir fluxo de “Esqueci minha senha” com token temporário enviado por e-mail.
* **Sugestão**: adicionar autenticação de dois fatores (2FA), pelo menos opcional, visando aumentar a segurança.

### 3.2. Exclusivo Admin

* **Cadastro de Usuário (com Perfis e Permissões)**

  * Definir claramente quais permissões cada perfil terá (Admin Master, Admin Setor, Gestor, etc.).
  * Talvez usar um sistema de Roles (RBAC) para facilitar a atribuição de privilégios.

* **Cadastro de Unidades Operacionais e Responsáveis**

  * Permitir hierarquia de unidades (padrão árvore): Região → Filial → Setor → Obra.
  * Tela pode apresentar “breadcrumbs” ou “árvore” para navegação.

* **Cadastro de EPIs/EPCs**

  * É útil ter categorias pré-definidas (ex.: Químico, Biológico, Elétrico) e subcategorias.
  * Incluir campos como vida útil, frequência de substituição, fabricante, custo.
  * Permitir upload de fichas técnicas ou manuais em PDF.

* **Criação de Serviço → Seleção de Técnico → Definição de EPIs → Recomendação de Medidas**

  * Esse fluxo é bem específico: talvez desenhar uma tela multitabs em que o Admin preenche primeiro dados gerais (serviço, data, local), depois escolhe um técnico (lista de técnicos disponíveis), depois lista de EPIs recomendados (com base no tipo de serviço), e por fim, medidas de controle.
  * Se possível, sugerir EPIs automaticamente a partir de regras (ex.: se “Serviço = Manutenção em altura”, então incluir cinto de segurança, trava-queda, capacete etc.).

* **Validação e Assinaturas**

  * Indicar claramente quem assina cada etapa (Administrador, Técnico, Responsável pela Unidade).
  * Se for assinar digitalmente, integrar com alguma biblioteca de assinatura eletrônica (ex.: DocuSign, Adobe Sign) ou implementar assinatura com certificado digital (ICP-Brasil).
  * Caso só seja “marca de aceite”, deixar uma check-box e um campo de “Observações da assinatura”.

* **Agendamento de Envio de Relatórios por E-mail**

  * Permitir o Admin configurar periodicidade (diária, semanal, mensal) e destinatários.
  * Tela de parâmetros pode ser um modal dentro do “Painel Admin” ou uma aba específica de “Configurações > E-mail”.

* **Parâmetros e Configurações do Sistema**

  * Deve incluir:

    * Configuração de SMTP (servidor de e-mail, porta, usuário, senha).
    * Configuração de APIs de mapas (Google Maps / OpenStreetMap).
    * Definição de permissões padrão (ex.: tempo máximo de prorrogação).
    * Políticas de senhas (complexidade, expiração).

### 3.3. Exclusivo Técnico

* **Visualização de Serviços Designados**

  * Tela inicial após login deve listar os serviços pendentes, com indicadores de urgência (por data de vencimento, nível de risco).
  * Permitir ordenar/filtar por data, tipo de serviço, status (em andamento, pendente de prorrogação, finalizado).

* **Controle de Prorrogação e Encerramento do Serviço**

  * Separar claramente dois botões: “Solicitar Prorrogação” e “Encerrar Serviço”.
  * Ao encerrar, abrir modal para comprovar checklist e upload de fotos.

### 3.4. Funcionalidades Comuns (Admin & Técnico)

* **Cadastro de Extintores**

  * Modelar entidade “Extintor” com campos: número de série, tipo (PQ-S, CO₂, etc.), capacidade, data de aquisição, data da última inspeção.
  * Tela de listagem paginada com busca por tag/ID.

* **Agenda de Inspeções / Alertas e Notificações**

  * Ferramenta de calendário (fullcalendar.js ou componente similar).
  * Permitir agendamento recorrente (ex.: inspeção mensal).
  * Notificações por e-mail ou SMS (integrar com Twilio ou outros).

* **Checklist de Inspeção (web & mobile)**

  * Checklist pré-definido para cada tipo de inspeção.
  * Interface amigável para preenchimento rápido no celular (React + PWA ou React Native).
  * Permitir desenhar no mapa ou marcar sobre imagem do local.

* **Upload de Fotos e Observações**

  * Usar serviço de armazenamento em nuvem (S3, Google Cloud Storage) ou mantê-la local, mas padronizar nomes e paths.
  * Comprimir imagens antes de salvar para economizar espaço e banda.

* **Geração de Relatórios (PDF / Excel)**

  * Bibliotecas sugeridas (backend Java): iText ou JasperReports para gerar PDF. Para exportar Excel, usar Apache POI.
  * Criar templates visuais limpos: cabeçalho com logo, título do relatório, filtros aplicados, tabelas e gráficos.
  * Permitir download direto ou envio por e-mail (módulo de agendamento).

* **Filtros e Exportações**

  * Em cada tela de listagem (Ocorrências, Inspeções, FAPs/PTs), colocar filtros na coluna esquerda e botões de “Exportar CSV” e “Exportar PDF” no topo.
  * Cuidar para que a exportação aplique exatamente os filtros usados pelo usuário.

* **Notificações do Sistema (e-mail, SMS)**

  * Definir gatilhos:

    1. Vencimento de extintor em 7 dias → SMS ao responsável e e-mail ao admin.
    2. FAP/PT expirado → alerta automático para gestor.
    3. Ocorrência de alta gravidade cadastrada → notificar equipe de resposta.

* **Ajuda / Suporte / FAQ**

  * Construir seção estática com perguntas frequentes, passo a passo para cada perfil.
  * Possibilidade de chat interno (Slack channel ou integração com alguma API de chat).

* **Perfil e Preferências do Usuário**

  * Em “Configurações” ou “Meu Perfil”: alterar foto, nome, e-mail, telefone, preferências de notificação (e-mail, SMS), língua (PT-BR/EN).

---

## 4. Recomendações de Organização de Pastas no Repositório

1. **Backend (Spring Boot / Node.js)**

   ```
   /backend
     ├── src
     │   ├── main
     │   │   ├── java/com/empresa/sstfap
     │   │   │   ├── controller
     │   │   │   ├── service
     │   │   │   ├── repository
     │   │   │   └── model
     │   │   └── resources
     │   │       ├── application.properties
     │   │       └── db
     │   └── test
     ├── Dockerfile
     ├── pom.xml (ou package.json se Node.js)
     └── README.md
   ```

2. **Frontend (Angular ou React)**

   ```
   /frontend
     ├── public (para assets estáticos)
     ├── src
     │   ├── app (Angular) ou src (React)
     │   │   ├── components
     │   │   ├── pages
     │   │   ├── services (chamadas HTTP)
     │   │   ├── assets (imagens, ícones)
     │   │   └── styles (scss/css globais)
     │   └── index.html / index.tsx
     ├── angular.json (ou package.json)
     ├── tsconfig.json
     ├── Dockerfile
     └── README.md
   ```

3. **Infraestrutura / DevOps**

   ```
   /infra
     ├── docker-compose.yml
     ├── .github
     │   └── workflows
     │       └── ci-cd.yml
     ├── k8s (se usar Kubernetes)
     │   ├── deployment.yaml
     │   └── service.yaml
     └── scripts
         ├── build.sh
         └── deploy.sh
   ```

---

## 5. Próximos Passos Sugeridos

1. **Escolher e consolidar o stack**

   * Defina se vai usar **Angular vs React** e **Node.js vs Spring Boot** antes de criar a estrutura de projeto.
   * Ajuste este documento de acordo com a decisão (ícones, dependências, instruções de instalação).

2. **Criar o repositório e esboçar um `README.md` inicial**

   * Incluir instruções para clonar, configurar variáveis de ambiente e rodar em modo de desenvolvimento.
   * Link para protótipos ou wireframes (Figma, Sketch ou qualquer ferramenta de design que esteja usando).

3. **Estabelecer um backlog no GitHub Projects (ou similar)**

   * Liste as user stories identificadas na seção de requisitos funcionais.
   * Priorize e atribua prazos estimados.

4. **Desenhar protótipos de baixa fidelidade (wireframes)**

   * Nem sempre é necessário criar mockups extremamente detalhados, mas ter o fluxo de telas ajuda no entendimento de navegação.
   * Ferramentas como Figma (gratuito para projetos pequenos) podem ser suficientes.

5. **Configurar o banco de dados e criar migrações iniciais**

   * Se for Spring Boot + JPA, crie entidades e use Flyway ou Liquibase para versionar o schema.
   * Se for Node.js + TypeORM, crie as entidades e configure scripts de migração.

6. **Implementar primeiro módulo de autenticação**

   * É a base para qualquer chamada autenticada.
   * Inclua roteiro de testes manuais e automáticos (Postman + JUnit ou Jest).

7. **Agendar revisões periódicas de design e arquitetura**

   * Sempre que terminar um módulo (por exemplo, Cadastro de Unidades), faça uma demo para a equipe e documente lições aprendidas.
   * Ajuste backlog conforme feedback de usuários (se já houver testes internos).

---

## 6. Sugestões de Ferramentas e Bibliotecas

* **Frontend**

  * Se optar por React:

    * **React Router** para navegação.
    * **Redux Toolkit** ou **Context API** para gerenciamento de estado.
    * **Ant Design**, **Material-UI** ou **Tailwind CSS** para componentes prontos.

* **Backend (Java)**

  * **Spring Boot Starter Security** para autenticação e autorização.
  * **Spring Data JPA** + **Hibernate** para persistência.
  * **Flyway** ou **Liquibase** para versionamento de banco.
  * **OpenAPI / Swagger** para documentação automática dos endpoints.

* **Geolocalização / Mapa**

  * **Leaflet.js** (OpenStreetMap) ou **Google Maps JavaScript API**.
  * Para backend, conversão de endereços em coordenadas (geocoding), usar **Geocoding API** do Google ou alguma biblioteca em Java.

* **Geração de PDF/Relatórios**

  * **JasperReports** (bom para relatórios mais robustos).
  * **iText** (focado em gerar PDF programaticamente).
  * **Apache POI** para relatórios em Excel.

* **Envio de E-mail/SMS**

  * **Spring Boot Mail** (JavaMail) para e-mails.
  * **Twilio** (SMS) ou **Nexmo** (alternativa).

* **Teste e Qualidade de Código**

  * **JUnit + Mockito** (backend Java).
  * **Jest + React Testing Library** (frontend React).
  * **ESLint + Prettier** (JS/TS).
  * **SonarQube** para análise de qualidade de código.

---

## 7. Possíveis Pontos de Atenção

1. **Gerenciamento de arquivos (fotos, PDFs)**

   * Defina limite de tamanho por upload (ex.: máximo 5 MB por foto).
   * Padronizar estrutura de pastas no storage ou S3.

2. **Usuários e Permissões**

   * Se no futuro for necessário incluir perfis adicionais (Supervisor, Visitante, etc.), ter sistema de Roles genérico facilita evolução.

3. **Escalabilidade e Separação de Preocupações**

   * Caso o projeto cresça, pensar em separar microserviços (ex.: um microserviço apenas para relatórios, outro para autenticação). Mas, num primeiro momento, monolito modularizado pode ser suficiente.

4. **Acessibilidade**

   * Garantir que as telas tenham contraste adequado e sejam navegáveis via teclado.

5. **Internacionalização (i18n)**

   * Se houver plano de usar mais de um idioma no futuro, configurar biblioteca de tradução (ex.: Spring MessageSource para backend e react-i18next no frontend).




## 🧰 Tecnologias Utilizadas

### Frontend
<img src="https://skillicons.dev/icons?i=angular,ts,html,css" />

### Backend
<img src="https://skillicons.dev/icons?i=nodejs,java,js,git" />

### IDEs e Ferramentas
<img src="https://skillicons.dev/icons?i=idea,vscode,mysql" />



