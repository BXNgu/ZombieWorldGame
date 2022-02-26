package game;

/**
 * A class to represent the Zombie's arms and legs
 * @author Wincent
 */
public class BodyParts{

    private final int turn = 1;
    protected int arms = 2;
    protected int legs = 2;
    private int counter;

    /**
     * To check the chances of arms dropping when getting attack
     * @return a boolean
     */
    public boolean dropArms(){
        double armDrop = Math.random();
        if(arms > 0 && arms <= 2){
            if(armDrop <= 0.25){
                return true;
            }
        }
        return false;
    }

    /**
     * To check the chances of legs dropping when getting attack
     * @return a boolean
     */
    public boolean dropLegs(){
        double legDrop = Math.random();
        if(legs > 0 && legs <= 2){
            if(legDrop <= 0.25){
                return true;
            }
        }
        return false;
    }

    /**
     * To check the chances to attack
     * @return a boolean
     */
    public boolean chanceToAttack(){
        boolean attack = true;
        double chance = Math.random();
        if(arms >= 0 && arms <= 2) {
            if (arms == 1) {
                if (chance <= 0.5) {
                    attack = false;
                }
            }
            if (arms == 0) {
                attack = false;
            }
        }
        return attack;
    }

    /**
     * To check the chances to move on that turn
     * @return a boolean
     */
    public boolean chanceToMove() {
        boolean move = true;
        if (legs >= 0 && legs <= 2) {
            if (legs == 1) {
                if (counter != turn) {
                    counter++;
                    move = false;
                } else {
                    counter = 0;
                }
            }

            if (legs == 0) {
                move = false;
            }
        }
        return move;
    }
}
