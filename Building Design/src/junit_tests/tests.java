package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Unit;
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

}
