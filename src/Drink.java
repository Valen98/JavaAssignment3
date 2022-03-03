import java.util.Random;

public class Drink {
	String coffeeType;
	int drinkEnergy;
	//BrewDrink every time it gets called
	public Drink() {
		brewDrink();
	}
	
	public void brewDrink() {
		Random rand = new Random();
		//Random value between 0-2
		int randomCoffee = rand.nextInt(3);
		
		//Sets the value depending on what randomCoffee gives
		if(randomCoffee == 0) {
			this.coffeeType = "BlackCoffee";
			this.drinkEnergy = rand.nextInt(20-15) + 15;
		}else if (randomCoffee == 1) {
			this.coffeeType = "Cappucino";
			this.drinkEnergy = rand.nextInt(30-20) + 20;
		}else {
			this.coffeeType = "Latte";
			this.drinkEnergy = rand.nextInt(35-25) + 25;
		}
	}
}
