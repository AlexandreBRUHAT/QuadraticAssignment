public class Main {

    public static void main (String[] args) {


        Grid grid = new Grid("data/tai30a.txt");

        int runs = 20;

        // Tabou parameters
        int tabouSize = 0;
        int maxIterations = 30_000;

        // Recui parameters
        double startingTemperature = 999999999999D;
        double recuiAcceptation = 0.9;

        int randomStrategyNumber = 160;

        double moyTabouCyclic = 0;
        double moyTabouRandom = 0;

        double moyRecuiCyclic = 0;
        double moyRecuiRandom = 0;

        Combination combination, tabou, recui;

        RandomNeightbourStrategy randomNeightbourStrategy = new RandomNeightbourStrategy(randomStrategyNumber);

        System.out.println("----------TABOU----------");

//        for(int tabouIncrement = tabouSize; tabouIncrement < grid.getSize(); ++tabouIncrement) {
//            moyTabouCyclic = 0;
//            moyTabouRandom = 0;
//
//            System.out.println("___________________________________");
//            System.out.println("Results for TabouSize: " + tabouIncrement);
//
//            for (int i = 0; i < runs; ++i) {
//                combination = Combination.generateRandom(grid);
//
////                tabou = Tabou.runTabou(grid, combination, CyclicSwapStrategy.getInstance(), tabouIncrement, maxIterations);
////                moyTabouCyclic += tabou.calculateFitness();
//
//                tabou = Tabou.runTabou(grid, combination, randomNeightbourStrategy, tabouIncrement, maxIterations);
//                //System.out.println(tabou);
//                moyTabouRandom += tabou.getFitness();
//            }
//
//            moyTabouCyclic /= runs;
//            moyTabouRandom /= runs;
//
////            System.out.println("Result for tabou (Cyclic): " + moyTabouCyclic);
//            System.out.println("Result for tabou (Random): " + moyTabouRandom);
//        }



        for (int i = 0; i < runs; ++i) {
            combination = Combination.generateRandom(grid);

            // 95% acceptation
//            recui = SimulatedAnnealing.runSimulatedAnnealing(grid,combination, CyclicSwapStrategy.getInstance(),startingTemperature,recuiAcceptation);
//            moyRecuiCyclic += recui.getFitness();

            // 95% acceptation
            recui = SimulatedAnnealing.runSimulatedAnnealing(grid,combination, randomNeightbourStrategy,startingTemperature,recuiAcceptation);
            moyRecuiRandom += recui.getFitness();
        }

        moyRecuiCyclic /= runs;
        moyRecuiRandom /= runs;

        System.out.println("----------RECUI----------");

//        System.out.println("Result for recui (Cyclic): " + moyRecuiCyclic);
        System.out.println("Result for recui (Random): " + moyRecuiRandom);
    }
}
