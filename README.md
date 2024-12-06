# Hospital Management System

Aplicación de gestión de citas. Utiliza Spring Boot, Java 17 y MySQL para proporcionar una API REST.

## Descripción

La aplicación permite a los doctores del área de Medicina Interna gestionar sus citas de manera eficiente. Las funcionalidades incluyen:

- Agendar citas
- Consultar citas
- Editar citas
- Cancelar citas

### Reglas para la gestión de citas

- No se puede agendar cita en un mismo consultorio a la misma hora.
- No se puede agendar cita para un mismo doctor a la misma hora.
- No se puede agendar cita para un paciente a la misma hora ni con menos de 2 horas de diferencia para el mismo día.
- Un mismo doctor no puede tener más de 8 citas en el día.

## Requisitos

- Java 17
- MySQL
- Maven

## Instalación

1. El proyecto fue creado con Spring Boot Initializr.
2. Base de datos: Ejecuta el archivo `sql/script.sql` en una base de datos MySQL para crear las tablas y datos iniciales.
3. Ejecutar proyecto:
   a) Abre una consola en la ruta donde se descargó el proyecto.
   b) Ejecuta:
      ```sh
      ./mvnw spring-boot:run
      ```
4. Pruebas: Importar el archivo `Hospital.postman_collection.json` en Postman.
   - Casos de uso:
     a) Nueva cita
     b) Consultar cita
     c) Eliminar cita
     d) Editar cita

