using System;

namespace Soccer.Domain.Exceptions
{
    public class GameNotInProgressException
        : Exception
    {
        public GameNotInProgressException()
            : base("The game is not in progress")
        {
        }
    }
}