package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Unit;
import model.Blueprint;
import model.Floor;
import model.InsufficientFloorSpaceException;

public class tests {

	@Test
	public void test_unit_01() {
		/*
		 * Create a new unit with its intended function and dimension (width by length).
		 * width and length are measured in feet.
		 * value of feet is specified as an integer.  
		 */   
		Unit unit = new Unit("Master Room", 14, 9);
		assertEquals("A unit of 126 square feet (14' wide and 9' long) functioning as Master Room", unit.toString());
	}
	
	@Test
	public void test_unit_02() {
		/*
		 * Create a new unit with its intended function and dimension (width by length). 
		 */
		Unit unit = new Unit("Master Room", 14, 9);
		
		/* 
		 * Change the measurement from feet to meters.
		 * Notes:
		 * One foot is equal to 0.3048 meter.
		 * Each value of meters and square meters should be displayed with 2 digits after the decimal point.
		 */
		unit.toogleMeasurement();
		
		assertEquals("A unit of 11.71 square meters (4.27 m wide and 2.74 m long) functioning as Master Room", unit.toString());
		

		 // Change the measurement from meters to feet. 
		unit.toogleMeasurement();
		assertEquals("A unit of 126 square feet (14' wide and 9' long) functioning as Master Room", unit.toString());
	}
	
	@Test
	public void test_unit_03() {
		/*
		 * Create three new units with their intended functions and dimensions. 
		 */
		Unit u1 = new Unit("Master Bedroom", 14, 9);
		Unit u2 = new Unit("Master Bedroom", 18, 7);
		Unit u3 = new Unit("Master Bedroom", 18, 8);
		Unit u4 = new Unit("Office", 18, 7); 
		
		assertEquals("A unit of 126 square feet (14' wide and 9' long) functioning as Master Bedroom", u1.toString());
		assertEquals("A unit of 126 square feet (18' wide and 7' long) functioning as Master Bedroom", u2.toString());
		assertEquals("A unit of 144 square feet (18' wide and 8' long) functioning as Master Bedroom", u3.toString());
		assertEquals("A unit of 126 square feet (18' wide and 7' long) functioning as Office", u4.toString());
		
		/*
		 * Two units are considered equal if their intended functions are the same (case-sensitive)
		 * 	and the areas (in square feet) are the same (even if the dimensions may be different). 
		 */
		assertEquals(u1, u2);
		assertNotEquals(u1, u3);
		assertNotEquals(u1, u4);
		assertNotEquals(u2, u3);
		assertNotEquals(u2, u4);
	}
	
	/*
	 * Tests related to the Floor class.
	 */ 
	
	@Test
	public void test_floor_01() {
		/*
		 * Create a floor with some fixed capacity measured in square feet.
		 */
		Floor f = new Floor(500); //not size of the array, summartino of area on the floor
		assertEquals("Floor's utilized space is 0 sq ft (500 sq ft remaining): []", f.toString());
	}
	
	@Test
	public void test_floor_02() {
		/*
		 * Create a floor with some fixed capacity measured in square feet.
		 */
		Floor f = new Floor(500);
		try { 
			/*
			 * Add units into a floor (which will not exceed the specified maximum capacity).
			 * The dimension of the unit is specified in feet.
			 * maximum number of units allowed for a floor (no error handling needed)
			 */
			f.addUnit("Master Bedroom", 18, 8);
			assertEquals("Floor's utilized space is 144 sq ft (356 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8')]", f.toString());
			f.addUnit("Office", 18, 7);
			assertEquals("Floor's utilized space is 270 sq ft (230 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8'), Office: 126 sq ft (18' by 7')]", f.toString());
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}
		
		/* 
		 * Attempting to add to the floor a new unit which would result in the specified maximum capacity being exceeded,
		 * 	in which case an exception is thrown and no change should be made the floor. 
		 */
		try { 
			assertEquals("Floor's utilized space is 270 sq ft (230 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8'), Office: 126 sq ft (18' by 7')]", f.toString());
			f.addUnit("Kitchen", 29, 8);
			fail("Expected exception not thrown");
		}
		catch(InsufficientFloorSpaceException e) {
			/* Expected exception thrown and no change has been made to the floor. */
			assertEquals("Floor's utilized space is 270 sq ft (230 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8'), Office: 126 sq ft (18' by 7')]", f.toString());
		}
	}
	
	@Test 
	public void test_floor_03() {
		/*
		 * Create two floor with some fixed capacities measured in square feet.
		 */
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(750);
		
		/*
		 * Two floors are not considered equal if their maximum capacities are different. 
		 */
		assertNotEquals(f1, f2); //does f1.euqlas(f2)
	}
	
