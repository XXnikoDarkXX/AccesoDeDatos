using System;
using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Domain.UnitTests.LineItemTests
{
    public static class ConstructorTests
    {
        public class Given_Product_Code_Product_Name_And_Price_When_Instantiating
            : Given_When_Then_Test
        {
            private LineItem _sut = null!;
            private string _productCode = null!;
            private string _productName = null!;
            private decimal _price;

            protected override void Given()
            {
                _productCode = "foo";
                _productName = "bar";
                _price = 123.456m;
            }

            protected override void When()
            {
                _sut = new LineItem(_productCode, _productName, _price);
            }

            [Fact]
            public void Then_It_Should_Have_Created_A_Valid_Instance()
            {
                _sut.Should().NotBeNull();
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_ProductCode()
            {
                _sut.ProductCode.Should().Be(_productCode);
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_ProductName()
            {
                _sut.ProductName.Should().Be(_productName);
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_Price()
            {
                _sut.Price.Should().Be(_price);
            }

            [Fact]
            public void Then_It_Should_Have_One_Quantity_By_Default()
            {
                _sut.Quantity.Should().Be(1);
            }
        }

        public class Given_Product_Code_Product_Name_Price_And_Quantity_When_Instantiating
            : Given_When_Then_Test
        {
            private LineItem _sut = null!;
            private string _productCode = null!;
            private string _productName = null!;
            private decimal _price;
            private int _quantity;

            protected override void Given()
            {
                _productCode = "foo";
                _productName = "bar";
                _price = 123.456m;
                _quantity = 12;
            }

            protected override void When()
            {
                _sut = new LineItem(_productCode, _productName, _price, _quantity);
            }

            [Fact]
            public void Then_It_Should_Have_Created_A_Valid_Instance()
            {
                _sut.Should().NotBeNull();
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_ProductCode()
            {
                _sut.ProductCode.Should().Be(_productCode);
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_ProductName()
            {
                _sut.ProductName.Should().Be(_productName);
            }

            [Fact]
            public void Then_It_Should_Have_The_Correct_Price()
            {
                _sut.Price.Should().Be(_price);
            }

            [Fact]
            public void Then_It_Should_Have_The_Expected_Quantity()
            {
                _sut.Quantity.Should().Be(_quantity);
            }
        }

        public class Given_Null_ProductCode_When_Constructing_Instance
            : Given_When_Then_Test
        {
            private string _productCode = null!;
            private string _productName = null!;
            private decimal _price;
            private ArgumentNullException _exception = null!;

            protected override void Given()
            {
                _productCode = null!;
                _productName = "bar";
                _price = 123.456m;
            }

            protected override void When()
            {
                try
                {
                    _ = new LineItem(_productCode, _productName, _price);
                }
                catch (ArgumentNullException exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Throw_An_ArgumentNullException()
            {
                _exception.Should().NotBeNull();
            }
        }

        public class Given_Null_ProductName_When_Constructing_Instance
            : Given_When_Then_Test
        {
            private string _productCode = null!;
            private string _productName = null!;
            private decimal _price;
            private ArgumentNullException _exception = null!;

            protected override void Given()
            {
                _productCode = "foo";
                _productName = null!;
                _price = 123.456m;
            }

            protected override void When()
            {
                try
                {
                    _ = new LineItem(_productCode, _productName, _price);
                }
                catch (ArgumentNullException exception)
                {
                    _exception = exception;
                }
            }

            [Fact]
            public void Then_It_Should_Throw_An_ArgumentNullException()
            {
                _exception.Should().NotBeNull();
            }
        }
    }
}