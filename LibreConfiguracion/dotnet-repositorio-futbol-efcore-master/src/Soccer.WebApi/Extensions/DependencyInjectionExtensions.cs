using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Soccer.Application;
using Soccer.Application.Mappers;
using Soccer.Application.Services;
using Soccer.Infra.Repository.EntityFramework;
using Soccer.Infra.Repository.InMemory;

namespace Soccer.WebApi.Extensions
{
    public static class DependencyInjectionExtensions
    {
        public static IServiceCollection AddSoccerDependencies(this IServiceCollection services)
        {
            services.AddSingleton<IDateTimeService, DateTimeService>();
            services.AddTransient<GamesCommandService>();
            services.AddTransient<GamesQueryService>();
            services.AddSingleton<GameToGameReportMapper>();
            
            // Registro de Entity Framework con SqlServer
            const string connectionString = "Server=localhost;Database=Soccer;user=sa;password=Password12!";
            services.AddDbContext<SoccerContext>(options =>
                options.UseSqlServer(connectionString));
            
            // Registro del repositorio a utilizar
            services.AddSingleton<IGamesRepository, InMemoryGamesRepository>();
           // services.AddTransient<IGamesRepository, EntityFrameworkRepository>();
            
            return services;
        }
    }
}