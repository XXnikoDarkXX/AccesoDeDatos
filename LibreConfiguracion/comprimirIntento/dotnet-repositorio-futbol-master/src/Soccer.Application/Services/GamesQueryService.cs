using System;
using Soccer.Application.Mappers;
using Soccer.Application.Models;

namespace Soccer.Application.Services
{
    public class GamesQueryService
    {
        private readonly IGamesRepository _gamesRepository;//Representa al repositorio donde almacena los datos
       
        private readonly GameToGameReportMapper _gameToGameReportMapper; //te permite convertir de la clase game a gamereportMapper

        public GamesQueryService(IGamesRepository gamesRepository, 
            GameToGameReportMapper gameToGameReportMapper)
        {
            _gamesRepository = gamesRepository;
            _gameToGameReportMapper = gameToGameReportMapper;
        }
        
        public ReporteGoles GetGameReport(Guid id)
        {
            //  var game = _gamesRepository.GetGame(id);
            //var gameReport = _gameToGameReportMapper.Map(game);
            ReporteGoles reporteGoles = _gamesRepository.GetReporteGoles(id);
            return reporteGoles;
        }
    }
}