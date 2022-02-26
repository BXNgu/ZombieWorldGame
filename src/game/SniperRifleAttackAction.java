package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
/**
 * Special Action for attacking other Actors using Sniper Rifle
 * @author Wincent
 *
 */
public class SniperRifleAttackAction extends Action{

	protected Actor target;
	private static HashMap<Actor, Integer> aiming = new HashMap<Actor, Integer>();
	private static int counter = 9;
	private boolean shot = false;

	/**
	 * Constructor
	 * @param target A Zombie target
	 * @param aiming A HashMap of Actor as a key and integer as the value
	 */
	public SniperRifleAttackAction(Actor target, HashMap<Actor, Integer> aiming){
		this.target = target;
		this.aiming = aiming;
	}
	
	/**
	 * To shoot the target based on the turns of aiming of the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		String result = "";
		double shootChance = Math.random();
		
		if(counter != 0) {
			counter--;
		}else {
			for(Item item : actor.getInventory()) {
				if(item.getDisplayChar() == '1') {
					actor.removeItemFromInventory(item);
					counter = 9;
					break;
				}
			}
		}
		
		if(aiming.get(target) == 0) {
			if(shootChance <= 0.75) {
				target.hurt(weapon.damage());
				result += actor + " " + weapon.verb() + " " + target + " for " + weapon.damage() + " damage.";
			}else {
				result += actor + " misses " + target;
			}
		}else if(aiming.get(target) == 1) {
			if(shootChance <= 0.9) {
				int newDamage = actor.getWeapon().damage() + 25;
				target.hurt(newDamage);
				result += actor + " " + weapon.verb() + " " + target + " for " + newDamage + " damage.";
			}else {
				result += actor + " misses " + target;
			}
		}
				
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
//		System.out.println(displayCounter);
//		if (displayCounter == 1) {
//			displayCounter = 0;
			return actor + " shoots " + target;
//		} else {
//			return actor + " uses sniper to aim " + target;
//		}
//		return actor + " uses sniper to aim " + target;
	}
	
	@Override
	public Actions getNextActions(GameMap map) {
		if (!shot) {
			Actions actions = new Actions();
			if (aiming.get(target) < 2) {
				actions.add(new SniperRifleAttackAction(target,aiming));
				actions.add(new SniperRifleAimingAction(target,aiming));
			}
			if (aiming.get(target) == 2) {
				actions.add(new InstakillAction(target, aiming));
			}
			return actions;
		}
		return null;
	}
}
