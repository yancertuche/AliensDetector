# DNA ALIEN DETECTOR API

## Descripción

Esta es una API para detectar secuencias de ADN alienígenas y obtener estadísticas sobre las detecciones realizadas. La API está construida utilizando Spring Boot y proporciona dos endpoints:
- `POST /alien`: Detecta si una secuencia de ADN es alienígena.
- `GET /stats`: Obtiene estadísticas sobre las detecciones de ADN.

## Prerrequisitos

Asegúrate de tener instalados los siguientes componentes en tu sistema:

- Java 8
- Maven
- Git (opcional, para clonar el repositorio)

## Configuración del Proyecto

1. **Clonar el Repositorio (opcional):**

    ```bash
    git clone https://github.com/yancertuche/AliensDetector.git
    cd AliensDetector
    ```

2. **Configurar el archivo `application.properties`:**

   El proyecto cuenta con el archivo `application.properties` en el directorio `src/main/resources` donde se configura la política de acceso a la API (disponible para cualquier origen), accesos a la base de datos (en este caso se uso H2) y la habilitación de caché (redis) para aumentar rendimiento con el siguiente contenido:

    ```properties
   cross.origin=*
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=update
   spring.cache.type=redis
   spring.redis.host=localhost
   spring.redis.port=6379
    ```

## Construcción y Ejecución de la API

1. **Construir el Proyecto:**

   Navega al directorio del proyecto y ejecuta el siguiente comando para construir el proyecto usando Maven:

    ```bash
    mvn clean install
    ```

2. **Ejecutar la Aplicación:**

   Una vez construido el proyecto, ejecuta la aplicación utilizando el siguiente comando:

    ```bash
    mvn spring-boot:run
    ```

   La API estará disponible en `http://localhost:8080`.
## DOCUMENTACIÓN DE LA API

   El proyecto cuenta con documentación Swagger configurada la cual está disponible en la ruta `http://localhost:8080/swagger-ui/index.html` y donde encontrará un cliente disponible para usar la API y realizar pruebas.

## Ejecución de pruebas unitarias

   Para ejecutar las pruebas unitarias usa el siguiente comando:
   
   ```bash
      mvn test
   ```


## Generación del reporte de Cobertura de Código

   Para generar el reporte de cobertura de JaCoCo, ejecuta:

   ```bash
   mvn clean verify
   ```
   El reporte estará disponible en el directorio `target/site/jacoco` en distintos formatos.