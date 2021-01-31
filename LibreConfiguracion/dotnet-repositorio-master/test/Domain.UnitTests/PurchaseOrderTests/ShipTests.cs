using System;
using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.PurchaseOrderTests
{
    public static class ShipTests
    {
        public class Given_A_Confirmed_PurchaseOrder_With_ShippingInfo_When_Shipping
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);

                var shippingInfo = new ShippingInfo("foo", "bar");
                _sut.AddShippingInfo(shippingInfo);
                
                _sut.Confirm();
            }

            protected override void When()
            {
                _sut.Ship();
            }

            [Fact]
            public void Then_It_Should_Be_Shipped()
            {
                _sut.IsShipped.Should().BeTrue();
            }

            [Fact]
            public void Then_It_Should_Have_A_Valid_ShippedOn()
            {
                _sut.ShippedOn.Should().NotBeNull();
            }
        }

        public class Given_An_Unconfirmed_PurchaseOrder_With_ShippingInfo_When_Shipping
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private Exception _exception = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);

                var shippingInfo = new ShippingInfo("foo", "bar");
                _sut.AddShippingInfo(shippingInfo);
            }

            protected override void When()
            {
                try
                {
                    _sut.Ship();
                }
                catch (Exception exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Not_Be_Shipped()
            {
                _sut.IsShipped.Should().BeFalse();
            }

            [Fact]
            public void Then_It_Should_Throw_An_Exception()
            {
                _exception.Should().NotBeNull();
            }
        }

        public class Given_An_Unconfirmed_PurchaseOrder_Without_ShippingInfo_When_Shipping
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private Exception _exception = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);
            }

            protected override void When()
            {
                try
                {
                    _sut.Ship();
                }
                catch (Exception exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Not_Be_Shipped()
            {
                _sut.IsShipped.Should().BeFalse();
            }

            [Fact]
            public void Then_It_Should_Throw_An_Exception()
            {
                _exception.Should().NotBeNull();
            }
        }

        public class Given_A_Confirmed_PurchaseOrder_With_ShippingInfo_Already_Shipped_When_Shipping
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private Exception _exception = null!;

            protected override void Given()
            {
                _sut = new PurchaseOrder(123);

                var shippingInfo = new ShippingInfo("foo", "bar");
                _sut.AddShippingInfo(shippingInfo);

                _sut.Confirm();
                _sut.Ship();
            }

            protected override void When()
            {
                try
                {
                    _sut.Ship();
                }
                catch (Exception exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Remain_Shipped()
            {
                _sut.IsShipped.Should().BeTrue();
            }

            [Fact]
            public void Then_It_Should_Throw_An_Exception()
            {
                _exception.Should().NotBeNull();
            }
        }
    }
}