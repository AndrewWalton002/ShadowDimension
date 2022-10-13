public class Log {
    private GameEntity attacker;
    private LivingEntity attacked;
    private String logAttackString;
    private String timescaleIncrementString;
    private String timescaleDecrementString;

    public Log(GameEntity attacker, LivingEntity attacked){
        this.attacker = attacker;
        this.attacked = attacked;
        this.logAttackString = attacker.getNAME() + " inflicts damage points " + (int)attacker.getBASE_DAMAGE() +
                        " on " + attacked.getNAME() + ". " + attacked.getNAME() + "\'s current health: " +
                        (int)attacked.getCurrentHealth() + "/" + (int)attacked.getMAX_HEALTH();

    }
    public Log(){
        this.timescaleIncrementString = "Sped up, Speed: " + ShadowDimension.getInstance().getTimesScale();
        this.timescaleDecrementString = "Slowed down, Speed: " + ShadowDimension.getInstance().getTimesScale();
    }

    public void printAttackLog(){
        System.out.println(logAttackString);
    }
    public void timescaleIncrementLog(){
        System.out.println(timescaleIncrementString);
    }
    public void timescaleDecrementLog(){
        System.out.println(timescaleDecrementString);
    }
}
