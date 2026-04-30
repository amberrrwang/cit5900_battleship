package battleship;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	public void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	public void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	public void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5)); //Tests that its occupied 

		//More tests 
		assertTrue(ocean.isOccupied(0, 0));   //Test that its occupied by submarine
		assertTrue(ocean.isOccupied(0, 5));   //Test second part of destroyer

		assertFalse(ocean.isOccupied(9, 9));  //Test empty space
		assertFalse(ocean.isOccupied(2, 2));  //Test empty space

		//Fresh ocean
		Ocean newOcean = new Ocean(); //New object
		assertFalse(newOcean.isOccupied(3, 3)); //Should not have a ship on this new ocean

		//New ship placement
		Submarine sub2 = new Submarine(); //New object 
		sub2.placeShipAt(4, 4, false, newOcean); //Places submarine 
		assertTrue(newOcean.isOccupied(4, 4)); //Should be occupied from this new submarine 
	}

	@Test
	public void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//More tests
		assertTrue(destroyer.isSunk()); //Making sure the destroyer is sunk after last shot 
		assertFalse(ocean.shootAt(9, 9)); //Testing if it stays false with miss
		assertFalse(ocean.shootAt(0, 5)); //Now should be false 

		assertFalse(ocean.shootAt(1, 5)); //Hitting a ship that is already sunk should return false 

		//Add submarine 
		Submarine sub = new Submarine();
		sub.placeShipAt(5, 5, false, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertTrue(sub.isSunk()); //Submarine length = 1, so should be sunk 
	}

	@Test
	public void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//More Tests 
		ocean.shootAt(1, 5); //shooting at the same spot 
		assertEquals(7, ocean.getShotsFired()); //making sure it's the correct amount of shots fired 

		//Fresh ocean
		Ocean newOcean = new Ocean(); //Creates new object
		newOcean.shootAt(2, 2); //Shoots at fresh ocean 
		assertEquals(1, newOcean.getShotsFired()); //One shot on new ocean 
		
	}

	@Test
	public void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//More tests
		assertTrue(ocean.shootAt(0, 5));  //Second hit at this spot
		assertEquals(2, ocean.getHitCount()); //Should be equal to two
		
		assertFalse(ocean.shootAt(9, 9)); //Making sure a miss does not increase hit count
		assertEquals(2, ocean.getHitCount()); //Should stay at 2 if it's a miss 

		//Submarine test 
		Submarine sub = new Submarine(); //Create new object 
		sub.placeShipAt(4, 4, false, ocean); //Place submarine 

		assertTrue(ocean.shootAt(4, 4)); //Shoot at 4,4 
		assertEquals(3, ocean.getHitCount()); //Should equal 3 total now 
	}
	
	@Test
	public void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//More tests
		assertTrue(ocean.shootAt(0, 5));  //Destroyer should sink 
		assertTrue(destroyer.isSunk()); //Confirming destroyer is sunk 
		assertEquals(1, ocean.getShipsSunk()); //One ship should be sunk 
		
		ocean.shootAt(1, 5); //Second hit 
		assertEquals(1, ocean.getShipsSunk()); //Making sure the sunken ship number doesn't increase 

		//Add submarine 
		Submarine sub = new Submarine(); //New object
		sub.placeShipAt(3, 3, false, ocean); //Place submarine 

		assertTrue(ocean.shootAt(3, 3)); //Should sink submarine 
		assertEquals(2, ocean.getShipsSunk()); //Submarine = 1 so confirms submarine is sunk 
	}

	@Test
	public void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//More tests
		Destroyer destroyer = new Destroyer(); //Create new object 
		destroyer.placeShipAt(1, 5, false, ocean); //place destroyer at 1, 5 

		assertEquals("destroyer", shipArray[1][5].getShipType()); //The destroyer should be at this spot
		assertEquals("destroyer", shipArray[0][5].getShipType()); //The destoryer should also be at this spot

		//Add submarine
		Submarine sub = new Submarine(); //New object
		sub.placeShipAt(2, 2, false, ocean); //Place submarine 

		assertEquals("submarine", shipArray[2][2].getShipType()); //There should be a submarine at this location
	}

}
