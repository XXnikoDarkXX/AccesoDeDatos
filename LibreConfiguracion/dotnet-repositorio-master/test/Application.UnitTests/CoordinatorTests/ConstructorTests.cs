using FluentAssertions;
using Moq;
using Sasw.TestSupport;
using Xunit;

namespace Application.UnitTests.CoordinatorTests
{
    public static class ConstructorTests
    {
        public class Given_Valid_Dependencies_When_Constructing_Instance
            : Given_When_Then_Test
        {
            private Coordinator _sut = null!;
            private IPurchaseOrderRepository _purchaseRepository = null!;

            protected override void Given()
            {
                _purchaseRepository = Mock.Of<IPurchaseOrderRepository>();
            }

            protected override void When()
            {
                _sut = new Coordinator(_purchaseRepository);
            }

            [Fact]
            public void Then_It_Should_Have_Created_A_Valid_Instance()
            {
                _sut.Should().NotBeNull();
            }
        }
    }
}