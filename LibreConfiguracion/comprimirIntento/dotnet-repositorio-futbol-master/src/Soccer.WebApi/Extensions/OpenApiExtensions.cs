using System.IO;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.OpenApi.Models;

namespace Soccer.WebApi.Extensions
{
    public static class OpenApiExtensions
    {
        public static IServiceCollection AddOpenApi(this IServiceCollection services)
        {
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo
                {
                    Title = "Soccer.WebApi", 
                    Version = "v1",
                    Description = "Proyecto de la asignatura Acceso a Datos de CENEC 2020-2021",
                    Contact = new OpenApiContact
                    {
                        Name = "Diego Martin",
                        Email = "diego.martin@cenecmalaga.es"
                    }
                });
                
                var xmlCommentsWebApi = Path.Combine(System.AppContext.BaseDirectory, "Soccer.WebApi.xml");
                c.IncludeXmlComments(xmlCommentsWebApi);
                var xmlCommentsApplication = Path.Combine(System.AppContext.BaseDirectory, "Soccer.Application.xml");
                c.IncludeXmlComments(xmlCommentsApplication);
            });
            
            return services;
        }

        public static void UseOpenApi(this IApplicationBuilder app)
        {
            app.UseSwagger();
            app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "Soccer.WebApi v1"));
        }
    }
}