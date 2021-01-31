using System;
using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Main.IntegrationTests
{
    public static class MainTests
    {
        public class Given_A_Console_Program_When_Running
            : Given_When_Then_Test
        {
            private Exception _exception = null!;

            protected override void Given()
            {
                
            }

            protected override void When()
            {
                try
                {
                    Program.Main(null!);
                }
                catch (Exception exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Not_Throw_Any_Exception()
            {
                _exception.Should().BeNull();
            }
        }
    }
}