package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors using Shotgun
 * @author Wincent
 *
 */
public class ShotgunAttackAction extends Action{
	
	private String[] direction;
	private String result;
	private static int bulletCounter = 9;
	private boolean shot = false;
	private int directionCount = 0;
	
	/**
	 * Constructor
	 * @param direction The name of the direction
	 */
	public ShotgunAttackAction(String[] direction) {
		this.direction = direction;
	}
	
	public ShotgunAttackAction(String[] direction, int directionCount) {
		this.direction = direction;
		this.directionCount = directionCount;
	}
	
	/**
	 * To shoot to a direction and will hurt all the target in the direction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		int playerX = map.locationOf(actor).x();
		int playerY = map.locationOf(actor).y();
		int damage = weapon.damage();
		result = "";
		
		if(bulletCounter != 0) {
			bulletCounter--;
		}else {
			for(Item item : actor.getInventory()) {
				if(item.getDisplayChar() == '0') {
					actor.removeItemFromInventory(item);
					bulletCounter = 9;
					break;
				}
			}
		}
		
		if(direction[directionCount] == "North") {
			//NORTH
			double shootChance = Math.random();
			int initial_x = -3;
			int initial_x2 = 4;
			for(int y=-3; y<1; y++) {
				for(int x=initial_x; x < initial_x2; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
				initial_x += 1;
				initial_x2 -= 1;
			}
		}else if(direction[directionCount] == "South") {
			//SOUTH
			double shootChance = Math.random();
			int initial_x = -1;
			int initial_x2 = 2;
			for(int y=0; y<4; y++) {
				for(int x=initial_x; x < initial_x2; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
				initial_x -= 1;
				initial_x2 += 1;
			}
		}else if(direction[directionCount] == "East") {
			//EAST
			double shootChance = Math.random();
			int initial_y = 0;
			int initial_y2 = 1;
			for(int x=0; x<4; x++) {
				for(int y=initial_y; y < initial_y2; y++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
				initial_y -= 1;
				initial_y2 += 1;
			}
		}else if(direction[directionCount] == "West") {
			//WEST
			double shootChance = Math.random();
			int initial_y = -3;
			int initial_y2 = 4;
			for(int x=-3; x<1; x++) {
				for(int y=initial_y; y < initial_y2; y++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}

						}
					} catch (Exception e) {
					}
				}
				initial_y += 1;
				initial_y2 -= 1;
			}
		}else if(direction[directionCount] == "North-West") {
			//NORTH WEST
			double shootChance = Math.random();
			for(int y=-3; y<1; y++) {
				for(int x=-3; x < 1; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}

						}
					} catch (Exception e) {
					}
				}
			}
		}else if(direction[directionCount] == "South-West") {
			//SOUTH WEST
			double shootChance = Math.random();
			for(int y=0; y<4; y++) {
				for(int x=-3; x < 1; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@'){
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
			}
		}else if(direction[directionCount] == "North-East") {
			//NORTH EAST
			double shootChance = Math.random();
			for(int y=-3; y<1; y++) {
				for(int x=0; x < 4; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@') {
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
			}
		}else if(direction[directionCount] == "South-East") {
			//SOUTH EAST
			double shootChance = Math.random();
			for(int y=0; y<4; y++) {
				for(int x=0; x < 4; x++) {
					try {
						if((map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.ALIVE) || 
								map.at(x + playerX, y + playerY).getActor().hasCapability(ZombieCapability.UNDEAD))
								&& map.at(x + playerX, y + playerY).getDisplayChar() != '@'){
							if(shootChance <= 0.75) {
								map.at(x + playerX, y + playerY).getActor().hurt(damage);
								result += actor + " " + weapon.verb() + " " + map.at(x + playerX, y + playerY).getActor() + 
										" for " + damage + " damage.\n";
								result += checkTarget(map.at(x + playerX, y + playerY).getActor(), map) + "\n";
							}else {
								result += actor + " misses " + map.at(x + playerX, y + playerY).getActor();
							}
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return menuDescription(actor) + "\n" + result;
	}

	@Override
	public Actions getNextActions(GameMap map) {
		if (!shot) {
			Actions actions = new Actions();
			for (int i = 0; i < direction.length; i++) {
				actions.add(new ShotgunAttackAction(direction,i));
			}
			return actions;
		}
		return null;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoot " + direction[directionCount];
	}
	
	/**
	 * To check whether the target is still alive
	 * @param target An Actor
	 * @param map A GameMap
	 * @return A string of result saying the target is killed.
	 */
	public String checkTarget(Actor target, GameMap map) {
		result = "";
		if (!target.isConscious()) {
			if (target.hasCapability(ZombieCapability.ALIVE)) {
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
	

}
