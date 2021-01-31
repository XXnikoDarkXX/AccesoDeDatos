using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.PurchaseOrderTests
{
    public static class AddShippingInfoTests
    {
        public class Given_A_PurchaseOrder_When_Adding_ShippingInfo
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private ShippingInfo _shippingInfo = null!;

            protected override void Given()
            {
                _shippingInfo = new ShippingInfo("foo", "bar");
                _sut = new PurchaseOrder(123);
            }

            protected override void When()
            {
                _sut.AddShippingInfo(_shippingInfo);
            }

            [Fact]
            public void Then_It_Should_Have_The_Expected_ShippingInfo()
            {
                _sut.ShippingInfo.Should().Be(_shippingInfo);
            }
        }

        public class Given_A_PurchaseOrder_With_ShippingInfo_When_Adding_Another_ShippingInfo
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private ShippingInfo _shippingInfo = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);
                var existingShippingInfo = new ShippingInfo("Joe Bloggs", "High Street 1, NYC");
                _sut.AddShippingInfo(existingShippingInfo);

                _shippingInfo = new ShippingInfo("foo", "bar");
            }

            protected override void When()
            {
                _sut.AddShippingInfo(_shippingInfo);
            }

            [Fact]
            public void Then_It_Should_Have_The_New_ShippingInfo_That_Replaces_Previous_One()
            {
                _sut.ShippingInfo.Should().Be(_shippingInfo);
            }
        }

        // TODO Ejercicio - Añadir otro escenario donde una PurchaseOrder tiene ShippingInfo y se añade la misma ShippingInfo otra vez. El resultado esperado es que se sobreescriba sin error
    }
}