using Microsoft.AspNetCore.Mvc;
using Soccer.Application.Models;
using Soccer.Application.Services;
using System;
using System.Collections.Generic;

namespace Soccer.WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class GamesController
        : ControllerBase
    {
        private readonly GamesCommandService _gamesCommandService;
        private readonly GamesQueryService _gamesQueryService;

        public GamesController(
            GamesCommandService gamesCommandService,
            GamesQueryService gamesQueryService)
        {
            _gamesCommandService = gamesCommandService;
            _gamesQueryService = gamesQueryService;
        }

        /// <param name="id" example="00000000-0000-0000-0000-000000000000">The game id</param>
        [HttpGet("{id}")]
        public IActionResult GetGameReport(Guid id)//funcion para obtener reporter
        {
           // var gameReport = _gamesQueryService.GetGameReport(id);
           var ReporteGoles = _gamesQueryService.GetGameReport(id);
            return Ok(ReporteGoles);
        }

       [HttpPost]
        public IActionResult AddGame([FromBody] NewGame newGame)
        {
            var id = _gamesCommandService.CreateGame(newGame);
            return CreatedAtAction(nameof(GetGameReport), new { id = id }, newGame);
        }

        /// <param name="id" example="00000000-0000-0000-0000-000000000000">The game id</param>
        /// <param name="gameProgress">The patch game object containing the isInProgress property</param>
        [HttpPatch("{id}")]
        public IActionResult StartGame(Guid id, [FromBody] GameProgress gameProgress)
        {
            _gamesCommandService.SetProgress(id, gameProgress);
            return Ok();
        }

        /// <param name="id" example="00000000-0000-0000-0000-000000000000">The game id</param>
        /// <param name="newGoal">The new goal containing the team that scores and the scorer</param>
        [HttpPost("{id}/goals")]
        public IActionResult AddGoal(Guid id, [FromBody] NewGoal newGoal)
        {
            _gamesCommandService.AddGoal(id, newGoal);
            return Ok();
        }

      
        [HttpGet("getAllReport")]
        public IActionResult allGamesReport()
        {
            List<GameReport> listaReporte = new List<GameReport>();
          listaReporte=  _gamesCommandService.mostrarReportes();
            return Ok(listaReporte);
        }


        [HttpDelete("{id}/Games")]

        public IActionResult deleteGame(Guid id)
        {

            _gamesCommandService.borrarJuego(id);
            return Ok();
        }


    }
}