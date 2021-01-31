using Domain;
using FluentAssertions;
using Sasw.TestSupport;
using Xunit;

namespace Repository.InMemory.UnitTests.InMemoryPurchaseOrderRepositoryTests
{
    public static class GetTests
    {
        public class Given_A_PurchaseOrder_Stored_When_Getting_It
            : Given_When_Then_Test
        {
            private int _id;
            private InMemoryPurchaseOrderRepository _sut;
            private PurchaseOrder _result = null!;
            private PurchaseOrder _purchaseOrder = null!;

            protected override void Given()
            {
                _id = 123;
                _purchaseOrder = new PurchaseOrder(_id);
                _sut = new InMemoryPurchaseOrderRepository();
                _sut.Save(_purchaseOrder);
            }

            protected override void When()
            {
                _result = _sut.GetPurchaseOrder(_id);
            }

            [Fact]
            public void Then_It_Should_Be_The_Expected_PurchaseOrder()
            {
                _result.Should().Be(_purchaseOrder);
            }
        }

        public class Given_Many_PurchaseOrder_Stored_When_Getting_One
            : Given_When_Then_Test
        {
            private int _id;
            private InMemoryPurchaseOrderRepository _sut;
            private PurchaseOrder _result = null!;
            private PurchaseOrder _purchaseOrder = null!;

            protected override void Given()
            {
                _id = 123;
                _purchaseOrder = new PurchaseOrder(_id);
                var anotherPurchaseOrder = new PurchaseOrder(_id + 1);
                var lastPurchaseOrder = new PurchaseOrder(_id + 2);
                _sut = new InMemoryPurchaseOrderRepository();
                _sut.Save(_purchaseOrder);
                _sut.Save(anotherPurchaseOrder);
                _sut.Save(lastPurchaseOrder);
            }

            protected override void When()
            {
                _result = _sut.GetPurchaseOrder(_id);
            }

            [Fact]
            public void Then_It_Should_Be_The_Expected_PurchaseOrder()
            {
                _result.Should().Be(_purchaseOrder);
            }
        }
    }
}