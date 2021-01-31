using System.Collections.Generic;
using Application;
using Domain;

namespace Repository.InMemory
{
    // This class must be singleton
    public class InMemoryPurchaseOrderRepository
        : IPurchaseOrderRepository
    {
        private readonly IDictionary<int, PurchaseOrder> _values = new Dictionary<int, PurchaseOrder>();

        public PurchaseOrder GetPurchaseOrder(int id)
        {
            var isExisting = _values.TryGetValue(id, out var value);
            if (isExisting)
            {
                return value;
            }

            return null;
        }

        public void Save(PurchaseOrder purchaseOrder)
        {
            _values[purchaseOrder.Id] = purchaseOrder;
        }
    }
}
