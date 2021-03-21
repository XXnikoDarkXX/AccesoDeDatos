using System;

namespace Soccer.Domain.Exceptions
{
    public class InvalidTeamException
        : Exception
    {
        public InvalidTeamException(string message)
            : base(message)
        {
        }
    }
}