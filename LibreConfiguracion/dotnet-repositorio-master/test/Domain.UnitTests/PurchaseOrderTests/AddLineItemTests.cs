using System;
using System.Linq;
using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.PurchaseOrderTests
{
    public static class AddLineItemTests
    {
        public class Given_A_PurchaseOrder_When_Adding_LineItem
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private LineItem _lineItem = null!;

            protected override void Given()
            {
                _lineItem = new LineItem("foo", "bar", 123.456m);
                _sut = new PurchaseOrder(123);
            }

            protected override void When()
            {
                _sut.AddLineItem(_lineItem);
            }

            [Fact]
            public void Then_It_Should_Have_One_LineItem()
            {
                _sut.LineItems.Should().HaveCount(1);
            }

            [Fact]
            public void Then_It_Should_Have_The_Expected_LineItem()
            {
                _sut.LineItems.Single().Should().Be(_lineItem);
            }
        }

        public class Given_A_PurchaseOrder_With_One_LineItem_When_Adding_Another_LineItem_With_Same_Values
            : Given_When_Then_Test
        {
            private PurchaseOrder _sut = null!;
            private LineItem _lineItem = null!;
            private ArgumentException _exception = null!;

            protected override void Given()
            {
                var existingLineItem = new LineItem("foo", "bar", 123.456m);
                
                _sut = new PurchaseOrder(123);
                _sut.AddLineItem(existingLineItem);

                _lineItem = new LineItem("foo", "bar", 123.456m);
            }

            protected override void When()
            {
                try
                {
                    _sut.AddLineItem(_lineItem);
                }
                catch (ArgumentException exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Throw_An_ArgumentException()
            {
                _exception.Should().NotBeNull();
            }

            [Fact]
            public void Then_It_Should_Still_Have_One_LineItem()
            {
                _sut.LineItems.Should().HaveCount(1);
            }
        }
    }
}