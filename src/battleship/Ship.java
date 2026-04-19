package battleship;

public class Ship {
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;
    
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
    public abstract String getShipType();

    // other methods
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        return false;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    }

    public boolean shootAt(int row, int column) {
        return false;
    }

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
        return "x"; // otherwise return "x"
    }

}
