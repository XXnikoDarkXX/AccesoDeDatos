using System;
using System.Linq;
using Moq;
using Sasw.TestSupport;
using Soccer.Application.Models;
using Soccer.Application.Services;
using Soccer.Domain;
using Xunit;

namespace Soccer.Application.UnitTests.Services.GamesCommandServiceTests
{
    public static class AddGoalTests
    {
        public class Given_A_Started_Game_When_Adding_Local_Team_Goal
            : Given_When_Then_Test
        {
            private GamesCommandService _sut;
            private Mock<IGamesRepository> _gamesRepositoryMock;
            private Guid _id;
            private NewGoal _newGoal;
            private string _scorer;

            protected override void Given()
            {
                _id = Guid.Empty;
                var game = new Game(_id);
                var localTeamCode = "RMD";
                game.AddLocalTeam(localTeamCode);
                game.AddForeignTeam("FCB");
                var startedOn = new DateTime(2021,02,01); 
                game.Start(startedOn);
                
                _gamesRepositoryMock = new Mock<IGamesRepository>();
                _gamesRepositoryMock
                    .Setup(x => x.GetGame(_id))
                    .Returns(game);
                var gamesRepository = _gamesRepositoryMock.Object;
                
                var dateTimeServiceMock = new Mock<IDateTimeService>();
                dateTimeServiceMock
                    .Setup(x => x.GetUtcNow())
                    .Returns(startedOn.AddHours(2));
                var dateTimeService = dateTimeServiceMock.Object;
                
                _sut = new GamesCommandService(gamesRepository, dateTimeService);

                _scorer = "Cristiano Ronaldo";
                _newGoal =
                    new NewGoal
                    {
                        ScoredBy = _scorer,
                        TeamCode = localTeamCode
                    };
            }

            protected override void When()
            {
                _sut.AddGoal(_id, _newGoal);
            }

            [Fact]
            public void Then_It_Should_Use_The_GamesRepository_To_Retrieve_The_Game()
            {
                _gamesRepositoryMock.Verify(x => x.GetGame(_id));
            }
            
            [Fact]
            public void Then_It_Should_Use_The_GamesRepository_To_Update_The_Game()
            {
                _gamesRepositoryMock.Verify(x => x.UpdateGame(_id, 
                    It.Is<Game>(
                        game => 
                            game.IsInProgress 
                            && game.LocalGoals.Single().ScoredBy == _scorer
                            && game.ForeignGoals.Count == 0)));
            }
        }
        
        public class Given_A_Started_Game_When_Adding_Foreign_Team_Goal
            : Given_When_Then_Test
        {
            private GamesCommandService _sut;
            private Mock<IGamesRepository> _gamesRepositoryMock;
            private Guid _id;
            private NewGoal _newGoal;
            private string _scorer;

            protected override void Given()
            {
                _id = Guid.Empty;
                var game = new Game(_id);
                var foreignTeamCode = "FCB";
                game.AddLocalTeam("RMD");
                game.AddForeignTeam(foreignTeamCode);
                var startedOn = new DateTime(2021,02,01); 
                game.Start(startedOn);
                
                _gamesRepositoryMock = new Mock<IGamesRepository>();
                _gamesRepositoryMock
                    .Setup(x => x.GetGame(_id))
                    .Returns(game);
                var gamesRepository = _gamesRepositoryMock.Object;
                
                var dateTimeServiceMock = new Mock<IDateTimeService>();
                dateTimeServiceMock
                    .Setup(x => x.GetUtcNow())
                    .Returns(startedOn.AddHours(2));
                var dateTimeService = dateTimeServiceMock.Object;
                
                _sut = new GamesCommandService(gamesRepository, dateTimeService);

                _scorer = "Leo Messi";
                _newGoal =
                    new NewGoal
                    {
                        ScoredBy = _scorer,
                        TeamCode = foreignTeamCode
                    };
            }

            protected override void When()
            {
                _sut.AddGoal(_id, _newGoal);
            }

            [Fact]
            public void Then_It_Should_Use_The_GamesRepository_To_Retrieve_The_Game()
            {
                _gamesRepositoryMock.Verify(x => x.GetGame(_id));
            }
            
            [Fact]
            public void Then_It_Should_Use_The_GamesRepository_To_Update_The_Game()
            {
                _gamesRepositoryMock.Verify(x => x.UpdateGame(_id, 
                    It.Is<Game>(
                        game => 
                            game.IsInProgress 
                            && game.ForeignGoals.Single().ScoredBy == _scorer
                            && game.LocalGoals.Count == 0)));
            }
        }
    }
}