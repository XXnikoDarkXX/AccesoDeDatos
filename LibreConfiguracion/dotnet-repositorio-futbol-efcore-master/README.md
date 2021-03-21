# dotnet-repositorio-futbol-efcore

## Descripción
Soccer es una web API que permite añadir información sobre partidos de fútbol
y permite recuperar reportes de partidos ya finalizados donde se muestra el marcador.

## Pre-requisitos
1. Tener instalado el SDK de .NET 5.0. 
   
   **NOTA**: Alternativamente se permite utilizar con el SDK .NET Core 3.1 pero se requiere sustituir la línea `<TargetFramework>net5.0</TargetFramework>` por `<TargetFramework>netcoreapp3.1</TargetFramework>` en los siguientes archivos: 
   * `Soccer.WebApi.csproj`
   * `Soccer.Application.UnitTests.csproj`
   * `Soccer.Domain.UnitTests.csproj`
   * `Soccer.IntegrationTests.csproj`
   
2. Tener instalado un IDE de desarrollo .NET. 
 
   **NOTA**: Se recomienda la versión más actualizada de Visual Studio 2019 Community Edition, pero también se puede utilizar Rider o Visual Studio Code.

3. Tener instalado el Entity Framework Core CLI como herramienta dotnet 
   ```
   dotnet tool install --global dotnet-ef
   ```
   
4. Tener un servidor de bases de datos SQL Server en ejecución y accesible. Se puede instalar un servidor local SQL Server Express, o LocalDb, o levantar un contenedor con docker, por ejemplo:
   ```
   docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Password12!' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-latest
   ```

5. Opcionalmente se puede tener un cliente UI de base de datos Sql Server como SQL Server Management Studio o [Azure Data Studio](https://docs.microsoft.com/en-us/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver15)

## Instrucciones
1. Descarga (o clona con git) esta solución
2. Compila la solución
3. Ejecuta todos los test automatizados a la vez y observa que todos los tests pasan
4. Lanzar la aplicación. Entrar en http://localhost:5000/swagger y observar que cualquier request HTTP falla
5. Crea las entities que consideres necesarias en el proyecto `Soccer.Infra.Repository.EntityFramework`
6. Ahora implementa la clase `SoccerDbContext` del proyecto `Soccer.Infra.Repository.EntityFramework` para que utilice las entities (o tablas) especificadas
7. Utiliza EF Core CLI para crear una migración que, al ejecutarse, cree automáticamente una base de datos en SQL Server con las tablas especificadas
8. BONUS: Implementa los métodos de la clase `EntityFrameworkRepository` del proyecto `Soccer.Infra.Repository.EntityFramework` para que la aplicación funcione como debe

NOTA: Recuerda que puedes hacer que tu aplicación utilice el `InMemoryGamesRepository` en lugar del `EntityFrameworkRepository` si necesitas recordar el funcionamiento de la aplicación.
Este cambio se realiza siempre que quieras en `DependencyInjectionExtensions` dentro del proyecto `Soccer.WebApi`

## Rúbrica de evaluación
Se deberá completar los 7 primeros pasos para aprobar esta práctica.
A partir de ahí, se valorará la solución aportada y la consecución del BONUS para para lograr aumentar la califación.

**MUY IMPORTANTE**: Es imprescindible que la solución compile, se inicie correctamente en http://localhost:5000/swagger 
y tenga las Migraciones pertinentes autogeneradas para poder evaluar la prueba.

## ANEXO 1 - Cómo utilizar migraciones con EF Core CLI

### Para crear migraciones
A nivel de la solución, abre una línea de comandos y ejecuta lo siguiente
```
dotnet ef migrations add <nombre__migracion> --project src/Soccer.Infra.Repository.EntityFramework --startup-project src/Soccer.WebApi
```

### Para ejecutar migraciones
A nivel de la solución, abre una línea de comandos y ejecuta lo siguiente
```
dotnet ef database update --project src/Soccer.Infra.Repository.EntityFramework --startup-project src/Soccer.WebApi
```

NOTA: Observa que en ambos casos se especifica unos parámetros. Con `--project` se le indica a EF Core CLI dónde encontrar el proyecto que tiene el DbContext, con `--startup-project`
se le indica a EF Core CLI dónde encontrar el proyecto ejecutable de .NET que tiene una dependencia a `Microsoft.EntityFrameworkCore.Design`