package battleship;

import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // create a new Ocean object (10x10 grid with ships)
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
                continue;
            }

            try {
                // convert input strings to integers
                int row = Integer.parseInt(parts[0].trim());
                int col = Integer.parseInt(parts[1].trim());

                // validate row and column range
                if (row < 0 || row > 9 || col < 0 || col > 9) {
                    System.out.println("Invalid input. Row and column must be between 0 and 9.");
                    continue;
                }

                int sunkBefore = ocean.getShipsSunk();
                Ship ship = ocean.getShipArray()[row][col];

                // shoot at the specified location
                boolean hit = ocean.shootAt(row, col);

                if (hit) {
                    System.out.println("Hit!");

                    if (ocean.getShipsSunk() > sunkBefore) {
                        System.out.println("You just sank a ship - " + ship.getShipType());
                    }
                } else {
                    System.out.println("Miss!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers in format row,column.");
            }
        }

        ocean.print();
        System.out.println("Game over!");
        System.out.println("Shots: " + ocean.getShotsFired());
        System.out.println("Hits: " + ocean.getHitCount());
        System.out.println("Ships sunk: " + ocean.getShipsSunk());

        scanner.close();
    }
}