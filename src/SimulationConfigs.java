
public class SimulationConfigs {
	public static float simulationSpeed = 1f; // simulation speed = the speed of brewing coffee: increase to make it faster and decrease to make it slower
	public static float giveDrinkSpeed = 1; // giveDrinkSpeed = the time of giving out coffee: increase to make it faster and decrease to make it slower
	public static float decaySpeed = 1f; // decaySpeed = the time the worker loses 1 energy: increase to make it faster and decrease to make it slower

	public static int numberOfWorkers = 5; // This int is the amount of workers working

	private static int seconds = 20; //How long the program will run

	public static long start = System.currentTimeMillis();
	public static long endTimer = start + seconds * 1000;
}
