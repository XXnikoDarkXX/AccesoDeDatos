using System;

namespace Soccer.Domain
{
    public class Goal
    {
        public DateTime ScoredOn { get; }
        public string ScoredBy { get; }

        public Goal(DateTime scoredOn, string scoredBy)
        {
            ScoredOn = scoredOn;
            ScoredBy = scoredBy;
        }
    }
}