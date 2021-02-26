using System;
using System.Collections.Generic;
using System.Text;

namespace Soccer.Application.Exceptions
{
    class IdYaExisteException: Exception
    {
        public IdYaExisteException(string message) : base(message)
        {
        }
    }
}
