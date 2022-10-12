public class Log {
    private final GameEntity ATTACKER;
    private final LivingEntity ATTACKED;
    private final String LOG_STRING;

    public Log(GameEntity attacker, LivingEntity attacked){
        this.ATTACKER = attacker;
        this.ATTACKED = attacked;
        this.LOG_STRING = ATTACKER.getNAME() + " inflicts damage points " + (int)ATTACKER.getBASE_DAMAGE() + " on " +
                        ATTACKED.getNAME() + ". " + ATTACKED.getNAME() + "\'s current health: " +
                        (int)ATTACKED.getCurrentHealth() + "/" + (int)ATTACKED.getMAX_HEALTH();
    }

    public void printLog(){
        System.out.println(LOG_STRING);
    }
}
