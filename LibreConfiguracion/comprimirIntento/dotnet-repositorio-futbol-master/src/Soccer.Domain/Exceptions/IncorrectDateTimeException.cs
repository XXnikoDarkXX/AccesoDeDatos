using System;

namespace Soccer.Domain.Exceptions
{
    public class IncorrectDateTimeException
        : Exception
    {
        public IncorrectDateTimeException(string message)
            : base(message)
        {
        }
    }
}