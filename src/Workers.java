import java.util.Random;

public class Workers extends Thread implements Person {

	private String name;
	CoffeeMachine machine;
	private int energyLevel;
	private int energyDecay;
	long endTimer = SimulationConfigs.endTimer;

	// The state of the worker to check if he/she is tired, working or on a break
	enum State {
		WORK, TIRED, BREAK,
	}

	public void run() {
		// Sets the workers enum to work.
		State workerState = State.WORK;
		// If the energyLevel is 0 the worker stops working and also stop working if the
		// timer is 0
		while (this.energyLevel > 0 && System.currentTimeMillis() < endTimer) {

			// Checks if the worker has drank enough coffee to start working again, energy
			// over 100, sets the energy to 100. Just because the worker cannot have more
			// energy than 100
			if (this.energyLevel >= 100) {
				workerState = State.WORK;
				this.energyLevel = 100;
			}

			// If worker working and the energy is decreased to 30. if 30 take a break and
			// start to queue up by the machine. If the worker drinks coffee and still has
			// less than 100, queue up again and start drinking.
			if (this.energyLevel < 30 || workerState.equals(State.BREAK)) {
				System.out.println(this.name + " is on break with energy: " + this.energyLevel);
				workerState = State.BREAK;
				this.machine.coffeeQueue(this);

			}
			//The worker has now over 100 energy and starts working again
			if (workerState.equals(State.WORK)) {
				System.out.println(this.name + " is working with energy: " + this.energyLevel);
			}
			//decrease the energyLevel by 1
			this.energyLevel--;
			try {
				//Sleep for how long the eneryDecay is
				Thread.sleep(this.energyDecay);
			} catch (InterruptedException e) {
				System.out.print("Cannot put into sleep");
			}
		}
		System.out.println(this.name + " has 0 energy and now goes home");

	}

	/***
	 * 
	 * @param name:           The workers name
	 * @param machine:        Which coffeeMahcine the worker belongs to
	 * @param minStartEnergy: the least amount energy the worker can start with
	 * @param maxStartEnergy: the most amount of energy the worker can start with
	 */
	public Workers(String name, CoffeeMachine machine, int minStartEnergy, int maxStartEnergy) {
		this.name = name;
		this.machine = machine;
		this.energyLevel = random(minStartEnergy, maxStartEnergy);
		this.energyDecay = (int) (random(500, 1500) / SimulationConfigs.decaySpeed);
	}

	@Override
	public int getEnergyLevel() {
		return this.energyLevel;
	}

	@Override
	public int getEnergyDecay() {
		return this.energyDecay;
	}

	@Override
	public String getWorkerName() {
		return this.name;
	}

	// Takes in the drink the worker drinks and adds the drink.energyLevel to this
	// workers energyLevel
	@Override
	public void drinkCoffee(Drink drink) {
		this.energyLevel += drink.drinkEnergy;
		System.out.println(this.name + " just drank " + drink.coffeeType + " increased energy with: "
				+ drink.drinkEnergy + " the energy is now: " + this.energyLevel);

	}

	/***
	 * 
	 * @return String: Random name from 2 arrays so the workers have almost unique
	 *         name
	 */
	public static int random(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}

}
