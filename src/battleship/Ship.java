package battleship;

public abstract class Ship {
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;
    
    // constructor: set the length of the ship and initialize the hit array
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
    /**
      * Every subclass will implement this to return the type of ship
      */
    public abstract String getShipType(); 

    // other methods
    /**
     * If the ship hasn’t been sunk, mark that part of the ship as “hit” and return true; otherwise return false.
     * @param row
     * @param column 
     */
    public boolean shootAt(int row, int column) {
        // if already sunk, don't count hits 
        if(isSunk()) {
            return false;
        } 
        int index;

        if(isHorizontal()) {
            if (row != bowRow) {
                return false;
            }
            index = bowColumn - column;
        } else {
            if (column != bowColumn) {
                return false;
            }
            index = bowRow - row; 
        }
        // marked as hit 
        if(index >= 0 && index < getLength()) {
            getHit()[index] = true; 
            return true;
        }
        return false; 
    }   
    
    /**
     * Returns true if it is legal to place a ship of this length with its bow in this location, false otherwise
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
     Ship[][] ships = ocean.getShipArray(); 
        
    // making sure the ship is in the 10x10 grid 
    if(horizontal) {
        if(column - (getLength() - 1) < 0) return false;
    } else {
        if(row - (getLength() - 1) < 0) return false; 
        }

    // looping though each square of the ship
    for(int i = 0; i < getLength(); i++) {
        int r = row;
        int c = column;

        if(horizontal) {
            c = column - i; // updates length of column
        } else {
            r = row - i; // updates length of row 
            }
    // checks whether the square and all surrounding squares are emptysea (different ships cannot be touching)
    for(int rowShift = -1; rowShift <= 1; rowShift++) {
        for(int colShift = -1; colShift <= 1; colShift++) {
            int newR = r + rowShift;
            int newC = c + colShift;

            if (newR >= 0 && newR < 10 && newC >= 0 && newC < 10) {
                if (!(ships[newR][newC] instanceof EmptySea)) {
                    return false;
                }
            }
        }
    }
    }
        return true; 
    }
     /**
      * Places the ship in a given location on the ocean
      * Horizontal ships face east and vertical ships face south
      * @param row
      * @param column
      * @param horizontal
      * @param ocean
      */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    //give values
    this.bowRow = row;
    this.bowColumn = column;
    this.horizontal = horizontal; 

    Ship[][] ships = ocean.getShipArray();
    
    for(int i = 0; i < getLength(); i++) {
        if(horizontal) {
            ships[row][column - i] = this;
        } else {
            ships[row - i][column] = this;
        }
    }
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
