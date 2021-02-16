# dotnet-ef-sqlserver

## Pre-requisitos
1. Tener una instancia de Sql Server

## Crear base de datos
1. Instala la CLI de Entity Framework
    ```
    dotnet tool install --global dotnet-ef
    ```
2. En el proyecto donde se encuentre el DbContext ejecuta lo siguiente para crear la migración
    ```
    dotnet ef migrations add Inicio --project src/Main
    ```
3. Aplica la migración
    ```
    dotnet ef database update --project src/Main
    ```
Comprueba que en la base de datos se ha creado una base de datos con tablas.

## SqlServer con docker
Si tienes docker y docker compose instalados, puedes crear un archivo `linux-mssqlserver.yml`
con el siguiente contenido
```
version: '3'
services:
    sqlserver:
        image: microsoft/mssql-server-linux
        restart: always
        ports:
            - 1433:1433
        environment:
            - ACCEPT_EULA=Y
            - SA_PASSWORD=Password12!
```

y levantar la instancia de Sql Server con
```
docker-compose -f linux-mssqlserver.yml up -d 
```

Ahora puedes conectarte a *localhost* con SQL Server Management Studio o Azure Data Studio
utilizando las credenciales `sa` y la contraseña especificada
