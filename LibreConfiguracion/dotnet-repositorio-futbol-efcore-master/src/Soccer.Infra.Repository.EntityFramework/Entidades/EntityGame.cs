using System;
using System.Collections.Generic;
using System.Text;

namespace Soccer.Infra.Repository.EntityFramework.Entidades
{
    public class EntityGame
    {
        private EntityTeam local { get; set; }
        private EntityTeam visitante { get; set; }
    }
}
