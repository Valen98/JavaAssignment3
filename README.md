# JavaAssignment3

This assignment is made in groups of up to 2 students.

Your assignment is to create a program which uses threads to simulate a number of people in their workplace, having breaks (fika) to socialize and energize at the coffee machine.

Every person has a unique name and an energy-level represented through integer values,  where 0 represents the weakest-level below which that person leaves the workplace and goes home, and 100 represents the minimum level of energy at which that person goes to work. When a person is weak (energy level <30), that person goes to the coffee room and uses the shared coffee machine. Every drink delivered by the machine gives a certain amount of energy to the person: A BlackCoffee gives 15-20, a Capuccino gives 20-30 and a Latte gives 25-35. It takes two seconds for the coffee machine to produce a hot drink, which is added to its drink reserve. The machine can deliver one drink to one worker each second, as long as there is at least one drink in the reserve. The reserve can hold at most 20 drinks at a time.

Workers will need to queue for the coffee machine and should be served in a FIFO order. If a worker's energy is still below 100 after drinking a cup, they will go back to the end of the queue and wait for another cup. Below is an illustration of a person movements (state transitions) with respect to a worker's energy-level.

In the beginning, the machine has a reserve for 0 cups of hot drinks and every worker starts their day in the office with an energy-level between 30 and 90. For every worker, their energy decreases with 1 energy every T seconds, where T is an individual constant for each worker. T should be randomly assigned in the interval (500 ms <= T <= 1500 ms). Energy decreases both when the worker is in the office and waiting in the coffee room for a new drink, and should leave the coffee queue in case it's energy level goes to 0 while waiting. For simplicity, you may assume that drinking is instant.

You should simulate the above scenario of worker’s energy-based states during a given time-window expressed in seconds, which illustrates the different events discussed in the above scenario. A sample of the simulation output is given below (the sample display does not illustrate the synchronization points when using the coffee machine):

Both the coffee machine and each worker should run its own thread and interact with other threads in a thread safe manner. The coffee queue can for example be implemented using the ConcurrentLinkedQueue (Links to an external site.), which is thread safe.

Set up a simulation of at least four workers over 20 seconds. We recommend that you implement a scaling of time so that the speed of the simulation can be increased, which greatly facilitate testing. Your main class that starts the application should be named Fika. Indicate in a clear way (using comments) how the code is adjusted to change simulation speed, duration, and number of workers.

Please note that this assignment is solve in pairs, and you need to follow your own plan to create your program. This means you are expected to create all of the content you are submitting. It is ok to discuss with other students about your ideas and get help from others to get better understanding, however in the end all material you are submitting needs to be created by you. Plagiarism of the code or other material is prohibited. In case unsure, please consult with your supervisor.
