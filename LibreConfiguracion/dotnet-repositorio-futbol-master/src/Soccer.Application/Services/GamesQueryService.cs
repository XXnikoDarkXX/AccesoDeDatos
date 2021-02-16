using System;
using Soccer.Application.Mappers;
using Soccer.Application.Models;

namespace Soccer.Application.Services
{
    public class GamesQueryService
    {
        private readonly IGamesRepository _gamesRepository;
        private readonly GameToGameReportMapper _gameToGameReportMapper;

        public GamesQueryService(
            IGamesRepository gamesRepository, 
            GameToGameReportMapper gameToGameReportMapper)
        {
            _gamesRepository = gamesRepository;
            _gameToGameReportMapper = gameToGameReportMapper;
        }
        
        public GameReport GetGameReport(Guid id)
        {
            var game = _gamesRepository.GetGame(id);
            var gameReport = _gameToGameReportMapper.Map(game);
            return gameReport;
        }
    }
}