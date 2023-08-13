package org.toys;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {
    public static class Toy {
        int id;
        String name;
        int weight;

        public Toy(int id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }
    }

    public static class ToyQueue {
        private PriorityQueue<Toy> toyQueue;
        private Random random;

        public ToyQueue(String[] toyStrings) {
            toyQueue = new PriorityQueue<>((toy1, toy2) -> toy2.weight - toy1.weight);
            random = new Random();

            for (String toyString : toyStrings) {
                String[] parts = toyString.split(" ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[2];
                int weight = Integer.parseInt(parts[1]);
                Toy toy = new Toy(id, name, weight);
                toyQueue.add(toy);
            }
        }

        public int getRandomToyId() {
            int randomValue = random.nextInt(100) + 1;

            if (randomValue <= 20) {
                return 1;
            } else if (randomValue <= 40) {
                return 2;
            } else {
                return 3;
            }
        }

        public void generateResultsToFile(int numResults) {
            try {
                FileWriter writer = new FileWriter("results.txt");
                for (int i = 0; i < numResults; i++) {
                    int randomToyId = getRandomToyId();
                    if (randomToyId == 1) {
                        writer.write("1\n");
                    } else if (randomToyId == 2) {
                        writer.write("2\n");
                    } else {
                        writer.write("3\n");
                    }
                }
                writer.close();
                System.out.println("Results written to 'results.txt'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] toyStrings = {
                "1 2 конструктор",
                "2 2 робот",
                "3 6 кукла"
        };

        ToyQueue toyQueue = new ToyQueue(toyStrings);
        toyQueue.generateResultsToFile(10);
    }
}