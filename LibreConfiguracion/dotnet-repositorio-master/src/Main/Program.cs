using System;
using System.Diagnostics.CodeAnalysis;
using Application;
using Repository.InMemory;

namespace Main
{
    [SuppressMessage("ReSharper", "StringLiteralTypo")]
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("Ejemplo - Patrón repositorio");
            RunExample();
            Console.WriteLine("Fin del Ejemplo");
        }

        private static void RunExample()
        {
            // Dependency Injection wiring
            IPurchaseOrderRepository purchaseOrderRepository = new InMemoryPurchaseOrderRepository();

            var coordinator = new Coordinator(purchaseOrderRepository);
            coordinator.Run(1);
        }
    }
}
