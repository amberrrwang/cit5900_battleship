package battleship;

public class Ship {
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;
    
    // set the length of the ship and initialize the hit array
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    // getters
    public int getLength() {
        return length;
    }

    public int getBowRow() {
        return bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public boolean[] getHit() {
        return hit;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    // setters
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    // abstract methods
    // every subclass will implement this to return the type of ship
    public abstract String getShipType(); 

    // other methods
    // returns true if it is legal to place a ship of this length with its bow in this location, false otherwise
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        return false;
    } 
    // places the ship in given location on the ocean
    // horizontal ships face east and vertical ships face south
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    }
    // returns true if the given location contains a part of the ship and hasn't been sunk, false otherwise
    public boolean shootAt(int row, int column) {
        return false;
    }
    // returns true if every part of the ship has been hit, false otherwise
    public boolean isSunk() {
        for (boolean partHit : hit) {
            if (!partHit) {
                return false;
            }
        }     return true;
    } 

    @Override
    public String toString() {
        if (isSunk()) {
            return "s"; // if the ship has been sunk, return "s"
        }
        return "x"; // return "x" if it has not been sunk 
    }

}
