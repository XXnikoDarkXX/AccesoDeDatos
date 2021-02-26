# dotnet-repositorio-futbol

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

## Instrucciones
1. Descarga (o clona con git) esta solución
2. Compila la solución
3. Ejecuta todos los test automatizados a la vez y observa que hay algunos en rojo
4. Sin borrar ni modificar ni un solo test existente (aunque puedes añadir los que consideres oportunos), consigue que todos estén en verde. Para ello haz los cambios necesarios en los proyectos `Soccer.Infra.Repository.InMemory` y `Soccer.Application`
5. BONUS 1: Añade un nuevo *endpoint* HTTP que permita recuperar todos los reportes de partidos que haya en el sistema (i.e: `GET /games` debe devolver un `200 OK` con una colección de objetos `GameReport`)
7. BONUS 2: Añade un nuevo *endpoint* HTTP que permita eliminar un partido (i.g: `DELETE /games/{id}` debería devolver un `200 OK`)
8. BONUS 3: Añade un nuevo *endpoint* HTTP, o modifica el existente en `GET /games/{id}/report` sin romper tests, que permita recuperar reportes de partidos con la información no solo del resultado sino de quiénes y cuándo anotaron los goles

## Rúbrica de evaluación
Se deberá conseguir que todos los test automatizados estén en verde para aprobar el proyecto.
A partir de ahí, se valorará la solución aportada y la consecución de los BONUS para lograr aumentar la califación.

**MUY IMPORTANTE**: Es imprescindible que la solución compile y se inicie correctamente en http://localhost:5000/swagger para poder evaluar la prueba. También es imprescindible no modificar ninguno de los tests existentes, aunque se pueden añadir otros nuevos.