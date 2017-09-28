package Vehicles;

import java.io.Serializable;

/**
 * A class for the moving trucks.
 * @author Vito Ilchev
 * @version 1.0
 */
public class MovingTruck extends Vehicle implements Serializable{

	private double loadSize;

	/**
	 * A constructor that initializes all the fields by calling the constructor
	 * from the superClass Vehicle, and the double parameter loadSize.
	 * @param make for the moving trucks
	 * @param model for the moving trucks
	 * @param year for the moving trucks
	 * @param color for the moving trucks
	 * @param regNo for the moving trucks
	 * @param fuelType for the moving trucks
	 * @param transmission for the moving trucks
	 * @param mileage for the moving trucks
	 * @param location for the moving trucks
	 * @param price for the moving trucks
	 * @param loadSize for the moving trucks
	 */
	public MovingTruck(String make, String model, int year, String color,
			String regNo, String fuelType, int mileage, String transmission,
			String location, double price, double loadSize) {
		super(make, model, year, color, regNo, fuelType, mileage, transmission,
				location, price);
		this.loadSize = loadSize;
	}
	
	public double getLoadSize()
	{
	   return loadSize;
	}

	/**
	 * A toString method
	 * @return a String for the objects of the superClass Vehicle and the
	 *         parameter of the class MovingTrucks, loadSize.
	 */
	public String toString() {
		return super.toString() + ", " + loadSize;
	}

	/**
	 * An equals method that renders the 'equality' of objects.
	 * @return a boolean showing, if one object is equal to another.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof MovingTruck)) {
			return false;
		}
		MovingTruck other = (MovingTruck) obj;

		return super.equals(other) && loadSize == other.loadSize;
	}

}
