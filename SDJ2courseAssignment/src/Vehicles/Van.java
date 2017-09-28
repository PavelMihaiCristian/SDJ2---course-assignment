package Vehicles;

import java.io.Serializable;

/**
 * @author Vito Ilchev
 * @version 1.0
 */
public class Van extends Vehicle implements Serializable{
   
   private double loadSize;
   
	/**
	 * A constructor that initializes all the fields by calling the constructor
	 * from the superClass Vehicle.
	 * 
	 * @param make of the Van
	 * @param model of the Van
	 * @param year of the Van
	 * @param color of the Van
	 * @param regNo of the Van
	 * @param fuelType of the Van
	 * @param transmission of the Van
	 * @param mileage of the Van
	 * @param location of the Van
	 * @param price of the Van
	 */
	public Van(String make, String model, int year, String color, String regNo,
			String fuelType, int mileage, String transmission, String location,
			double price, double loadSize) {
		super(make, model, year, color, regNo, fuelType, mileage, transmission,
				location, price);
		this.loadSize = loadSize;
	}
	/**
	 * a get method for the load size
	 * @return load size of the Van
	 */
	public double getLoadSize()
	{
	   return loadSize;
	}
	
	/**
	 * Gets a representation of the Van objects.
	 * @return a String that shows all the superClass data.
	 */
	public String toString(){
		return super.toString()+" "+loadSize;
	}
	/**
	 * An equals method that compares objects.
	 * @return a boolean showing, if one object is equal to another.
	 */
	public boolean equals(Object obj){
		if(!(obj instanceof Van)){
			return false;
		}
		Van other = (Van) obj;
		
		return super.equals(other) && loadSize==other.loadSize;
	}

}
