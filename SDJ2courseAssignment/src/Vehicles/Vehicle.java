package Vehicles;


import java.io.Serializable;

import Vehicles.Vehicle;
/**
 * @author Vito Ilchev
 * @version 1.0
 */
public class Vehicle implements Serializable{

	private String make;
	private String model;
	private int year;
	private String color;
	private String regNo;
	private String fuelType;
	private int mileage;
	private String transmission;
	private String location;
	private double price;
	//private VehicleState state = AVAILABLE;

	/**
	 * A ten parameter constructor that initializes all the parameters of Vehicles.
	 * @param make of the vehicle
	 * @param model of the vehicle
	 * @param year of the vehicle
	 * @param color of the vehicle
	 * @param regNo of the vehicle
	 * @param fuelType of the vehicle
	 * @param transmission of the vehicle
	 * @param mileage of the vehicle
	 * @param location of the vehicle
	 * @param price of the vehicle
	 */
	public Vehicle(String make, String model, int year, String color,
			String regNo, String fuelType, int mileage, String transmission,
			String location, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.regNo = regNo;
		this.fuelType = fuelType;
		this.mileage = mileage;
		this.transmission = transmission;
		this.location = location;
		this.price = price;
	}

	/**
	 * A setter for the price.
	 * @param price set for the specified vehicle.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * A getter for the price.
	 * @return the price already specified.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * A setter for the mileage. 
	 * @param mileage for the vehicle specified.
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * A getter for the mileage.
	 * @return the mileage already specified.
	 */
	public int getMileage() {
		return mileage;
	}
	/**
	 * A getter for the registration number of a vehicle object.
	 * @return a String with the registration number of the vehicle object.
	 */
	public String getRegNo(){
		return regNo;
	}

	/**
	 * A toString method for printing out all data related to vehicles.
	 * @return a String with all the information for vehicles.
	 */
	public String toString() {
		return  make + "  " + model+ "  " + year + "  " + color+ "  " + regNo	+ "  " + fuelType + "  " +transmission+ "  " + mileage+ "  " + location+ "  " + price;
	}

	/**
	 * An equals method that redefines the equality of objects.
	 * @return a boolean showing, if one object is equal to another.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehicle)) {
			return false;
		}
		Vehicle other = (Vehicle) obj;

		return make.equals(other.make) && model.equals(other.model)
				&& year == other.year && color.equals(other.color)
				&& regNo.equals(other.regNo) && fuelType.equals(other.fuelType)
				&& transmission.equals(other.transmission)
				&& mileage == other.mileage && location.equals(other.location)
				&& price == other.price;

	}
}
