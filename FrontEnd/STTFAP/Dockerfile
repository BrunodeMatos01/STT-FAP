# Etapa 1: build do Angular
FROM node:18 as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --configuration=production

# Etapa 2: servir com Nginx
FROM nginx:alpine
COPY --from=build /app/dist/**nome-do-projeto** /usr/share/nginx/html

# Exponha a porta padrão do Nginx
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
