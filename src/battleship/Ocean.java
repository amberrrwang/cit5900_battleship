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
   Ship [][] getShipArray() {
     return ships
       }
  /**
    * Places all ten ships randomly on the (initially empty) ocean 
    */
  void placeAllShipsRandomly() {
    
  
}
