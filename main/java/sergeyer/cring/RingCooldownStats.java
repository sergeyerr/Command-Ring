package sergeyer.cring;

public class RingCooldownStats {
    public long LastTimeofUsage;
    public Boolean CanBeUsed;
    public RingCooldownStats() {
        CanBeUsed = true;
        LastTimeofUsage = 0;
    }
}
