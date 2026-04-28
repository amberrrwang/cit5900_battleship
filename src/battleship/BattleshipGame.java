package battleship;

import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // ceate a new Ocean object (10x10 grid with ships)
        Ocean ocean = new Ocean();
        // randomly place all ships into the ocean
        ocean.placeAllShipsRandomly();

        // read user input
        Scanner scanner = new Scanner(System.in);

        // game loop: continues until all ships are sunk
        while (!ocean.isGameOver()) {
            ocean.print(); // print the ocean grid (hits/misses)

            System.out.print("Enter row,column: ");
            String input = scanner.nextLine();

            // parse input, validate input format 
            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter in format row,column.");
                continue; // restart loop and ask again
            }

            // convert input strings to integers
            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());

            // shoot at the specified location
            // returns true if a ship is hit, false otherwise
            boolean hit = ocean.shootAt(row, col);

            // display result of the shot
            if (hit) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }
        }

        // final output/ statistics after game is over
        System.out.println("Game over!");
        System.out.println("Shots: " + ocean.getShotsFired());
        System.out.println("Hits: " + ocean.getHitCount());
        System.out.println("Ships sunk: " + ocean.getShipsSunk());

        scanner.close();
    }
}
