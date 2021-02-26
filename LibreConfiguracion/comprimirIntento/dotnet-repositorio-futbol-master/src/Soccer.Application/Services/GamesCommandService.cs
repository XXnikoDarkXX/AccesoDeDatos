using System;
using System.Collections.Generic;
using Soccer.Application.Exceptions;
using Soccer.Application.Mappers;
using Soccer.Application.Models;
using Soccer.Domain;

namespace Soccer.Application.Services
{
    public class GamesCommandService
    {
        private readonly IGamesRepository _gamesRepository;//objeto del repositorio
        private readonly IDateTimeService _dateTimeService;//para las fechas

        public GamesCommandService(
            IGamesRepository gamesRepository,
            IDateTimeService dateTimeService)
        {
            _gamesRepository = gamesRepository;
            _dateTimeService = dateTimeService;
        }

        public Guid CreateGame(NewGame newGame)
        {
            var newId = Guid.NewGuid();//creamos una clave unica
            var game = new Game(newId);//creamos un nuevo game mediante newId

            game.AddLocalTeam(newGame.LocalTeamCode);//añadimos al game el nombre 
            game.AddForeignTeam(newGame.ForeignTeamCode);//añadimos al game el nombre del adversario

            _gamesRepository.AddGame(game);//añadimos el juego a la base de datos

            return newId;//nos de vuelve
        }

        public void SetProgress(Guid id, GameProgress gameProgress)//me actualiza solo el progreso del partido
        {
            var game = _gamesRepository.GetGame(id);//extraemos el partido mediante el repositorio
            var currentDate = _dateTimeService.GetUtcNow();//sirve para establecer la fecha actual
            if (gameProgress.IsInProgress)
            {
                game.Start(currentDate);
            }
            else
            {
                game.End(currentDate);
            }

            _gamesRepository.UpdateGame(id, game);//actualizamos el partido de la variable privada
        }

        public void AddGoal(Guid id, NewGoal newGoal)
        {
            var game = _gamesRepository.GetGame(id);//obtenemos el partido mediante la id

            
            GameToGameReportMapper mapeador = new GameToGameReportMapper();
            GameReport report = mapeador.Map(game);
            ReporteGoles reporteGoles = new ReporteGoles(report);
            
            


            var currentDate = _dateTimeService.GetUtcNow();//establecemos la fecha actual del partido

            var teamCode = newGoal.TeamCode;//obtenemos el nombre del equipo

            var goal = new Goal(currentDate, newGoal.ScoredBy);//creamos un goal con la fecha actual del gol
            //, y scoreby quien marco (nombre del jugador)

            var isTeamPlaying = game.LocalTeamCode == teamCode || game.ForeignTeamCode == teamCode;
            
            
            if (!isTeamPlaying)//si no esta jugando
            {
                throw new ResourceNotFoundException($"The team code {teamCode} is not playing the game");
            }

            if (game.LocalTeamCode == teamCode)//si el juego el quipoLocal  es igual al teamcode 
            {
                game.AddLocalTeamGoal(goal);//agregamos al juego los goal que han marcado si pertenece a ese equipo
                                            //
                reporteGoles.añadirGoles(newGoal, goal);
                
            }
            else
            {
                game.AddForeignTeamGoal(goal);//se lo metemos al equipo adversario
                
                reporteGoles.añadirGoles(newGoal,goal);
                
            }

            _gamesRepository.UpdateGame(id, game);//actualizamos la base de datos con la id y game

            //actualizamos el reporte de la clase pivote

             mapeador = new GameToGameReportMapper();
      Game    juegoFinal= _gamesRepository.GetGame(id);
          GameReport     reporteFinal = mapeador.Map(juegoFinal);
           reporteGoles = new ReporteGoles(report);

            _gamesRepository.AddReporteGoles(juegoFinal, reporteGoles);
            //
        }

        /**
         * Metodo que devuelve una con todos los reportr de la base de datos
         * Creamos una lista de tipo Game y almacenamos todos los game de la bbdd
         * 
         * Por ultimo creamos una lista de GameReport y con el mapeador le añadimos los game
        */
        public List<GameReport> mostrarReportes()
        {

            List<Game> lista = new List<Game>();
            lista= (List<Game>)_gamesRepository.GetGames();//lista de game

            List<GameReport> listaReporte = new List<GameReport>();//lista de reportes que vamos a devolver
            GameToGameReportMapper mapeador = new GameToGameReportMapper();//mapeador
            for (int i = 0; i < lista.Count; i++)
            {
           //usando el mapeador le cargarmos a la lista de reporte un nuevo GameReport de la lista de Games
                listaReporte.Add(mapeador.Map(lista[i]));

                
            }
          
                return listaReporte;
        }


        public void borrarJuego(Guid id)
        {
            Game juego = _gamesRepository.GetGame(id);

                if (juego != null) { 
            _gamesRepository.RemoveGame(id);
            }
            else
            {
                throw new ResourceNotFoundException("la id que intentas borrar no existe");
            }

        }
    }
}