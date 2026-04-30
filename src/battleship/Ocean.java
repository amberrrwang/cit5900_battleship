package battleship;

public class Ocean {
//instance variables
  private Ship [][] ships = new Ship [10][10];
  private int shotsFired;
  private int hitCount;
  private int shipsSunk;

//constructor
  public Ocean() {
    shotsFired = 0;
    hitCount = 0;
    shipsSunk = 0;

    for (int row = 0; row < 10; row++;) {
      for (int col = 0; col < 10; row++;) {
        ships[row][col] = new EmptySea(); 
      }
    }
  }
  
  //methods
  /**
    * Returns the 10x10 array of ships 
    */
   public Ship [][] getShipArray() {
     return ships;
       }
  
  /**
    * Returns true if the given location contains a ship, false if it does not  
    */
  public boolean isOccupied(int row, int column) {
    if(ships[row][column] instanceof EmptySea) { //if at the row and column it's empty sea 
      return false; 
        } else {
      return true;
        }
  }
  
  /**
    * Returns number of ships sunk (in the game)
    */
  public int getShipsSunk() {
    return shipsSunk;  
  }

  /**
    * Returns the number of shots fired (in the game) 
    */
  public int getShotsFired() {
    return shotsFired;  
  }

  /**
    * Returns number of hits recorded (in the game)
    */
  public int getHitCount() {
    return hitCount; 
  }

  /**
    * Returns true if the given location contains a ”real” ship, still afloat, false if it does not. 
    * Updates the number of shots that have been fired, and the number of hits.
    * @param row 
    * @param column 
    */
  boolean shootAt (int row, int column) {
    shotsFired++; //counts every single shot 
    Ship ship = ships[row][column];

    boolean hit = ship.shootAt(row, column); 
    if(hit) {
      hitCount++;
      
      if(ship.isSunk()) {
        shipsSunk++;
      }
      
      return true;
    }
    
    return false;
  }

   /**
    * Places all ten ships randomly on the (initially empty) ocean 
    */
  boolean isGameOver() {
  if(ships.getShipsSunk == 10) { //if all ten are sunk
      return true;
  } else {
      return false;
    }
  }
    
  /**
    * Places all ten ships randomly on the (initially empty) ocean 
    */
  void placeAllShipsRandomly() {
  Random rand = new Random();
  
  //Battleship (1)
  for(int i = 0; i < 1; i++) {
    Ship ship = new Battleship(); 
    boolean placed = false; //has not been placed
    
      while (!placed) { //while not placed there
        int row = rand.nextInt(10); //generate random row
        int col = rand.nextInt(10); //generate random column
        boolean horizontal = rand.nextBoolean();

      if (ship.okToPlaceShipAt(row, col, horizontal, this)) { //call ship is okay to place at
        ship.placeShipAt(row, col, horizontal, this); //place
        placed = true; //boolean now true
      }
    }
  }
  //Cruisers (2)
  for(int i = 0; i < 2; i++) {
    Ship ship = new Cruiser(); 
    boolean placed = false;
    
      while (!placed) {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        boolean horizontal = rand.nextBoolean();

      if (ship.okToPlaceShipAt(row, col, horizontal, this)) {
        ship.placeShipAt(row, col, horizontal, this);
        placed = true;
      }
    }
  }
  //Destroyers (3)
  for(int i = 0; i < 3; i++) {
  Ship ship = new Destroyer(); 
  boolean placed = false;
    
      while (!placed) {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        boolean horizontal = rand.nextBoolean();

      if (ship.okToPlaceShipAt(row, col, horizontal, this)) {
        ship.placeShipAt(row, col, horizontal, this);
        placed = true;
      }
    }
  }
  //Submarines (4)
  for(int i = 0; i < 4; i++) {
  Ship ship = new Submarine(); 
  boolean placed = false;
    
      while (!placed) {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        boolean horizontal = rand.nextBoolean();

      if (ship.okToPlaceShipAt(row, col, horizontal, this)) {
        ship.placeShipAt(row, col, horizontal, this);
        placed = true;
      }
    }
  }
}

  /**
    * Prints the ocean display
    */
    public void print() {

    //Display column numbers 
    System.out.print("  ");
    for (int col = 0; col < 10; col++) {
        System.out.print(col + " ");
    }
    System.out.println();

    //loop through each row
    for (int row = 0; row < 10; row++) {

        System.out.print(row + " "); //display the row numbers

        for (int col = 0; col < 10; col++) { //nested loop through columns

            Ship ship = ships[row][col]; //object reference

           if (ship.isSunk()) { 
                System.out.print("s ");
            } else {
                System.out.print(ship.toString() + " "); //calls to string
            }
        }
        System.out.println();
      }
    }

  /**
    * Prints the Ocean with row number displayed along the left edge of the array, and column numbers displayed alongthe top.
    * Shows the location of the ships.
    */
    public void printWithShips() {
    //Display column numbers 
    System.out.print("  ");
    for (int col = 0; col < 10; col++) {
        System.out.print(col + " ");
    }
    System.out.println();

    //loop through rows
    for (int row = 0; row < 10; row++) {
        System.out.print(row + " "); //display row numbers

        for (int col = 0; col < 10; col++) { //nested loop through columns
            Ship ship = ships[row][col]; //object reference 
            String type = ship.getShipType(); //get ship type from ship class, object reference
            if (type.equals("battleship")) {
                System.out.print("b ");
            } else if (type.equals("cruiser")) {
                System.out.print("c ");
            } else if (type.equals("destroyer")) {
                System.out.print("d ");
            } else if (type.equals("submarine")) {
                System.out.print("s ");
            } else {
                System.out.print("  "); // empty sea
            }

        }

        System.out.println();
    }
}
