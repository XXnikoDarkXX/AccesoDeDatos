using System;

namespace Soccer.Application.Services
{
    public class DateTimeService
        : IDateTimeService
    {
        public DateTime GetUtcNow()
        {
            return DateTime.UtcNow;
        }
    }
}