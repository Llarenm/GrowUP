
Proyecto: Gestión de Personas - Backend

Descripción:
Se desarrolló un módulo backend en Java utilizando Spring Boot, que implementa operaciones CRUD (crear, consultar, actualizar y eliminar) sobre la entidad Persona.

Aclaraciones de uso de JPA:
Se utilizó JPA como herramienta de persistencia, la cual internamente emplea JDBC para la conexión con la base de datos, cumpliendo así con el requerimiento de acceso a datos.

Base de datos de referencia para pruebas de desarrollo backend:

Para desarrollo de este proyecto se hizo uso de la base de datos del proyecto GrowUP para la pruebas con registros sin realizar conexion del proyecto Frontend con el Backend desarrollado.


Tecnologías utilizadas:
- Java
- Spring Boot
- JPA (Hibernate)
- MySQL
- Maven

Funcionalidades:
- Registro de personas
- Consulta de registros
- Actualización de datos
- Eliminación de registros

Arquitectura:
Se implementó arquitectura por capas:
- Model
- Repository
- Service
- Controller

Base de datos:
MySQL conectada mediante JPA (uso interno de JDBC)

Repositorio:
LINK: https://github.com/Llarenm/GrowUP.git

El repositorio contiene dos módulos:

- frontend: interfaz web desarrollada en HTML
- backend: módulo desarrollado en Spring Boot que gestiona la lógica del sistema y conexión a base de datos

Ambos forman parte del mismo sistema.
