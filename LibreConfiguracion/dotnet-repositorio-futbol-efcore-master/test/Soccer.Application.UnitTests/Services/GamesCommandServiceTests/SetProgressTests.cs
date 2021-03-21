using System;
using Moq;
using Sasw.TestSupport;
using Soccer.Application.Models;
using Soccer.Application.Services;
using Soccer.Domain;
using Xunit;

namespace Soccer.Application.UnitTests.Services.GamesCommandServiceTests
{
    public static class SetProgressTests
    {
        public class Given_A_Non_Started_Game_When_Setting_To_InProgress
            : Given_When_Then_Test
        {
            private GamesCommandService _sut;
            private Mock<IGamesRepository> _gamesRepositoryMock;
            private GameProgress _gameProgress;
            private Guid _id;

            protected override void Given()
            {
                _id = Guid.Empty;
                var game = new Game(_id);
                game.AddLocalTeam("RMD");
                game.AddForeignTeam("FCB");
                
                _gamesRepositoryMock = new Mock<IGamesRepository>();
                _gamesRepositoryMock
                    .Setup(x => x.GetGame(_id))
                    .Returns(game);
                var gamesRepository = _gamesRepositoryMock.Object;
                
                var dateTimeService = Mock.Of<IDateTimeService>();
                
                _sut = new GamesCommandService(gamesRepository, dateTimeService);
                
                _gameProgress =
                    new GameProgress
                    {
                        IsInProgress = true
                    };
            }

            protected override void When()
            {
                _sut.SetProgress(_id, _gameProgress);
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
                    It.Is<Game>(game => game.IsInProgress)));
            }
        }
        
        public class Given_A_Started_Game_When_Setting_Progress_To_Not_InProgress
            : Given_When_Then_Test
        {
            private GamesCommandService _sut;
            private Mock<IGamesRepository> _gamesRepositoryMock;
            private GameProgress _gameProgress;
            private Guid _id;

            protected override void Given()
            {
                _id = Guid.Empty;
                var game = new Game(_id);
                game.AddLocalTeam("RMD");
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
                
                _gameProgress =
                    new GameProgress
                    {
                        IsInProgress = false
                    };
            }

            protected override void When()
            {
                _sut.SetProgress(_id, _gameProgress);
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
                    It.Is<Game>(game => !game.IsInProgress)));
            }
        }
    }
}