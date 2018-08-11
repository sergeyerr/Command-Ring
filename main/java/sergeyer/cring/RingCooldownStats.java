package sergeyer.cring;

public class RingCooldownStats {
    public long LastTimeofUsage;
    public Boolean CanBeUsed;
    public String UserName;
    public RingCooldownStats() {
        CanBeUsed = true;
        LastTimeofUsage = 0;
        UserName = "none";
    }
}
