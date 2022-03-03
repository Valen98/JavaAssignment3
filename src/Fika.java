import java.util.Random;

public class Fika {

	private static int minStartEnergy = 30;
	private static int maxStartEnergy = 90;

	public static void main(String[] args) {

		CoffeeMachine machine = new CoffeeMachine();
		// Starts the machine thread
		Thread m1 = new Thread(machine);
		m1.start();

		hireWorkers(SimulationConfigs.numberOfWorkers, machine, minStartEnergy, maxStartEnergy);

	}

	/***
	 * 
	 * @param min: min value for the random to pick from
	 * @param max: max value for the random to pick from
	 * @return: the value between min and max
	 */
	public static int random(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}

	/***
	 * 
	 * @param amountOfWorkers: Takes in number of workers and runs X times.
	 * @param machine:         uses the same instance of the coffee machine
	 * @param minStartEnergy:  The least amount of energy the worker can start with
	 * @param maxStartEnergy:  The most amount of energy the worker can start with
	 *                         It loops the amount of workers and creates a new
	 *                         worker object and starts the thread for the worker
	 */
	public static void hireWorkers(int amountOfWorkers, CoffeeMachine machine, int minStartEnergy, int maxStartEnergy) {
		Workers[] myWorkers = new Workers[amountOfWorkers];
		System.out.println("Lets hire new people!");
		for (int i = 0; i < amountOfWorkers; i++) {
			myWorkers[i] = new Workers(randomName(), machine, minStartEnergy, maxStartEnergy);
			Thread workerThreads = new Thread(myWorkers[i]);
			workerThreads.start();
		}
	}

	/***
	 * 
	 * @return String: Random name from 2 arrays so the workers have almost unique
	 *         name
	 */
	public static String randomName() {
		String[] firstTwo = { "Te", "Le", "Ma", "Si", "Pa", "Ro", "Ed", "Fu", "Ca", "Ka", "Ri", "Ev", "Pe", "Ze", "Co"};
		String[] lastTwo = { "ta", "xi", "ri", "ny", "er", "bu", "an", "ck", "rl", "ll", "on", "rt", "ra", "sa", "ta"};

		Random rand = new Random();

		String selectFirstTwo = firstTwo[rand.nextInt(firstTwo.length)];
		String selectLastTwo = lastTwo[rand.nextInt(lastTwo.length)];

		return selectFirstTwo + selectLastTwo;
	}

}

//Varje person ska vara en thread