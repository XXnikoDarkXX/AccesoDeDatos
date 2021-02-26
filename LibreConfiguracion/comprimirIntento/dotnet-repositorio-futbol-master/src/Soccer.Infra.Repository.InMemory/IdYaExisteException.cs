using System;
using System.Runtime.Serialization;

namespace Soccer.Infra.Repository.InMemory
{
    [Serializable]
    internal class IdYaExisteException : Exception
    {
        public IdYaExisteException()
        {
        }

        public IdYaExisteException(string message) : base(message)
        {
        }

        public IdYaExisteException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected IdYaExisteException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}