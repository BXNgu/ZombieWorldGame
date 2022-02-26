package game;

import edu.monash.fit2099.engine.*;


/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] allBehaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new PickUpItemBehaviour(),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	private Behaviour[] someBehaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new PickUpItemBehaviour(),
	};

	private BodyParts bodyParts = new BodyParts();
	private int tempHP = hitPoints;
	
	
	/**
	 * Constructor
	 * @param name Name of Zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}


	/**
	 * If a Zombie does not have a weapon in his inventory, it will use punch or bites as their attack
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		double bite = Math.random();
		if (bite <= 0.3) {
			super.heal(5);
			tempHP = hitPoints;
			return new IntrinsicWeapon(15, "bites");
		} 
		if(bodyParts.chanceToAttack()){
			return new IntrinsicWeapon(10, "punches");
		}else {
			return null;
		}
	}


	/**
	 * If a Zombie can attack, it will. If not, it will picks up the weapon if there is one which the zombie is
	 * standing or else it will chase any human within 10 spaces.
	 * If no humans are close enough it will wander randomly.
	 * When Zombie is getting attacked by Player, it will have chance to drop limbs
	 *
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn (Actions actions, Action lastAction, GameMap map, Display display){				
		if (map.locationOf(this).getGround().getDisplayChar() == 'X') {
			return new StunAction();
		}
		
		if(tempHP - hitPoints > 0) {
			if (bodyParts.dropLegs()) {
				bodyParts.legs--;
				Leg leg = new Leg();
				map.locationOf(this).addItem(leg);
				System.out.println(this + " leg drop");
			}

			if (bodyParts.dropArms()) {
				bodyParts.arms--;
				Arm arm = new Arm();
				map.locationOf(this).addItem(arm);
				System.out.println(this + " arm drop");
				for (Item item : this.getInventory()) {
					if (item != null  && bodyParts.arms == 1) {
						double weaponDrop = Math.random();
						if (weaponDrop <= 0.5) {
							Behaviour behaviour = new DropItemBehaviour();
							return behaviour.getAction(this, map);
						}
					}
						if (bodyParts.arms == 0) {
							Behaviour behaviour = new DropItemBehaviour();
							return behaviour.getAction(this, map);
						}
				}
			}
		}
		tempHP = hitPoints;

		double chanceToSay = Math.random();
		if (chanceToSay <= 0.1) {
			display.println(name + ": Braaaaaaaaaains");
		}

		if(bodyParts.chanceToMove()) {
			for (Behaviour behaviour : allBehaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}else if(bodyParts.arms == 0){
			Behaviour attackBehaviour = new AttackBehaviour(ZombieCapability.ALIVE);
			if(attackBehaviour.getAction(this, map) != null){
				return attackBehaviour.getAction(this, map);
			}
		} else{
			for (Behaviour behaviour : someBehaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		return new DoNothingAction();
	}
}

