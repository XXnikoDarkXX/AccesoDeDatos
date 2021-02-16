using System;
using Microsoft.AspNetCore.Mvc;
using Soccer.Application.Models;
using Soccer.Application.Services;
using Soccer.Domain;

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
        public IActionResult GetGameReport(Guid id)
        {
            var gameReport = _gamesQueryService.GetGameReport(id);
            return Ok(gameReport);
        }

        [HttpPost]
        public IActionResult AddGame([FromBody] NewGame newGame)
        {
            var id = _gamesCommandService.CreateGame(newGame);
            return CreatedAtAction(nameof(GetGameReport), new { id = id}, newGame);
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
    }
}