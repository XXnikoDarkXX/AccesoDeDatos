using System;
using System.Collections.Generic;
using Soccer.Application;
using Soccer.Domain;

namespace Soccer.Infra.Repository.EntityFramework
{
    public class EntityFrameworkRepository
        : IGamesRepository
    {
        private readonly SoccerContext _soccerContext;

        public EntityFrameworkRepository(SoccerContext soccerContext)
        {
            _soccerContext = soccerContext;
        }

        public IEnumerable<Game> GetGames()
        {
         
            throw new NotImplementedException();
        }

        public Game GetGame(Guid id)
        {
          

            throw new NotImplementedException();
        }

        public void AddGame(Game game)
        {
            throw new NotImplementedException();
        }

        public void RemoveGame(Guid id)
        {
            throw new NotImplementedException();
        }

        public void UpdateGame(Guid id, Game game)
        {
            throw new NotImplementedException();
        }
    }
}