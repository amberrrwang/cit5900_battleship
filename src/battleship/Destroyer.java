package battleship;

public class Destroyer extends Ship {

    public Destroyer() {
        super(2); // length = 2
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }

}
