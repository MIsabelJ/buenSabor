# Etapa de construcción
FROM gradle:7.5.0-jdk17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Gradle
COPY build.gradle .
COPY settings.gradle .

# Copia el código fuente del proyecto
COPY src ./src

# Compila el proyecto y empaqueta en un archivo JAR
RUN gradle build -x test

# Etapa de ejecución
FROM amazoncorretto:17-alpine3.19-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR desde la etapa de compilación
COPY --from=build /app/build/libs/*.jar app.jar

# Define el comando para ejecutar la aplicación Java al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]