using System;
using System.Collections.Generic;
using Soccer.Application.Mappers;
using Soccer.Domain;

namespace Soccer.Application
{
    public interface IGamesRepository
    {
        IEnumerable<Game> GetGames();
        Game GetGame(Guid id);
        void AddReporteGoles(Game Game, ReporteGoles reporteGoles);
        ReporteGoles GetReporteGoles(Guid id);
        void AddGame(Game game);
        void RemoveGame(Guid id);
        void UpdateGame(Guid id, Game game);
    }
}