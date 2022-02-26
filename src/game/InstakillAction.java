package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * An Action for Player to kill a target in one shot
 * @author Wincent
 *
 */
public class InstakillAction extends Action{
	protected Actor target;
	private static HashMap<Actor, Integer> aiming = new HashMap<Actor, Integer>();
	private String result;
	
	/**
	 * Constructor 
	 * @param target A Zombie target
	 * @param aiming A HashMap of Actor as a key and integer as the value
	 */
	public InstakillAction(Actor target,  HashMap<Actor, Integer> aiming) {
		this.target = target;
		this.aiming = aiming;
	}

	/**
	 * To kill the target in one turn
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		result = "";
		Weapon weapon = actor.getWeapon();
		
		int newDamage = actor.getWeapon().damage() + 100;
		target.hurt(newDamage);
		result += actor + " " + weapon.verb() + " " + target + " in the head";
		
		if (!target.isConscious()) {
			if (target.getDisplayChar() != 'Z' && target.getDisplayChar() != 'M') {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " instakill " + target;
	}

}
