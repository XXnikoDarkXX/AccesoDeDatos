using System;
using System.Collections.Generic;
using Soccer.Application;
using Soccer.Domain;

namespace Soccer.Infra.Repository.InMemory
{
    public class InMemoryGamesRepository
        : IGamesRepository
    {
        public IEnumerable<Game> GetGames()
        {
            throw new NotImplementedException("Se necesita devolver todos los Game guardados");
        }
        
        public Game GetGame(Guid id)
        {
            throw new NotImplementedException("Se necesita devolver el Game con el id indicado");
        }
        
        public void RemoveGame(Guid id)
        {
            throw new NotImplementedException("Se necesita eliminar el Game con el id indicado");
        }
        
        public void UpdateGame(Guid id, Game game)
        {
            throw new NotImplementedException("Se necesita sustituir el Game existente con id indicado por el nuevo Game indicado");
        }
        
        public void AddGame(Game game)
        {
            throw new NotImplementedException("Se necesita añadir un Game nuevo");
        }
    }
}