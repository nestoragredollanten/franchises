# franchises
Prueba Práctica Backend - Nequi
This project is a Spring Boot application for managing franchises, branches, and products, with AWS DynamoDB as the
persistence layer.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Despliegue](#despliegue)

## Características

-
   1. El proyecto debe desarrollarse utilizando Spring Boot.
-
   2. Debe existir un endpoint para agregar una nueva franquicia.
-
   3. Debe existir un endpoint para agregar una nueva sucursal a una franquicia.
-
   4. Debe existir un endpoint para agregar un nuevo producto a una sucursal.
-
   5. Debe existir un endpoint para eliminar un producto de una sucursal.
-
   6. Debe existir un endpoint para modificar el stock de un producto.
-
   8. Se deben utilizar sistemas de persistencia de datos como Dynamo en un proveedor de nube, quedando a libre
      elección.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación utilizado.
- **Spring Boot**: Framework para construir aplicaciones Java.
- **AWS DynamoDB**: Base de datos NoSQL utilizada para el almacenamiento de datos.
- **Kubernetes (EKS)**: Orquestador de contenedores para gestionar la aplicación.
- **Docker**: Contenerización de la aplicación.

## Requisitos

- Java 17 o superior.
- Maven.
- Gradle.
- AWS CLI configurado con las credenciales adecuadas.
- Acceso a un clúster de Kubernetes en EKS.
- Docker.

## Instalación

1. Clona este repositorio:

- git clone https://github.com/nestoragredollanten/franchises.git

## Configuración

Para ejecutar este proyecto de forma local, es necesario configurar tus credenciales de AWS. Asegúrate de que tu clave
de acceso y clave secreta de AWS estén almacenadas en la siguiente ubicación:

- ~/.aws/credentials

- [default]
- aws_access_key_id = <TU_ACCESS_KEY_ID>
- aws_secret_access_key = <TU_SECRET_ACCESS_KEY>

Para contenerizar la aplicación, es necesario construir la imagen de Docker. Para ello, ejecuta el siguiente comando en
la raíz del proyecto:

1. Compilar el proyecto
2. Verificar que el archivo jar se haya generado en la carpeta build/libs/franchises-0.0.1-SNAPSHOT.jar
2. docker build -t api-gestion-franquicias .
3. docker run -p 8080:8080 api-gestion-franquicias


   

