/** A class encapsulating the information regarding the log */
public class Log {
    /** Attack log attributes */
    private GameEntity attacker;
    private LivingEntity attacked;
    private String logAttackString;
    /** Timescale attributes */
    private String timescaleIncrementString;
    private String timescaleDecrementString;
    /** Constructor for a log of an attack */
    public Log(GameEntity attacker, LivingEntity attacked){
        this.attacker = attacker;
        this.attacked = attacked;
        this.logAttackString = attacker.getNAME() + " inflicts damage points " + (int)attacker.getBASE_DAMAGE() +
                        " on " + attacked.getNAME() + ". " + attacked.getNAME() + "\'s current health: " +
                        (int)attacked.getCurrentHealth() + "/" + (int)attacked.getMAX_HEALTH();
    }
    /** Constructor for a timescale log */
    public Log(){
        this.timescaleIncrementString = "Sped up, Speed: " + ShadowDimension.getInstance().getTimesScale();
        this.timescaleDecrementString = "Slowed down, Speed: " + ShadowDimension.getInstance().getTimesScale();
    }
    /** Print to the console, the attack log */
    public void printAttackLog(){
        System.out.println(logAttackString);
    }
    /** Print to the console, the timescale increment log */
    public void timescaleIncrementLog(){
        System.out.println(timescaleIncrementString);
    }
    /** Print to the console, the timescale decrement log */
    public void timescaleDecrementLog(){
        System.out.println(timescaleDecrementString);
    }
}