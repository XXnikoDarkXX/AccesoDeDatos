using System;

namespace Soccer.Application.Models
{
    public class GameReport
    {
        public Guid Id { get; }//id
        public string LocalTeamName { get; }//Nombre del equipo
        public int LocalGoals { get; }//goles locales
        public string ForeignTeamName { get; }//Nombre de equivo adversario
        public int ForeignGoals { get; }//goles de equipo adversario
        public string Result => $"{LocalTeamName} {LocalGoals} - {ForeignGoals} {ForeignTeamName}";

        /**
         * Constructor de GameReport con todos los parametros
         * @params Guid id es la id del equipo
         * @parmas string localTeamName nombre del equipo local
         * @params int localGoals goles locales
         * @foreignTeanName nombre del equipo contrario
         * @paramas foreignGoals goles del adversario
         */
        public GameReport(Guid id, string localTeamName, int localGoals, string foreignTeamName, int foreignGoals)
        {
            Id = id;
            LocalTeamName = localTeamName;
            LocalGoals = localGoals;
            ForeignTeamName = foreignTeamName;
            ForeignGoals = foreignGoals;
        }
    }
}