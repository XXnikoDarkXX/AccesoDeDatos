using System;

namespace Soccer.Domain.Exceptions
{
    public class GameInProgressException
        : Exception
    {
        public GameInProgressException()
            : base("The game is in progress")
        {
        }
    }
}