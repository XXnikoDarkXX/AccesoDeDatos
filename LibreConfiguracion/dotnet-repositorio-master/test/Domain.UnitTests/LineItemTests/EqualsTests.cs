using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.LineItemTests
{
    public static class EqualsTests
    {
        public class Given_Two_LineItem_With_Same_Values_In_Properties_When_Comparing
            : Given_When_Then_Test
        {
            private LineItem _sut = null!;
            private LineItem _anotherLineItem = null!;
            private bool _result;

            protected override void Given()
            {
                _sut = new LineItem("foo", "bar", 123.456m);
                _anotherLineItem = new LineItem("foo", "bar", 123.456m);
            }

            protected override void When()
            {
                _result = _sut.Equals(_anotherLineItem);
            }

            [Fact]
            public void Then_It_Should_Be_Equal()
            {
                _result.Should().BeTrue();
            }
        }
    }
}