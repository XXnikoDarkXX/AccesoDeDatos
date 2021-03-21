using System;
using System.Collections.Generic;
using Soccer.Application;
using Soccer.Domain;

namespace Soccer.Infra.Repository.InMemory
{
    public class InMemoryGamesRepository
        : IGamesRepository
    {
        private readonly IDictionary<Guid, Game> _games = new Dictionary<Guid, Game>();
        public IEnumerable<Game> GetGames()
        {
            return _games.Values;
        }

        public Game GetGame(Guid id)
        {
            return _games[id];
        }

        public void AddGame(Game game)
        {
            _games.Add(game.Id, game);
        }

        public void RemoveGame(Guid id)
        {
            if (_games.ContainsKey(id))
            {
                _games.Remove(id);
            }
        }

        public void UpdateGame(Guid id, Game game)
        {
            _games[id] = game;
        }
    }
}