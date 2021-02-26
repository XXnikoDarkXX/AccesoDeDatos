using System;

namespace Soccer.Application.Services
{
    public interface IDateTimeService
    {
        DateTime GetUtcNow();
    }
}