using System;
using System.Diagnostics.CodeAnalysis;
using Domain;

namespace Application
{
    [SuppressMessage("ReSharper", "StringLiteralTypo")]
    public class Coordinator
    {
        private readonly IPurchaseOrderRepository _purchaseOrderRepository;

        public Coordinator(IPurchaseOrderRepository purchaseOrderRepository)
        {
            _purchaseOrderRepository = purchaseOrderRepository;
        }

        public void Run(int id)
        {
            var purchaseOrder = _purchaseOrderRepository.GetPurchaseOrder(id);
            if (purchaseOrder is null)
            {
                Console.WriteLine($"No hay ningún purchase order con Id {id}");

                Console.WriteLine($"Generando purchase order con Id {id}");
                purchaseOrder = new PurchaseOrder(id);
                purchaseOrder.AddLineItem(new LineItem("M1", "Jamón", 5.25m));
                purchaseOrder.AddLineItem(new LineItem("M2", "Fuet", 2.99m, 3));
                purchaseOrder.AddLineItem(new LineItem("V1", "Lechuga", 0.95m));
                purchaseOrder.AddShippingInfo(new ShippingInfo("Greg Young", "123 High Street, Seattle, USA"));
                purchaseOrder.Confirm();
                purchaseOrder.Ship();

                _purchaseOrderRepository.Save(purchaseOrder);
                Console.WriteLine("Purchase Order guardada con éxito");

                var retrievedPurchaseOrder = _purchaseOrderRepository.GetPurchaseOrder(id);
                Console.WriteLine($"Purchase Order con Id {id} recuperada");
                Console.WriteLine(retrievedPurchaseOrder);
            }
        }
    }
}