	@Test 
	public void test_floor_04() {
		/*
		 * Create two floor with some fixed capacities measured in square feet.
		 */
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(500);
		
		/*
		 * Two floors are considered equal if: 
		 * -their maximum capacities are the same; and
		 * -their spaces are utilized in the same way 
		 * 	(meaning that for each added unit in one floor, we can find its equivalent in the other floor)
		 *  the orders in which units are added to the two floors do not matter.   
		 */
		assertEquals(f1, f2);
		
		try { 
			f1.addUnit("Master Bedroom", 14, 9);
			f1.addUnit("Master Bedroom", 14, 9);
			f1.addUnit("Office", 8, 12);
			f1.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (14' by 9'), Master Bedroom: 126 sq ft (14' by 9'), Office: 96 sq ft (8' by 12'), Kitchen: 90 sq ft (9' by 10')]", f1.toString());
			
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Office", 16, 6);
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (7' by 18'), Office: 96 sq ft (16' by 6'), Master Bedroom: 126 sq ft (7' by 18'), Kitchen: 90 sq ft (9' by 10')]", f2.toString());
			
			/*
			 * Floors f1 and f2 are utilized in the same way (despite the added orders of units):
			 * 	+ 2 master bedrooms, each of 126'
			 *  + 1 office of 96'
			 *  + 1 kitchen of 90' 
			 */
			assertEquals(f1, f2);
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}

	}
	
	@Test 
	public void test_floor_05a() {
		
		// Create two floor with some fixed capacities measured in square feet.
		 
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(500);
		 
		try { 
			f1.addUnit("Master Bedroom", 14, 9); 
			f1.addUnit("Office", 8, 12);
			f1.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 312 sq ft (188 sq ft remaining): [Master Bedroom: 126 sq ft (14' by 9'), Office: 96 sq ft (8' by 12'), Kitchen: 90 sq ft (9' by 10')]", f1.toString());
			
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Office", 16, 6);
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (7' by 18'), Office: 96 sq ft (16' by 6'), Master Bedroom: 126 sq ft (7' by 18'), Kitchen: 90 sq ft (9' by 10')]", f2.toString());
			
			
			//Floors f1 and f2 are utilized differently (despite the added orders of units):
			//+ In f2 one of the master bedrooms of 126 sq ft cannot be found for its equivalent in f1.   
			
			assertNotEquals(f1, f2);
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}

	}
	
	@Test 
	public void test_floor_05b() {
		/*
		 * Create two floor with some fixed capacities measured in square feet.
		 */
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(500);
		 
		try { 
			f1.addUnit("Master Bedroom", 14, 9);
			f1.addUnit("Master Bedroom", 13, 9);
			f1.addUnit("Office", 8, 12);
			f1.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 429 sq ft (71 sq ft remaining): [Master Bedroom: 126 sq ft (14' by 9'), Master Bedroom: 117 sq ft (13' by 9'), Office: 96 sq ft (8' by 12'), Kitchen: 90 sq ft (9' by 10')]", f1.toString());
			
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Office", 16, 6);
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (7' by 18'), Office: 96 sq ft (16' by 6'), Master Bedroom: 126 sq ft (7' by 18'), Kitchen: 90 sq ft (9' by 10')]", f2.toString());
			
			/*
			 * Floors f1 and f2 are utilized differently (despite the added orders of units):
			 * 	+ f1 has two master bedrooms of different sizes
			 * 	+ f2 has two master bedrooms of the same size 
			 */
			assertNotEquals(f1, f2);
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}

	}
	
	/*
	 * Tests related to the Blueprint class.
	 */
	@Test
	public void test_blueprint_01() {
		/*
		 * Create the blueprint for a 7-floor building.
		 */
		Blueprint bp = new Blueprint(7);
		
		/*
		 * Show the percentage of the building blueprint being completed 
		 * 	(based on the number of floor plans added).
		 * The percentage value displayed with 1 digit after the decimal point.
		 */
		assertEquals("0.0 percents of building blueprint completed (0 out of 7 floors)", bp.toString());
		
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(500);
		
		/*
		 * Add units to the two floors, before adding them to the blueprint.
		 */
		try { 
			/*
			 * Error checking on argument values of the `addUnit` method is not needed.
			 */
			f1.addUnit("Master Bedroom", 14, 9);
			f1.addUnit("Master Bedroom", 13, 9);
			f1.addUnit("Office", 8, 12);
			f1.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 429 sq ft (71 sq ft remaining): [Master Bedroom: 126 sq ft (14' by 9'), Master Bedroom: 117 sq ft (13' by 9'), Office: 96 sq ft (8' by 12'), Kitchen: 90 sq ft (9' by 10')]", f1.toString());
			
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Office", 16, 6);
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (7' by 18'), Office: 96 sq ft (16' by 6'), Master Bedroom: 126 sq ft (7' by 18'), Kitchen: 90 sq ft (9' by 10')]", f2.toString());
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}
		
		bp.addFloorPlan(f1);
		assertEquals("14.3 percents of building blueprint completed (1 out of 7 floors)", bp.toString());
		bp.addFloorPlan(f2);
		assertEquals("28.6 percents of building blueprint completed (2 out of 7 floors)", bp.toString());
		
		/*
		 * Retrieve the list of floor plans. 
		 */
		Floor[] fs = bp.getFloors();
		assertEquals(2, fs.length);
		
		/*
		 * The accessor `getFloors` should preserve composition:
		 * 	the returned floor plans are not to be shared with the blueprint `bp`.
		 */
		assertNotSame(fs[0], f1);
		assertEquals(fs[0], f1);
		
		assertNotSame(fs[1], f2);
		assertEquals(fs[1], f2);
		
	}
	
	@Test
	public void test_blueprint_02() {
		/*
		 * Create the blueprint for a 7-floor building.
		 */
		Blueprint bp1 = new Blueprint(7);
		
		/*
		 * Show the percentage of the building blueprint being completed 
		 * 	(based on the number of floor plans added).
		 * The percentage value should be displayed with 1 digit after the decimal point.
		 */
		assertEquals("0.0 percents of building blueprint completed (0 out of 7 floors)", bp1.toString());
		
		Floor f1 = new Floor(500);
		Floor f2 = new Floor(500);
		
		/*
		 * Add units to the two floors, before adding them to the blueprint.
		 */
		try { 
			/*
			 * Error checking on argument values of the `addUnit` method is not needed.
			 */
			f1.addUnit("Master Bedroom", 14, 9);
			f1.addUnit("Master Bedroom", 13, 9);
			f1.addUnit("Office", 8, 12);
			f1.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 429 sq ft (71 sq ft remaining): [Master Bedroom: 126 sq ft (14' by 9'), Master Bedroom: 117 sq ft (13' by 9'), Office: 96 sq ft (8' by 12'), Kitchen: 90 sq ft (9' by 10')]", f1.toString());
			
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Office", 16, 6);
			f2.addUnit("Master Bedroom", 7, 18);
			f2.addUnit("Kitchen", 9, 10);
			assertEquals("Floor's utilized space is 438 sq ft (62 sq ft remaining): [Master Bedroom: 126 sq ft (7' by 18'), Office: 96 sq ft (16' by 6'), Master Bedroom: 126 sq ft (7' by 18'), Kitchen: 90 sq ft (9' by 10')]", f2.toString());
		}
		catch(InsufficientFloorSpaceException e) {
			fail("Unexpected exception thrown");
		}
		
		bp1.addFloorPlan(f1);
		assertEquals("14.3 percents of building blueprint completed (1 out of 7 floors)", bp1.toString());
		bp1.addFloorPlan(f2);
		assertEquals("28.6 percents of building blueprint completed (2 out of 7 floors)", bp1.toString());
		
		/*
		 * Create a new building blueprint from another. 
		 */
		Blueprint bp2 = new Blueprint(bp1);
		
		/*
		 * Retrieve the list of floor plans from the blueprint `bp1`. 
		 */
		Floor[] fs1 = bp1.getFloors();
		assertEquals("28.6 percents of building blueprint completed (2 out of 7 floors)", bp2.toString());
		assertEquals(2, fs1.length);
		
		/*
		 * Retrieve the list of floor plans from the new blueprint `bp2`. 
		 */
		Floor[] fs2 = bp2.getFloors();
		assertEquals("28.6 percents of building blueprint completed (2 out of 7 floors)", bp2.toString());
		assertEquals(2, fs2.length);
		
		/*
		 * The accessor `getFloors` should preserve composition:
		 * 	the returned floor plans are not to be shared with 
		 * 		either the blueprint `bp1` or the blueprint `bp2`.
		 */
		assertNotSame(fs2[0], f1);
		assertEquals(fs2[0], f1);
		
		assertNotSame(fs1[0], fs2[0]);
		assertEquals(fs1[0], fs2[0]);
		
		assertNotSame(fs2[1], f2);
		assertEquals(fs2[1], f2);
		
		assertNotSame(fs1[1], fs2[1]);
		assertEquals(fs1[1], fs2[1]);
	}

}
