package battleship;

public class EmptySea extends Ship {

    public EmptySea() {
        super(1);
    }

    @Override
    public boolean shootAt(int row, int column) {
        return false; // always returns false to indicate that nothing was hit
    }

    @Override
    public boolean isSunk() {
        return false; // always returns false to indicate that an empty sea cannot be sunk
    }

    @Override
    public String toString() {
        return "-"; // returns "-" to indicate that this is an empty sea when printed
    }

    @Override
    public String getShipType() {
        return "empty"; // returns "empty" to indicate that this is an empty sea
    }
}

