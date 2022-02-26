package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	private int tempSniperHP = hitPoints;
	private static HashMap<Actor, Integer> aiming = new HashMap<Actor, Integer>();
	private static HashMap<Actor, Integer> tempAiming = new HashMap<Actor, Integer>();

	private Menu menu = new Menu();
	private boolean shotgunAmmo;
	private boolean sniperAmmo;
	
	private String[] directions = {"top-left","left","bottom-left","top","center","bottom", 
			"top-right", "right","bottom-right"};
	private String[] attackDirections = {"North-West", "North", "North-East", "West", "East", 
			"South-East", "South", "South-West"};

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	/**
	 * In Player's playTurn, Player can choose their actions from the menu.
	 * Player can move 8 directions if valid, attack Zombies, Harvest ripe crop, 
	 * Eat food in his inventory to restore health and craft weapons. 
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Player is
	 * @param display    the Display where the Player's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		int woodCount = 0;
		for (Item item : this.getInventory()) {
			if (item.getDisplayChar() == 'w') {
				woodCount++;
			}
		}
		
		if (woodCount >= 4) {
			actions.add(new SetTrapAction());
		}
	
		int x = map.locationOf(this).x();
		int y = map.locationOf(this).y();
		int counter = 0;
		for (int i = -1; i < 2; i++ ) {
			for (int j = -1; j < 2; j++) {
				try {
					if (map.at(x+i,y+j).getGround().getDisplayChar() == 'r'){
						actions.add(new HarvestAction(map.locationOf(this).getGround(),
								directions[counter],x+i,y+j));
					}else if (map.at(x+i,y+j).getGround().getDisplayChar() == 'T'){
						actions.add(new ChopTreeAction(directions[counter],x+i,y+j));
					}else if (map.at(x+i,y+j).getGround().getDisplayChar() == 'O' && woodCount >= 2){
						actions.add(new RepairAction(directions[counter],x+i,y+j));
					}
				} catch (Exception e) {
					
				}
				counter++;
			}
		}
	
		if(map.locationOf(this).getGround().getDisplayChar() == '{') {
			actions.add(new PickUpItemAction(new PortableItem("shotgun bullet", '0')));
		}
		
		if(map.locationOf(this).getGround().getDisplayChar() == '}') {
			actions.add(new PickUpItemAction(new PortableItem("sniper bullet", '1')));
		}
		
		if (this.hitPoints < this.maxHitPoints) {
			for (int i = 0; i < this.getInventory().size(); i++) {
				if (this.getInventory().get(i).getDisplayChar() == '*') {
					actions.add(new EatingAction(this.getInventory().get(i)));
				}
			}
		}
		for (Item item : this.getInventory()) {
			if (item.getDisplayChar() == 'A' || item.getDisplayChar() == 'L') {
				actions.add(new CraftingAction(item));
			}
		}
		for(Item item : this.getInventory()) {
			if(item.getDisplayChar() == '0') {
				shotgunAmmo = true;
				break;
			}else {
				shotgunAmmo = false;
			}
		}
		try {
			counter = 0;
			if(this.getWeapon().verb() == "boom" && shotgunAmmo) {
				actions.add(new ShotgunAttackDirectionAction(attackDirections));
			}
		} catch (Exception e1) {
		}
		
		for(Item item : this.getInventory()) {
			if(item.getDisplayChar() == '1') {
				sniperAmmo = true;
				break;
			}else {
				sniperAmmo = false;
			}
		}
		
		if(this.getWeapon().verb() == "bang" && sniperAmmo) {
			for(int i=-10; i<11; i++) {
				for(int j=-10; j<11; j++) {
					int xPlayer = map.locationOf(this).x();
					int yPlayer = map.locationOf(this).y();
					try {
						Actor target = map.at(xPlayer + i, yPlayer + j).getActor();
						if(target.hasCapability(ZombieCapability.UNDEAD)){
							if(!aiming.containsKey(target)){
								aiming.put(target, 0);
								tempAiming.put(target, -1);
							}
							
							SniperRifleAttackAction sniperAttack = 
									new SniperRifleAttackAction(target, aiming);
							SniperRifleAimingAction sniperAim = 
									new SniperRifleAimingAction(target, aiming);	
							
							if(tempSniperHP - hitPoints > 0) {
								sniperAim.reset();
							}
							
							if(aiming.get(target) <= 2) {
								actions.add(sniperAttack);
							}
							else{
								sniperAim.reset();
							}	

							if(aiming.get(target) > 0) {
								if(tempAiming.get(target) < aiming.get(target)){
									tempAiming.put(target, aiming.get(target));
								}else {
									sniperAim.reset();
									tempAiming.put(target, -1);
								}
							}
							
						}
					}catch (Exception e) {
					}
				}
			}
			tempSniperHP = hitPoints;
		}
	
		actions.add(new EndGameAction(this));
		
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		Action menuSelection = menu.showMenu(this, actions, display);
		if (menuSelection.getNextActions(map) != null) {
			menuSelection = menu.showMenu(this, menuSelection.getNextActions(map), display);
			if (menuSelection.getFollowUpActions(map,this,lastAction) != null) {
				menuSelection = menu.showMenu(this, menuSelection.getFollowUpActions(map, this, lastAction), display);
			}
		}
		return menuSelection;
	}
}
