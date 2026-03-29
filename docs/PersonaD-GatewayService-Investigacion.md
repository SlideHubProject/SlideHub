nvestigación: Gateway Service & Config Server
Proyecto: SlideHub

Responsable: Persona D (Sharon)

Fecha: Marzo 2026

1. Introducción y Premisa
El Gateway Service y el Config Server constituyen la columna vertebral de la arquitectura de microservicios de SlideHub. Su implementación resuelve dos problemas críticos:

Enrutamiento Centralizado: Evita que el cliente (Frontend) necesite conocer las direcciones IP o puertos individuales de cada microservicio.

Configuración Dinámica: Permite gestionar parámetros de todos los servicios desde un único punto, facilitando el mantenimiento y la escalabilidad.

2. Análisis de Documentación Interna
Basado en la revisión de AGENTS.md (§2.4) y CLAUDE.md (§12).

2.1 Spring Cloud Gateway
El servicio utiliza Spring Cloud Gateway para gestionar el tráfico de red.

Predicados: Se encargan de validar si una petición entrante cumple con ciertas condiciones (ej. el path empieza por /api/v1/).

Filtros: Permiten modificar la petición antes de ser enviada al servicio destino o la respuesta antes de llegar al usuario (ej. añadir cabeceras de seguridad).

2.2 Config Server
Actúa como un repositorio centralizado de configuraciones.

Almacenamiento: [Definir si se usa Git o sistema de archivos local].

Seguridad: Centraliza el manejo de credenciales de base de datos y llaves API.

3. Estrategia de Despliegue en Render
Referencia: DEPLOYMENT.md

Para garantizar un despliegue exitoso en Render, se deben considerar los siguientes puntos:

Build Command: Uso de ./mvnw clean package -DskipTests para generar el ejecutable.

Start Command: Ejecución mediante java -jar target/*.jar.

Variables de Entorno (Secrets): * SPRING_PROFILES_ACTIVE: Para alternar entre entornos (prod/test).

CONFIG_SERVER_URL: Dirección donde el Gateway buscará sus parámetros.

PORT: Render asigna un puerto dinámico que Spring Boot debe capturar.

4. Diseño de Contribución Práctica
4.1 Ruta de Prueba (Dummy Route)
Se planea implementar una ruta en RoutesConfig.java para validar el flujo de datos:

Path: /test

Destino: Servicio Mock / Externo.

Propósito: Verificar que el Gateway intercepta y redirige correctamente sin pasar por la lógica de negocio compleja.

4.2 Perfil de Testing Local
Configuración de application-local.properties para permitir pruebas rápidas en la máquina del desarrollador sin afectar las variables de producción.

5. Glosario de Conceptos Aprendidos
Microservicios: Arquitectura de software distribuida.

API Gateway: Punto de entrada único al sistema.

Externalized Configuration: Separación del código y los parámetros de configuración.

PaaS (Render): Plataforma para despliegue simplificado en la nube.