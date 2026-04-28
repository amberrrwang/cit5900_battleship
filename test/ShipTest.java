package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		Ship cruiser = new Cruiser();
        assertEquals(3, cruiser.getLength());
        Ship destroyer = new Destroyer();
        assertEquals(2, destroyer.getLength());
		Ship submarine = new Submarine();
		assertEquals(1, submarine.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test vertical placement
		Ship destroyer = new Destroyer();
		row = 8;
		column = 2;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(8, destroyer.getBowRow());
		// test cruiser placement
		Ship cruiser = new Cruiser();
        cruiser.placeShipAt(5, 6, false, ocean);
        assertEquals(5, cruiser.getBowRow());
		// test submarine placement
		Ship submarine = new Submarine();
        submarine.placeShipAt(9, 9, true, ocean);
        assertEquals(9, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		// test vertical placement
		Ship destroyer = new Destroyer();
		row = 8;
		column = 2;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(2, destroyer.getBowColumn());
		// test cruiser placement
		Ship cruiser = new Cruiser();
		cruiser.setBowColumn(7);
		assertEquals(7, cruiser.getBowColumn());
		// test destroyer placement
		Ship destroyer = new Destroyer();
		destroyer.setBowColumn(0);
		assertEquals(0, destroyer.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		// check remaining indices
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		// test after hits are recorded
		ship.setBowRow(0);
		ship.setBowColumn(3);
		ship.setHorizontal(true);
		ship.shootAt(0, 3);
		assertTrue(ship.getHit()[0]);
		ship.shootAt(0, 2);
		assertTrue(ship.getHit()[1]);
		// test different ship length
		Ship cruiser = new Cruiser();
		assertEquals(3, cruiser.getHit().length);
		assertFalse(cruiser.getHit()[0]);
		assertFalse(cruiser.getHit()[1]);
		assertFalse(cruiser.getHit()[2]);
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		assertEquals("cruiser", new Cruiser().getShipType());
		assertEquals("destroyer", new Destroyer().getShipType());
		assertEquals("submarine", new Submarine().getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		// test vertical placement
		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
        // test destroyer
		Ship destroyer = new Destroyer();
		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());
		destroyer.setHorizontal(false);
		assertFalse(destroyer.isHorizontal());
		// test default
		Ship submarine = new Submarine();
    	assertFalse(submarine.isHorizontal()); // default boolean = false
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		Ship ship = new Cruiser();
        ship.setBowRow(7);
        assertEquals(7, ship.getBowRow());
		Ship destroyer = new Destroyer();
		destroyer.setBowRow(2);
		assertEquals(2, destroyer.getBowRow());
		Ship submarine = new Submarine();
		submarine.setBowRow(9);
		assertEquals(9, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		Ship ship = new Cruiser();
        ship.setBowColumn(2);
        assertEquals(2, ship.getBowColumn());
		Ship submarine = new Submarine();
		submarine.setBowColumn(9);
		assertEquals(9, submarine.getBowColumn());
		Ship destroyer = new Destroyer();
		destroyer.setBowColumn(0);
		assertEquals(0, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		// test setting to false
		battleship.setHorizontal(false);
		assertFalse(battleship.isHorizontal());
		// test cruiser
		Ship cruiser = new Cruiser();
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());
		// test toggle behavior
		cruiser.setHorizontal(false);
		assertFalse(cruiser.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		assertTrue(ship.okToPlaceShipAt(0, 4, true, ocean));
        assertFalse(ship.okToPlaceShipAt(0, 2, true, ocean));
        assertFalse(ship.okToPlaceShipAt(2, 0, false, ocean));
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		// test overlap (same position)
		Battleship battleship3 = new Battleship();
		row = 0;
		column = 4;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to overlap with existing ship.");
		// test diagonal adjacent
		Battleship battleship5 = new Battleship();
		row = 1;
		column = 5;
		horizontal = true;
		boolean ok5 = battleship5.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "Not OK to place ship diagonally adjacent.");
		// test far away (valid placement)
		Battleship battleship6 = new Battleship();
		row = 5;
		column = 5;
		horizontal = true;
		boolean ok6 = battleship6.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok6, "OK to place ship far away.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		// test vertical placement
    	Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		assertEquals(cruiser, ocean.getShipArray()[5][6]);
		assertEquals(cruiser, ocean.getShipArray()[4][6]);
		assertEquals(cruiser, ocean.getShipArray()[3][6]);
		// test empty location
		assertEquals("empty", ocean.getShipArray()[9][9].getShipType());
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		// test shooting another part of horizontal ship
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		// test shooting all remaining parts
		assertTrue(battleship.shootAt(0, 7));
		assertTrue(battleship.shootAt(0, 6));
		boolean[] hitArray3 = {true, true, true, true};
		assertArrayEquals(hitArray3, battleship.getHit());
		// after ship is sunk, shootAt should return false
		assertTrue(battleship.isSunk());
		assertFalse(battleship.shootAt(0, 9));
		// test vertical ship
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.shootAt(5, 5));
		boolean[] cruiserHit1 = {true, false, false};
		assertArrayEquals(cruiserHit1, cruiser.getHit());
		assertTrue(cruiser.shootAt(4, 5));
		boolean[] cruiserHit2 = {true, true, false};
		assertArrayEquals(cruiserHit2, cruiser.getHit());
		assertFalse(cruiser.shootAt(5, 6));
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		// test submarine becomes sunk after one correct hit
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		// test battleship is not sunk until all 4 parts are hit
		Ship battleship = new Battleship();
		row = 0;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 9));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 8));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 7));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(0, 6));
		assertTrue(battleship.isSunk());
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		// test still not sunk after partial hits
		battleship.shootAt(8, 1);
		assertEquals("x", battleship.toString());
		battleship.shootAt(7, 1);
		assertEquals("x", battleship.toString());
		// sunk after all parts hit
		battleship.shootAt(6, 1);
		assertEquals("s", battleship.toString());
		// test submarine 
		Ship submarine = new Submarine();
		submarine.placeShipAt(3, 3, true, ocean);
		assertEquals("x", submarine.toString());
		submarine.shootAt(3, 3);
    	assertEquals("s", submarine.toString());
	}

}
