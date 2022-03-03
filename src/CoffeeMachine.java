import java.util.concurrent.ConcurrentLinkedQueue;

public class CoffeeMachine extends Thread {
	ConcurrentLinkedQueue<Workers> queue = new ConcurrentLinkedQueue<Workers>();
	ConcurrentLinkedQueue<Drink> coffeeList = new ConcurrentLinkedQueue<Drink>();
	
	//The higher the brewSleeper is the longer it takes to brew drinks
	private int brewSleeper = (int) (2000/SimulationConfigs.simulationSpeed);
	//The higher the giveDrinkSleeper is the longer it takes to give out drinks to the workers
	private int giveDrinkSleeper = (int) (1000/SimulationConfigs.giveDrinkSpeed);

	long endTimer = SimulationConfigs.endTimer; 
	
	public void run() {
		//Start the brewing process
		startProducing();
		while (System.currentTimeMillis() < endTimer) {
			//Checks if the queue is empty and if coffee list is empty. If they are null it will not do anything
			if (!queue.isEmpty() && !coffeeList.isEmpty()) {
				System.out.println("Check queue - " + queue.element().getWorkerName());
				giveDrink(queue.poll(), coffeeList.poll());
			}

			try {
				Thread.sleep(giveDrinkSleeper);
			} catch (InterruptedException e) {
				System.out.println("Cannot sleep coffeMachine");
			}
		}
		System.out.println("Coffee machine is turned off for the day");
	}
	//Method to start thread for producing drinks
	public void startProducing() { 
		ProduceDrinks produceDrinks = new ProduceDrinks();
		produceDrinks.start();
	}
	
	CoffeeMachine() {
	}

	//Takes in worker and first checks if worker exist in queue. If not the worker is placed in queue
	public void coffeeQueue(Workers worker) {
		if (!queue.contains(worker)) {
			queue.add(worker);
		}
	}

	//Give drink to the requesting worker
	public void giveDrink(Workers worker, Drink drink) {
		worker.drinkCoffee(drink);
	}

	//Adds drink to the coffee list
	public void addDrink() {
		Drink addDrink = new Drink();
		coffeeList.add(addDrink);
	}
	
	public class ProduceDrinks extends Thread {
		public void run() {
			while(System.currentTimeMillis() < endTimer) {
				//If the size of the coffee list is greater than 20 it will not store anymore drinks. Else add drink to the coffee list
				if (coffeeList.size() < 20) {
					addDrink();
					System.out.println("Drink created. Coffee Machine has "+ coffeeList.size() +" drinks in reserve.");
				} else {
					System.out.println("Max amount of coffee in reserve");
				}
				try {
					Thread.sleep(brewSleeper);
				} catch (InterruptedException e) {
					System.out.println("Cannot sleep coffeMachine");
				}				
			}
		}
	}
}