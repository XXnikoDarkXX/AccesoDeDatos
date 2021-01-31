using System;

namespace Domain
{
    public class ShippingInfo
    {
        public string Name { get; }
        public string Address { get; }

        public ShippingInfo(string name, string address)
        {
            Name = name ?? throw new ArgumentNullException(nameof(name));
            Address = address ?? throw new ArgumentNullException(nameof(address));
        }

        public override string ToString()
        {
            return $"{Name}, {Address}";
        }
    }
}
