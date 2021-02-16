using System;

namespace Soccer.Domain.Exceptions
{
    public class GameEndedException
        : Exception
    {
        public GameEndedException()
            : base("The game has already ended")
        {
        }
    }
}