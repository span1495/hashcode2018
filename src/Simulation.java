import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Simulation {
    public static void play(String file) {
        long T;
        Car[] cars;
        Ride[] rides;
        int R, C, F, N, B;

        try (Scanner scanner = new Scanner(new File("inputs/" + file + ".in"))) {
            R = scanner.nextInt();
            C = scanner.nextInt();
            F = scanner.nextInt();
            N = scanner.nextInt();
            B = scanner.nextInt();
            T = scanner.nextLong();

            rides = new Ride[N];

            for (int i = 0; i < N; i++) {
                rides[i] = new Ride(scanner, i);
            }
            Arrays.sort(rides);

            cars = new Car[F];
            for (int i = 0; i < F; i++) {
                cars[i] = new Car();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        long total = 0;
        for (Ride ride : rides) {
            int best_car = 0;
            int best_score = 0;
            for (int j = 0; j < cars.length; j++) {
                int score = cars[j].evaluate_ride(ride, T, B);
                if (score > best_score) {
                    best_car = j;
                    best_score = score;
                }
            }
            if (best_score > 0) {
                cars[best_car].assign_ride(ride);
                total += best_score;
            }
        }

        try (PrintWriter writer = new PrintWriter("output/" + file + ".out")) {
            for (Car car : cars) {
                writer.print(car.rides_id.size());
                for (Integer id : car.rides_id) {
                    writer.print(" " + id);
                }
                writer.println();
            }
            writer.close();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}