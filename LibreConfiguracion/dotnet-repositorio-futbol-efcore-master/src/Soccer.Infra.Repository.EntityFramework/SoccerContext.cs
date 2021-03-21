using Microsoft.EntityFrameworkCore;
using Soccer.Infra.Repository.EntityFramework.Entidades;

namespace Soccer.Infra.Repository.EntityFramework
{
    public class SoccerContext
        : DbContext
    {
        public SoccerContext(DbContextOptions<SoccerContext> options) 
            : base(options)
        {

        }
        /**
        * Cuando haga la conexion de datos nos crea las tablas
        */
        // Añade DbSet por cada tabla que consideres oportuna.
        // Ejemplo:
        // public DbSet<GameEntity> Games { get; set; }
        // public DbSet<TeamEntity> Teams { get; set; }
        //public DbSet<GoalEntity> Goals { get; set; }
        // etc.

        //Le metemos al dbSet
        public DbSet<EntityGame> Games { get; set; }
        DbSet<EntityGoles> teams { get; set; }
        DbSet<EntityTeam> goals { get; set; }

       
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            modelBuilder.Entity<EntityGame>(


                variable =>
                {
                    variable.HasNoKey();
                });

            modelBuilder.Entity<EntityGoles>(


           variable =>
           {
               variable.HasNoKey();
           });

            modelBuilder.Entity<EntityTeam>(


               variable =>
               {
                   variable.HasNoKey();
               });


        }
    }
}