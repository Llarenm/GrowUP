# Proyecto: Gestión de Personas y Cuentas - Backend GrowUP

## Descripción

Se desarrolló un módulo backend en Java utilizando Spring Boot para la gestión de personas y cuentas de usuario dentro del sistema GrowUP.

El proyecto inició con la implementación de operaciones CRUD sobre la entidad Persona y posteriormente se amplió con el desarrollo del módulo de registro y gestión de cuentas (Cuenta) como parte de la evidencia GA7-220501096-AA3-EV01.

El sistema implementa arquitectura por capas y persistencia de datos mediante JPA/Hibernate conectada a MySQL.

------------------------------------------------------------

# Evidencias desarrolladas

## Evidencia anterior

Implementación del módulo CRUD de personas:

- Registro de personas
- Consulta de registros
- Actualización de datos
- Eliminación de registros

------------------------------------------------------------

## Evidencia actual

GA7-220501096-AA3-EV01
Codificación de módulos del software stand-alone, web y móvil.

Se desarrolló el módulo backend para:

- Registro de cuentas de usuario
- Relación entre entidades Persona y Cuenta
- Persistencia mediante JPA
- Exposición de endpoints REST
- Gestión básica de autenticación y acceso

------------------------------------------------------------

# Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JDBC (uso interno mediante JPA)
- Arquitectura MVC por capas

------------------------------------------------------------

# Arquitectura implementada

Se implementó arquitectura por capas para separación de responsabilidades:

Controller → Service → Repository → Base de datos

## Capas utilizadas

### Model
Contiene entidades y clases del dominio:
- Persona
- Cuenta
- Rol
- EstadoCuenta

### Repository
Gestiona persistencia y acceso a datos mediante JPA.

### Service
Implementa lógica de negocio:
- registro de usuarios
- validaciones
- gestión de entidades

### Controller
Expone endpoints REST para comunicación HTTP.

------------------------------------------------------------

# Funcionalidades implementadas

## Módulo Persona

- Registro de personas
- Consulta de personas
- Actualización de información
- Eliminación de registros

## Módulo Cuenta

- Registro de cuentas
- Asociación entre cuenta y persona
- Gestión de roles
- Persistencia de autenticación básica

------------------------------------------------------------

# Persistencia y acceso a datos

Se utilizó JPA (Hibernate) como framework ORM para la persistencia de datos.

JPA realiza internamente el acceso a base de datos utilizando JDBC, permitiendo trabajar mediante entidades Java y relaciones ORM.

------------------------------------------------------------

# Base de datos

Se utilizó MySQL como sistema gestor de base de datos.

Para el desarrollo se trabajó sobre la base de datos del proyecto GrowUP, utilizando registros reales de prueba para validar:

- persistencia
- relaciones
- endpoints
- integración backend

------------------------------------------------------------

# Endpoints REST implementados

## Persona

- POST /personas
- GET /personas
- PUT /personas
- DELETE /personas/{id}

## Cuenta

- POST /cuenta

------------------------------------------------------------

# Herramientas utilizadas en pruebas

- CMD con curl para pruebas HTTP
- phpMyAdmin
- IntelliJ IDEA

------------------------------------------------------------

# Estructura del proyecto

src/main/java/com/growup/gestionestudiantes

├── controller
├── service
├── repository
├── model
└── dto

------------------------------------------------------------

# Repositorio

LINK:

------------------------------------------------------------

# Organización del repositorio

El repositorio contiene dos módulos:

## frontend

Interfaz web desarrollada en HTML.

## backend

Módulo desarrollado en Spring Boot encargado de:

- lógica del sistema
- persistencia
- conexión a base de datos
- endpoints REST

Ambos módulos forman parte del sistema GrowUP.

------------------------------------------------------------

# Evidencias visuales recomendadas

- Captura de estructura MVC del proyecto
- Consola Spring Boot iniciada correctamente
- Inserción de registros mediante curl
- Registros almacenados en MySQL/phpMyAdmin
- Entidades y relaciones JPA implementadas
