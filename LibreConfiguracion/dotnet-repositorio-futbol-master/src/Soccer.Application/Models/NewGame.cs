namespace Soccer.Application.Models
{
    public class NewGame
    {
        /// <summary>
        /// Local Team Code as a 3 uppercase characters
        /// </summary>
        /// <example>RMD</example>
        public string LocalTeamCode { get; set; } = null!;

        /// <summary>
        /// Foreign Team Code as a 3 uppercase characters
        /// </summary>
        /// <example>FCB</example>
        public string ForeignTeamCode { get; set; } = null!;
    }
}