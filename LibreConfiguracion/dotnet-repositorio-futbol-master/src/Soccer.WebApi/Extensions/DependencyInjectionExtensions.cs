using Microsoft.Extensions.DependencyInjection;
using Soccer.Application;
using Soccer.Application.Mappers;
using Soccer.Application.Services;
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
            
            // Registro del repositorio a utilizar
            services.AddSingleton<IGamesRepository, InMemoryGamesRepository>();
            
            return services;
        }
    }
}