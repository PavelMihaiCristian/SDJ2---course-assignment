package Customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.Date;
import Rentals.Rental;
import Vehicles.FamilyCar;
import Vehicles.Vehicle;

public class CustomerFactoryTest {
	private Rental rent1;
	private Vehicle vehicle;
	private Date pickUpTime;
	private Date returnTime;

	@Before
	public void setUp() {
		vehicle = new FamilyCar("Toyota", "Aygo", 2015, "Blue", "CR3453",
				"Patrol", 48000, "Manual", "Horsens", 1000);
		pickUpTime = new Date();
		returnTime = new Date();
		returnTime.nextDays(2);
		rent1 = new Rental(vehicle, "Chris", "Jensen", "CJ123456789",
				"Herningvej 23, Holstebro", "58595652", pickUpTime, returnTime,
				"Horsens", "Horsens");
	}

	@Test
	public void testSameCustomerDifferentBookings() {
		Date pickUpTime1 = new Date();
		Date returnTime1 = new Date();
		returnTime.nextDays(5);
		Rental rent2;
		rent2 = new Rental(vehicle, "Chris", "Jensen", "CJ123456789",
				"Herningvej 23, Holstebro", "58595652", pickUpTime1,
				returnTime1, "Horsens", "Horsens");
		// since they are the same customer
		// They should have the same memory address
		// Test that they are Equal
		assertTrue(rent1.getCustomer() == (rent2.getCustomer()));
		assertTrue(rent1 != rent2);
	}

	@Test
	public void testDifferentCustomers() {
		Rental rent3;
		rent3 = new Rental(vehicle, "Bash", "Jensen", "CJ123456789",
				"Herningvej 23, Holstebro", "58595652", pickUpTime, returnTime,
				"Horsens", "Horsens");
		// Different customers are different objects
		// They will have different memory address
		// Test that they are NOT equal
		assertFalse(rent1.getCustomer() == (rent3.getCustomer()));
	}

}
