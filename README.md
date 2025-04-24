# 🎬 Proyecto Cine Doré — Backend

Este es el backend del proyecto **Cine Doré**, encargado de gestionar los datos de la aplicación: desde la obtención de películas hasta el manejo de compras y usuarios.

---

## 🛠️ Tecnologías y herramientas utilizadas

- ☕ **Java**: Lenguaje principal del proyecto  
- ⚙️ **Spring Boot**: Framework para la creación de APIs  
- 🛡️ **Spring Security**: Autenticación y autorización  
- 🐬 **MySQL**: Base de datos relacional  
- 📘 **Swagger**: Documentación interactiva de la API  
- 📄 **JavaDoc**: Documentación automática del código  
- ✨ **Lombok**: Reducción de boilerplate con anotaciones  
- 🔐 **JWT**: Tokens seguros para autenticación  
- 📦 **Docker**: Contenerización de la aplicación  
- 📷 **ZXing**: Generación de códigos QR  


![My Skills](https://skillicons.dev/icons?i=java,spring,mysql,docker)

---

## 🧪 Instalación local (modo desarrollo)

Este proyecto puede ejecutarse localmente para desarrollo o pruebas.

### 🔧 Requisitos

- Docker + Docker Compose

### 🚀 Pasos

1. Clona el repositorio:
2. Levantar los servicios con docker-compose.yml según configuración
3. Verificar el funcionamiento de los endpoints

---

## 📚 Endpoints
La API está documentada con Swagger. Puedes consultar y probar los endpoints desde:

🔗 Swagger UI: http://localhost:8080/api/swagger-ui/index.html

🔗 Documentación JSON: http://localhost:8080/api/v3/api-docs

✨ Ejemplos de endpoints


🔐 POST /api/usuarios/login
  - Descripción: Inicio de sesión
  - Body: LoginRequestDTO (email, contraseña)
  - Respuesta: Usuario + JWT

🎥 GET /api/peliculas
  - Descripción: Obtener todas las películas
  - Respuesta: Lista de PeliculasDTO

🛒 POST /api/compras/crear
  - Descripción: Realizar una compra y generar QR
  - Body: CompraDTO (datos de compra y tickets)
  - Respuesta: TicketDisplayDTO con el QR

🎫 GET /api/tickets/usuario/{id}
  - Descripción: Obtener tickets activos de un usuario
  - Parámetro: ID del usuario
  - Respuesta: Lista de TicketDisplayDTO

---

## 🔐 Autenticación y seguridad
El proyecto implementa seguridad robusta con Spring Security y JWT:

🔑 Autenticación JWT: El usuario obtiene un token tras iniciar sesión.

🧱 Endpoints protegidos: Accesibles solo con token en la cabecera Authorization.

🛡️ Filtro personalizado: Valida el token en cada petición.

---

## 🔁 Flujo de Autenticación
El usuario se autentica vía /api/usuarios/login

El servidor responde con un token JWT.

El token se debe incluir en cada petición:

---

## ⚠️ Manejo de errores
La API maneja errores de forma centralizada para respuestas consistentes y claras.

✅ Características
📦 Respuestas en formato JSON estructurado

📊 Códigos HTTP adecuados (400, 401, 403, 404, 500…)

🧾 Mensajes detallados para ayudar en depuración
