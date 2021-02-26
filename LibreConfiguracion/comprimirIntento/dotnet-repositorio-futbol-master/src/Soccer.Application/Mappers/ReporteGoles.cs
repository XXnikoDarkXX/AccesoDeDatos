using Soccer.Application.Models;
using Soccer.Domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace Soccer.Application.Mappers
{
   public class ReporteGoles
    {

        private GameReport report;
        private List<Goal> golesLocales { get; set; }
        private List<Goal> golesAdversario { get; set; }




        public ReporteGoles(GameReport report)
        {
            this.report = report;
            this.golesAdversario = new List<Goal>();
            this.golesLocales = new List<Goal>();
        }

        public void añadirGoles(NewGoal newGoal,Goal gol)
        {
            //COMPROBAMOS si el gol que pasamos por parametros de de el equipo local o adversario

            if (newGoal.TeamCode.Equals(this.report.LocalTeamName))
            {
                
                golesLocales.Add(gol);
            }
            else if (newGoal.TeamCode.Equals(this.report.ForeignTeamName))
            {
                golesAdversario.Add(gol);
            }
        }


        public GameReport getGameReport()
        {
            return this.report;
        }



       
    }
}
