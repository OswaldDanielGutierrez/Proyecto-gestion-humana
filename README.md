# Aplicación de Gestión Humana

Este proyecto es una aplicación de Gestión Humana desarrollada en **Java 17** y **Spring Boot 3.3.2**. La aplicación está estructurada en tres microservicios que gestionan empleados, horas extras y la nómina de los empleados. Cada microservicio utiliza una base de datos independiente en **MySQL** y se comunican entre sí a través de **Feign Clients**. 

## Estructura de Microservicios

### 1. **Microservicio de Empleado**
Este microservicio se encarga de la gestión de los empleados.

#### Funcionalidades:
- **Guardar empleado**: Registra un nuevo empleado en la base de datos.
- **Obtener empleado**: Recupera los datos de un empleado mediante su `idCard`.
- **Obtener todos los empleados**: Devuelve una lista con todos los empleados.
- **Actualizar empleado**: Permite modificar los datos de un empleado.
- **Eliminar empleado**: Elimina un empleado de la base de datos.
- **Obtener horas extras del empleado**: Muestra las horas extras asociadas a un empleado, comunicándose con el microservicio de horas extras mediante su `idCard`.

### 2. **Microservicio de Horas Extras**
Este microservicio gestiona las horas extras trabajadas por los empleados y realiza cálculos relacionados con las mismas.

#### Funcionalidades:
- **Guardar horas extras**: Registra las horas extras trabajadas por un empleado.
- **Obtener todas las horas extras**: Devuelve una lista de todas las horas extras registradas.
- **Obtener horas extras por `idCard`**: Muestra las horas extras de un empleado específico.
- **Calcular el valor de horas extras por `idCard`**: Calcula el valor total de las horas extras de un empleado.
- **Obtener el total del valor de horas extras**: Calcula el monto total de todas las horas extras trabajadas por los empleados.

### 3. **Microservicio de Nómina**
Este microservicio se encarga de calcular y devolver la nómina de todos los empleados, incluyendo salario base y el valor de las horas extras.

#### Funcionalidades:
- **Obtener nómina**: Calcula y devuelve la nómina completa de todos los empleados, integrando la información de los microservicios de empleados y horas extras.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Data JPA** (para la gestión de datos)
- **Feign Clients** (para la comunicación entre microservicios)
- **MySQL** (como base de datos relacional)
- **Maven** (para la gestión de dependencias)
- **Postman** (recomendado para pruebas de API REST)

## Instalación

>git clone  [https://github.com/OswaldDanielGutierrez/Proyecto-gestion-humana.git](https://github.com/OswaldDanielGutierrez/Proyecto-gestion-humana.git "https://github.com/OswaldDanielGutierrez/Proyecto-gestion-humana.git") 
cd gestion-humana

#####Crear las bases de datos en MySQL

- CREATE DATABASE empleadosDb;
- CREATE DATABASE overtimeDb;
- CREATE DATABASE payrollDb;

## Características Futuras

A continuación se listan algunas de las características que se agregarán en versiones futuras de la aplicación:

### 1. **Generación de Nómina en PDF**
Se añadirá la funcionalidad para convertir y descargar la nómina de los empleados en formato PDF. Esto permitirá a los usuarios generar reportes de nómina listos para imprimir o almacenar digitalmente.

### 2. **Autenticación y Autorización**
Se integrará un sistema de autenticación y autorización utilizando **Spring Security** para asegurar que solo los usuarios autorizados puedan acceder y manipular los microservicios. Esto incluirá:
- Autenticación con **JWT (JSON Web Tokens)** para gestionar sesiones de usuarios.
- Roles y permisos para diferenciar entre los usuarios que pueden ver, modificar o eliminar información.


