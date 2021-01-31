using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.PurchaseOrderTests
{
    public static class ConstructorTests
    {
        public class Given_Valid_Dependencies_When_Constructing_Instance
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private int _id;

            protected override void Given()
            {
                _id = 123;
            }

            protected override void When()
            {
                _sut = new PurchaseOrder(_id);
            }

            [Fact]
            public void Then_It_Should_Have_Created_A_Valid_Instance()
            {
                _sut.Should().NotBeNull();
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_Id()
            {
                _sut.Id.Should().Be(_id);
            }

            [Fact]
            public void Then_It_Should_Not_Have_Any_Line_Items()
            {
                _sut.LineItems.Should().BeEmpty();
            }

            [Fact]
            public void Then_It_Should_Not_Have_Any_Shipping_Info()
            {
                _sut.ShippingInfo.Should().BeNull();
            }

            [Fact]
            public void Then_It_Should_Not_Be_Confirmed()
            {
                _sut.IsConfirmed.Should().BeFalse();
            }

            [Fact]
            public void Then_It_Should_Not_Be_Shipped()
            {
                _sut.IsShipped.Should().BeFalse();
            }
        }
    }
}