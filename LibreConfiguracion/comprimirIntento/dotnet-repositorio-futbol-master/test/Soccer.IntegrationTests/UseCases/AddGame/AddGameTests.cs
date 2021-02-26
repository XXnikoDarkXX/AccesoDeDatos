using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using FluentAssertions;
using Soccer.IntegrationTests.TestSupport;
using Soccer.IntegrationTests.TestSupport.Builders;
using Xunit;

namespace Soccer.IntegrationTests.UseCases.AddGame
{
    public static class AddGameTests
    {
        public class Given_A_NewGame_When_Posting : FunctionalTest
        {
            private string _url;//direccion
            private HttpResponseMessage _result;//respuesta 
            private StringContent _content;//contenido

            protected override Task Given()
            {
                _url = "games";//a url le ponemos "games"
                var jsonStream =new FileStreamBuilder()
                        .WithFileResourceName("add_game_new_game.json")
                        .Build();//guardamos en una variables un json
                _content =
                    new StringContentBuilder()
                        .WithContent(jsonStream)
                        .Build();//a content le añadimos el contenido de la variable json
                
                return Task.CompletedTask;
            }

            protected override async Task When()
            {
                _result = await HttpClient.PostAsync(_url, _content);//a result le cargamos el  contenido de la url
            }
            
            [Fact]
            public void Then_It_Should_Return_201_Created()
            {
                _result.StatusCode.Should().Be(HttpStatusCode.Created);
            }

            [Fact]
            public void Then_It_Should_Return_A_Location_Header()
            {
                _result.Headers.Location.Should().NotBeNull();
            }
        }
    }
}