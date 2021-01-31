using Domain;

namespace Application
{
    public interface IPurchaseOrderRepository
    {
        PurchaseOrder GetPurchaseOrder(int id);
        void Save(PurchaseOrder purchaseOrder);
    }
}