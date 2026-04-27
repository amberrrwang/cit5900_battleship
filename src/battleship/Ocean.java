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
    if(ships[row][column] instanceof EmptySea) { //if at the row and column, 
      return false 
        } else {
      return true
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
    * Places all ten ships randomly on the (initially empty) ocean 
    */
  void placeAllShipsRandomly() {
    
  
}
