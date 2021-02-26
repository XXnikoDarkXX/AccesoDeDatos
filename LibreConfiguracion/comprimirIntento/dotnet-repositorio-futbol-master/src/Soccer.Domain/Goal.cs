using System;

namespace Soccer.Domain
{
    public class Goal
    {
        public DateTime ScoredOn { get; }//cuando
        public string ScoredBy { get; }//quien

        public Goal(DateTime scoredOn, string scoredBy)
        {
            ScoredOn = scoredOn;
            ScoredBy = scoredBy;
        }
    }
}