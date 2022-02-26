package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A weapon which is dropped by Zombies
 * @author Wincent
 *
 */
public class Arm extends WeaponItem {

    public Arm(){
        super("arm", 'A', 20, "hit");
    }
}
