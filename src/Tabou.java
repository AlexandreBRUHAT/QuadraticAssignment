import java.util.LinkedList;
import java.util.List;

public class Tabou {

    public static Combination runTabou(Grid grid, Combination currentCombination, NeightboursStrategy strategy, int tabouSize, int maxIterations) {
        List<Neightbour> neighbours;
        LinkedList<Integer> tabouList = new LinkedList<Integer>();

        int currentFitness = currentCombination.getFitness();
        int bestFitness = currentFitness;
        Combination bestCombination = currentCombination;

        Combination bestNeightbourCombination;

        for (int i = 0; i < maxIterations; ++i) {
            neighbours = strategy.getNeightbours(currentCombination);
            if (neighbours.size() == 0) break;

            bestNeightbourCombination = null;
            int bestNeightbourFitness = Integer.MAX_VALUE, bestIndex = -1;

            for (Neightbour neightbour : neighbours) {
                if (tabouList.contains(neightbour.getIdOperation())) continue;

                int cFitness = neightbour.getCombination().getFitness();
                if (bestNeightbourCombination != null && bestNeightbourFitness <= cFitness) continue;

                bestNeightbourFitness = cFitness;
                bestIndex = neightbour.getIdOperation();
                bestNeightbourCombination = neightbour.getCombination();
            }

            if (bestNeightbourCombination == null) break;

            if (bestNeightbourFitness > currentFitness && tabouSize > 0) {
                if (tabouList.size() == tabouSize) {
                    tabouList.removeFirst();
                }

                tabouList.addLast(bestIndex);
            } else if (bestNeightbourFitness < bestFitness) {
                bestFitness = bestNeightbourFitness;
                bestCombination = bestNeightbourCombination;
            }

            currentCombination = bestNeightbourCombination;
            currentFitness = bestNeightbourFitness;
        }

        return bestCombination;
    }
}
