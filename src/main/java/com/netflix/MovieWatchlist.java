package com.netflix;
import java.util.*;
import java.io.*;
public class MovieWatchlist {
    public static void main(String[] args) throws Exception {

       ArrayList<Cat> allCats = new ArrayList<>();
       allCats.add(new Cat("Jerry"));
       allCats.add(new Cat("Tom"));


        // Display all cats
        for (Cat cat : allCats) {
            System.out.println(cat.name);
        }

        try {
            FileWriter writer = new FileWriter("cats.txt"); // This will create or overwrite the file
            for (Cat cat : allCats) {
                writer.write(cat.name + "\n");
            }
            writer.close();
            System.out.println("Cat names have been written to cats.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }










        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter = new FileWriter("MovieWatchlist.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Your movie watchlist: \n");


        // Start with an empty watchlist (no sample data)
        ArrayList<String> watchlist = new ArrayList<>();
        HashMap<String, Double> ratings = new HashMap<>();
        boolean running = true;
        System.out.println(" Welcome to your Movie Watchlist!");
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a movie");
            System.out.println("2. Display all movies in the watchlist");
            System.out.println("3. Give a movie a rating on a scale from 1-10");
            System.out.println("4. Stop the movie from playing");



            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear ENTER
            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String movie = scanner.nextLine();
                    watchlist.add(movie);
                    System.out.println(movie + " added to your watchlist!");
                    break;
                case 2:
                    if (watchlist.isEmpty()) {
                        System.out.println("No movies yet —add some first!");
                    } else {
                        for (String m : watchlist) {
                            String rating = ratings.containsKey(m)
                                    ? "⭐" + ratings.get(m)
                                    : "Not rated yet";
                            System.out.println("-" + m + " (" + rating + ")");
                        }
                        System.out.println("Total movies: " + watchlist.size());
                    }
                    break;
                case 3:
                    System.out.print("Enter movie to rate: ");
                    String rateMovie = scanner.nextLine();
                    if (watchlist.contains(rateMovie)) {
                        System.out.print("Enter rating (0-10): ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine();
                        ratings.put(rateMovie, rate);
                        System.out.println(rateMovie + " rated " + rate + "⭐!");
                        bufferedWriter.write(rateMovie + " rated " + rate + "⭐!\n");

                    } else {
                        System.out.println("Movie not found in your watchlist.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println(" Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        bufferedWriter.close();
        fileWriter.close();
    }
}
