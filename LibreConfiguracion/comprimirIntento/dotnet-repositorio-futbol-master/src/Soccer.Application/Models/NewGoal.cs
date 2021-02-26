namespace Soccer.Application.Models
{
    public class NewGoal
    {
        /// <summary>
        /// The name of the scorer
        /// </summary>
        /// <example>Cristiano Ronaldo</example>
        public string ScoredBy { get; set; } = null!;//quien marca

        /// <summary>
        /// The team code for the team adding the goal to its score
        /// </summary>
        /// <example>RMD</example>
        public string TeamCode { get; set; } = null!;//codigo del equipo que marca
    }
}