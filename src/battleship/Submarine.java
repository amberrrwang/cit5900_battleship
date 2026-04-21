package battleship;

public class Submarine extends Ship {

    public Submarine() {
        super(1); // length = 1
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }

}
