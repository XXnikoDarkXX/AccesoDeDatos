using Soccer.Application;
using Soccer.Application.Mappers;
using Soccer.Application.Models;
using Soccer.Domain;
using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Soccer.Infra.Repository.InMemory
{
    public class InMemoryGamesRepository : IGamesRepository
    {
        private readonly Dictionary<Guid, Game> baseDatos;
        private readonly Dictionary<Guid,ReporteGoles> baseReporteGoles;
        public InMemoryGamesRepository()
        {
            baseDatos = new Dictionary<Guid, Game>();
            baseReporteGoles = new Dictionary<Guid, ReporteGoles>();
        }

        public IEnumerable<Game> GetGames()
        {
            List<Game> lista = new List<Game>();
            foreach (KeyValuePair<Guid, Game> entry in baseDatos)
            {
                lista.Add(entry.Value);
            }
            return lista;
        }

        public Game GetGame(Guid id)
        {
            return baseDatos[id];
        }

        public void RemoveGame(Guid id)
        {
            baseDatos.Remove(id);
        }

        public void UpdateGame(Guid id, Game game)
        {
        
          
            
           baseDatos[id] = game;//actualzar el valor a traves de la clave
                

        }

        public void AddGame(Game game)
        {

            if (!baseDatos.ContainsKey(game.Id))
                {
               
                baseDatos.Add(game.Id, game);

               

            }
            else
            {
                throw new IdYaExisteException("La id ya existe");
            }
            
        }

        public void AddReporteGoles(Game game,ReporteGoles reporteGoles)
        {
            //
            //GameToGameReportMapper mapeador = new GameToGameReportMapper();
            //GameReport repor = mapeador.Map(game);
           //  reporteGoles = new ReporteGoles(repor);
            baseReporteGoles.Add(game.Id, reporteGoles);

            //
        }


        public ReporteGoles GetReporteGoles(Guid id)
        {
            return baseReporteGoles[id];
        }



    }

    [Serializable]
    internal class JuegoEnPogresoExcepcion : Exception
    {
        public JuegoEnPogresoExcepcion()
        {
        }

        public JuegoEnPogresoExcepcion(string message) : base(message)
        {
        }

        public JuegoEnPogresoExcepcion(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected JuegoEnPogresoExcepcion(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}