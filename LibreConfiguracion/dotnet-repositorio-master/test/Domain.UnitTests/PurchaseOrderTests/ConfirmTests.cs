using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.PurchaseOrderTests
{
    public static class ConfirmTests
    {
        public class Given_A_PurchaseOrder_And_ShippingInfo_When_Confirming
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);

                var shippingInfo = new ShippingInfo("foo", "bar");
                _sut.AddShippingInfo(shippingInfo);
            }

            protected override void When()
            {
                _sut.Confirm();
            }

            [Fact]
            public void Then_It_Should_Be_Confirmed()
            {
                _sut.IsConfirmed.Should().BeTrue();
            }

            [Fact]
            public void Then_It_Should_Have_A_Valid_ConfirmedOn()
            {
                _sut.ConfirmedOn.Should().NotBeNull();
            }
        }
    }
}