using Domain;
using Moq;
using Sasw.TestSupport;
using Xunit;

namespace Application.UnitTests.CoordinatorTests
{
    public static class RunTests
    {
        public class Given_A_Coordinator_When_Running
            : Given_When_Then_Test
        {
            private Coordinator _sut = null!;
            private Mock<IPurchaseOrderRepository> _purchaseOrderRepositoryMock = null!;
            private int _id;

            protected override void Given()
            {
                _id = 123;
                _purchaseOrderRepositoryMock = new Mock<IPurchaseOrderRepository>();
                _sut = new Coordinator(_purchaseOrderRepositoryMock.Object);
            }

            protected override void When()
            {
                _sut.Run(_id);
            }

            [Fact]
            public void Then_It_Should_Get_The_PurchaseOrder_For_Given_Id()
            {
                _purchaseOrderRepositoryMock.Verify(x => x.GetPurchaseOrder(_id));
            }

            [Fact]
            public void Then_It_Should_Save_A_PurchaseOrder()
            {
                _purchaseOrderRepositoryMock.Verify(x => x.Save(It.IsAny<PurchaseOrder>()));
            }
        }
    }
}