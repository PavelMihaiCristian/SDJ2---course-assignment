package Vehicles;

import java.io.Serializable;

/**
 * A class showing the auto campers
 * @author Vito Ilchev
 * @version 1.0
 */
public class AutoCamper extends Vehicle implements Serializable{

	private int capacity;

	/**
	 * A constructor that initializes all the fields by calling the constructor
	 * from the superClass Vehicle and the integer parameter of AutoCamper,
	 * capacity.
	 * @param make of the auto camper
	 * @param model of the auto camper
	 * @param year of the auto camper
	 * @param color of the auto camper
	 * @param regNo of the auto camper
	 * @param fuelType of the auto camper
	 * @param transmission of the auto camper
	 * @param mileage of the auto camper
	 * @param location of the auto camper
	 * @param price of the auto camper
	 * @param capacity of the auto camper
	 */
	public AutoCamper(String make, String model, int year, String color,
			String regNo, String fuelType, int mileage, String transmission, 
			String location, double price, int capacity) {
		super(make, model, year, color, regNo, fuelType, mileage, transmission, 
				location, price);
		this.capacity = capacity;
	}

	/**
	 * Gets a String representation of the Auto Camper objects.
	 * @return a String showing the objects of the superClass Vehicle and the
	 * parameter of the AutoCamper class.
	 */
	public String toString() {
		return super.toString() + ", " + capacity;
	}

	/**
	 * An equals method, used for comparing objects in the Auto Camper class.
	 * @return a boolean showing, if one object is equal to another.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof AutoCamper)) {
			return false;
		}
		AutoCamper other = (AutoCamper) obj;

		return super.equals(other) && capacity == other.capacity;
	}

}
