using System;
using System.Collections.Generic;
using System.Linq;
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
        
        public GameReport GetGameReport(Guid id, bool isDetailed = false)
        {
            var game = _gamesRepository.GetGame(id);
            var gameReport = _gameToGameReportMapper.Map(game, isDetailed);
            return gameReport;
        }
        
        public IEnumerable<GameReport> GetGameReports()
        {
            var games = _gamesRepository.GetGames();
            var gameReports = games.Select(x => _gameToGameReportMapper.Map(x));
            return gameReports;
        }

        public void DeleteGame(Guid id)
        {
            _gamesRepository.RemoveGame(id);
        }
    }
}