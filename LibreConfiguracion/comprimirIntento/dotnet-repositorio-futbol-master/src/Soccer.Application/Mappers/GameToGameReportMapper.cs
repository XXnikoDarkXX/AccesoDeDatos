using System;
using Soccer.Application.Models;
using Soccer.Domain;

namespace Soccer.Application.Mappers
{
    public class GameToGameReportMapper
    {
        /**
         * Funcion donde le pasamos un game y le devuelve un GameReport
        */
        public GameReport Map(Game game)
        {
          
        GameReport informe = new GameReport(game.Id,game.LocalTeamCode,game.LocalGoals.Count,game.ForeignTeamCode,game.ForeignGoals.Count);
           
            return informe;

        }
    }
}