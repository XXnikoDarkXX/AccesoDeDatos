using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Repository.InMemory.UnitTests.InMemoryPurchaseOrderRepositoryTests
{
    public static class ConstructorTests
    {
        public class Given_Valid_Dependencies_When_Constructing_Instance
            : Given_When_Then_Test
        {
            private InMemoryPurchaseOrderRepository _sut;

            protected override void Given()
            {
            }

            protected override void When()
            {
                _sut = new InMemoryPurchaseOrderRepository();
            }

            [Fact]
            public void Then_It_Should_Have_Created_A_Valid_Instance()
            {
                _sut.Should().NotBeNull();
            }
        }
    }
}