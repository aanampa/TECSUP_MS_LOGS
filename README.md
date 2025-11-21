# TECSUP_MS_LOGS - Microservicio para registro de Logs
Este proyecto es un microservicio RESTful desarrollado con Java Spring Boot encargado del registro de logs para diferenes aplicaciones. Está diseñado para ser desplegado fácilmente mediante Docker y utiliza PostgreSQL como motor de base de datos.

## Tecnologías

* **Lenguaje:** Java 17+
* **Framework:** Spring Boot (Web, JPA, Validation)
* **Base de Datos:** PostgreSQL
* **Herramientas:** Maven, Docker
* **Manejador de eventos: ** Apache Kafka

## Instalación con Docker

1. Asegúrate de tener Docker instalado.
2. Clonar el repositorio
   ```bash
   git clone https://github.com/aanampa/TECSUP_MS_LOGS.git

   # ingresar a carpeta del proyecto
   cd TECSUP_MS_LOGS
   ```

4.  Ejecuta el siguiente comando en la raíz del proyecto:
    ```bash
    docker-compose up --build -d
    ```
5.  Verifica que los contenedores estén corriendo:
    ```bash
    docker ps
    ```
6.  Compilar aplicacion java
    ```bash
    ./mvnw clean install
    ```
7.  Ejecutar aplicacion java
    ```bash
    ./mvnw spring-boot:run
    ```
## Ejecución

* Listar logs:
GET http://localhost:8065/api/logs

* Registrar logs:
POST http://localhost:8065/api/logs/registrar
```bash
{
  "sistema": "DEMO2025",
  "entidad": "LIMA",
  "identificador": "111",
  "contenido":"contenido 122"
}
```

* Kafka UI
Abrir la siguente ruta en el navgador:
<a href="http://localhost:8090" target="_blank">http://localhost:8090</a>

```
- Ir a Topics
- Ir a mensajes-topic
- Ir a Messages
```
