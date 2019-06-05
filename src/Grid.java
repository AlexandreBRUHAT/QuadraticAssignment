import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Grid {

    private int size;
    private int[][] flow;
    private int[][] distance;

    public Grid(String path) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {

            size = Integer.parseInt(bufferedReader.readLine().trim());

            flow = new int[size][size];
            distance = new int[size][size];

            String line;

            for (int i = 0; i < size; ++i) {
                line = bufferedReader.readLine();

                if (line.trim().isEmpty()) line = bufferedReader.readLine();
                String[] lineSplit = line.split(" ", -1);

                int j = 0;

                for (String value : lineSplit) {
                    if (!value.trim().isEmpty()) {
                        flow[i][j] = Integer.parseInt(value);
                        j++;
                    }
                }
            }

            line = bufferedReader.readLine();

            for (int i = 0; i < size; ++i) {
                line = bufferedReader.readLine();

                if (line.trim().isEmpty()) line = bufferedReader.readLine();
                String[] lineSplit = line.split(" ", -1);

                int j = 0;

                for (String value : lineSplit) {
                    if (!value.trim().isEmpty()) {
                        distance[i][j] = Integer.parseInt(value);
                        j++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }

    public int getDistance(int slot1, int slot2) {
        return distance[slot1][slot2];
    }

    public int getFlow(int slot1, int slot2) {
        return flow[slot1][slot2];
    }

    public int getCost(int slot1, int slot2) {
        return distance[slot1][slot2] * flow[slot1][slot2];
    }

    public int getHeaviestElement(int element) {
        int bestElement = element, flowBestElement = -1, flow;

        for (int i = 0; i < size; ++i) {
            if (i == element) continue;

            flow = getFlow(element,i);
            if (flowBestElement < flow) {
                bestElement = i;
                flowBestElement = flow;
            }
        }

        return bestElement;
    }

    public int getNearestSlot(int index) {
        int bestSlot = index, bestDistance = Integer.MAX_VALUE, distance;

        for (int i = 0; i < size; ++i) {
            if (i == index) continue;

            distance = getDistance(index, i);
            if (distance < bestDistance) {
                bestSlot = i;
                bestDistance = distance;
            }
        }

        return bestSlot;
    }
}
