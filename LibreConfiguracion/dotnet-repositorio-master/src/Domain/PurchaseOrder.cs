using System;
using System.Collections.Generic;
using System.Text;

namespace Domain
{
    public class PurchaseOrder
    {
        public int Id { get; }
        public ShippingInfo ShippingInfo { get; private set; }
        public IList<LineItem> LineItems { get; }
        public DateTime? ShippedOn { get; private set; }
        public DateTime? ConfirmedOn { get; private set; }
        public bool IsShipped => ShippedOn.HasValue;
        public bool IsConfirmed => ConfirmedOn.HasValue;

        public PurchaseOrder(int id)
        {
            Id = id;
            ShippingInfo = null!;
            LineItems = new List<LineItem>();
            ShippedOn = null;
            ConfirmedOn = null;
        }

        public void AddLineItem(LineItem lineItem)
        {
            if (LineItems.Contains(lineItem))
            {
                throw new ArgumentException("Line item already added");
            }

            LineItems.Add(lineItem);
        }

        public void AddShippingInfo(ShippingInfo shippingInfo)
        {
            ShippingInfo = shippingInfo ?? throw new ArgumentNullException(nameof(shippingInfo));
        }

        public void Confirm()
        {
            if (IsConfirmed)
            {
                throw new Exception($"Purchase Order already confirmed on {ConfirmedOn}");
            }

            ConfirmedOn = DateTime.UtcNow;
        }

        public void Ship()
        {
            if (IsShipped)
            {
                throw new Exception($"Purchase Order already shipped on {ShippedOn}");
            }

            if (ShippingInfo is null)
            {
                throw new Exception("There is no shipping info and the order can't be shipped");
            }

            if (!IsConfirmed)
            {
                throw new Exception("The purchase order is not yet confirmed");
            }

            ShippedOn = DateTime.UtcNow;
        }

        public override string ToString()
        {
            var message = 
                new StringBuilder()
                    .AppendLine($"Purchase Order with Id={Id}");

            if (IsConfirmed)
            {
                message.AppendLine($"Confirmed on {ConfirmedOn}");
            }

            if (IsShipped)
            {
                message.AppendLine($"Shipped on {ShippedOn} to {ShippingInfo}");
            }

            message
                .AppendLine("List Items")
                .AppendLine($"\t{string.Join(", ", LineItems)}");

            return message.ToString();
        }
    }
}
