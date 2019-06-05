import java.util.Random;

public class SimulatedAnnealing {

    public static Combination runSimulatedAnnealing(Grid grid, Combination currentCombination, NeightboursStrategy strategy,double temperature, double mu) {
        int sameElement = 0;

        Combination bestCombination = currentCombination;
        int currentFitness = currentCombination.getFitness();
        int bestFitness = currentFitness;

        Neightbour neightbour;
        int neightbourFitness = Integer.MAX_VALUE;

        Random random = new Random();
        int rng;

        while (sameElement <= 3) {
            rng = random.nextInt(grid.getSize());
            neightbour = strategy.getNeightbour(currentCombination, rng);

            neightbourFitness = neightbour.getCombination().getFitness();

            if (neightbourFitness < currentFitness) {
                if (neightbourFitness < bestFitness) {
                    bestFitness = neightbourFitness;
                    bestCombination = neightbour.getCombination();
                }

                currentFitness = neightbourFitness;
                currentCombination = neightbour.getCombination();
                sameElement = 0;
            } else {
                if (random.nextDouble() < Math.exp((currentFitness - neightbourFitness) / temperature)) {
                    currentFitness = neightbourFitness;
                    currentCombination = neightbour.getCombination();
                    sameElement = 0;

                    temperature *= mu;
                } else {
                    ++sameElement;
                }
            }
        }
        return bestCombination;
    }
}
