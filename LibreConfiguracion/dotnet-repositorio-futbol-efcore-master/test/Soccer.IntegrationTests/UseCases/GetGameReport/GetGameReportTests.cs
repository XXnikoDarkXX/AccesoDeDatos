using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using FluentAssertions;
using Newtonsoft.Json;
using Soccer.Application.Models;
using Soccer.IntegrationTests.TestSupport;
using Soccer.IntegrationTests.TestSupport.Builders;
using Xunit;

namespace Soccer.IntegrationTests.UseCases.GetGameReport
{
    public static class GetGameReportTests
    {
        public class Given_A_NewGame_When_Posting
            : FunctionalTest
        {
            private HttpResponseMessage _result;
            private Guid _id;
            private GameReport _expectedGameReport;

            protected override async Task Given()
            {
                var gamesUrl = "games";
                var jsonStream =
                    new FileStreamBuilder()
                        .WithFileResourceName("get_game_report_new_game.json")
                        .Build();

                var localTeamCode = "RMD";
                var foreignTeamCode = "FCB";
                
                var content =
                    new StringContentBuilder()
                        .WithContent(jsonStream)
                        .WithPlaceholderReplacement("local_team_code", localTeamCode)
                        .WithPlaceholderReplacement("foreign_team_code", foreignTeamCode)
                        .Build();
                
                var resultPost = await HttpClient.PostAsync(gamesUrl, content);
                // ReSharper disable once PossibleNullReferenceException
                var locationUrlParts = resultPost.Headers.Location.ToString().Split('/');
                var idPart = locationUrlParts.Single(x=> Guid.TryParse(x, out _));
                _id = new Guid(idPart);
                
                _expectedGameReport = new GameReport(_id, localTeamCode, 0, foreignTeamCode, 0);
            }

            protected override async Task When()
            {
                _result = await HttpClient.GetAsync($"games/{_id}");
            }
            
            [Fact]
            public void Then_It_Should_Return_200_Ok()
            {
                _result.StatusCode.Should().Be(HttpStatusCode.OK);
            }
            
            [Fact]
            public async Task Then_It_Should_Return_The_Expected_GameReport()
            {
                var content = await _result.Content.ReadAsStringAsync();
                var gameReport = JsonConvert.DeserializeObject<GameReport>(content);
                gameReport.Should().BeEquivalentTo(_expectedGameReport);
            }
        }
    }
}