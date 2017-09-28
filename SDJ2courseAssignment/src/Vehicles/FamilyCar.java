package Vehicles;

import java.io.Serializable;

/**
 * A class for the family cars
 * @author Vito Ilchev
 * @version 1.0
 */
public class FamilyCar extends Vehicle implements Serializable{
	/**
	 * A constructor that initializes all the fields by calling the constructor
	 * from the superClass Vehicle.
	 * @param make for the family car
	 * @param model for the family car
	 * @param year for the family car
	 * @param color for the family car
	 * @param regNo for the family car
	 * @param fuelType for the family car
	 * @param transmission for the family car
	 * @param mileage for the family car
	 * @param location for the family car
	 * @param price for the family car
	 */
	public FamilyCar(String make, String model, int year, String color,
			String regNo, String fuelType, int mileage, String transmission,
			String location, double price) {
		super(make, model, year, color, regNo, fuelType, mileage, transmission,
				location, price);
	}

	/**
	 * A toString method.
	 * @return a String extending the superClass Vehicle parameters.
	 */
	public String toString() {
		return super.toString();
	}

	/**
	 * An equals method that renders the equality of Objects.
	 * @return a boolean showing, if one object is equal to another.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof FamilyCar)) {
			return false;
		}
		FamilyCar other = (FamilyCar) obj;

		return super.equals(other);
	}
}
