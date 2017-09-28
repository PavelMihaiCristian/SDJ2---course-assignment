package Adaptor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import util.Date;
import Rentals.RentalList;
import Rentals.RentalListAdaptor;
import Vehicles.Vehicle;
import Vehicles.VehicleList;

/**
 * a class used to write and read from a bin file the vehicle list
 * 
 * @author vito ilchev
 * @version 1.0
 */
public class VehicleAdapter implements Serializable{

	private MyFileIO mfio;
	private String fileName;

	/**
	 * a one-argument constructor
	 * 
	 * @param fileName
	 *            the file name that we will use for the vehicle list
	 */
	public VehicleAdapter(String fileName) {
		mfio = new MyFileIO();
		this.fileName = fileName;
	}

	/**
	 * a method for reading the object Vehicle List from the bin file
	 * 
	 * @return a VehicleList object
	 */
	public VehicleList getAllVehicles() {
		VehicleList vehicles = null;

		try {
			vehicles = (VehicleList) mfio.readObjectFromFile(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Error reading file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
		}
		return vehicles;
	}

	

	/**
	 * a get method for all vehicles of type FamilyCar
	 * 
	 * @return a array of Vehicle type that contains all Family Cars
	 */
	public Vehicle[] getFamilyCars() {
		VehicleList vehicles = getAllVehicles();
		Vehicle[] temp = vehicles.getFamilyCars();
		return temp;
	}

	/**
	 * a get method for all vehicles of type Van
	 * 
	 * @return a array of Vehicle type that contains all vans
	 */
	public Vehicle[] getVans() {
		VehicleList vehicles = getAllVehicles();
		Vehicle[] temp = vehicles.getVans();
		return temp;
	}

	/**
	 * a get method for all vehicles of type MovingTrucks
	 * 
	 * @return a array of Vehicle type that contains all moving trucks
	 */
	public Vehicle[] getMovingTrucks() {
		VehicleList vehicles = getAllVehicles();
		Vehicle[] temp = vehicles.getMovingTrucks();
		return temp;
	}

	/**
	 * a get method for all vehicles of type AutoCamper
	 * 
	 * @return a array of Vehicle type that contains all auto campers
	 */
	public Vehicle[] getAutoCamper() {
		VehicleList vehicles = getAllVehicles();
		Vehicle[] temp = vehicles.getAutoCampers();
		return temp;
	}

	/**
	 * a method for saving to the bin file the VehicleList object
	 * 
	 * @param vehicles
	 *            the VehicleList object that will be saved
	 */
	public void saveVehicles(VehicleList vehicles) {
		try {
			mfio.writeToFile(fileName, vehicles);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Error writing to file");
		}
	}

	/**
	 * a method for adding a vehicle to the vehicle list and after saving the
	 * list
	 * 
	 * @param vehicle
	 *            the vehicle that will be added to the list
	 */
	public void addVehicle(Vehicle vehicle) {
		VehicleList temp = getAllVehicles();
		temp.addVehicle(vehicle);

		saveVehicles(temp);
	}

	/**
	 * a method for deleting a vehicle from the list that matches the reg number
	 * received as a parameter
	 * 
	 * @param regNo
	 *            the registration number of the vehicle that will be deleted
	 *            from the list
	 */
	public void deleteVehicle(String regNo) {
		VehicleList del = getAllVehicles();
		del.removeVehicle(regNo);

		saveVehicles(del);
		getAllVehicles();
	}

	/**
	 * a method for that will be used for changing the rental price of a vehicle
	 * 
	 * @param vehicle
	 *            the vehicle that will have its price changed
	 * @param price
	 *            the price that will become the new rental price of the vehicle
	 *            given as a parameter
	 */
	public void changePriceOfVehicle(Vehicle vehicle, double price) {
		VehicleList all = getAllVehicles();
		for (int i = 0; i < all.size(); i++) {
			if (all.getVehicleByIndex(i).equals(vehicle)) {
				all.getVehicleByIndex(i).setPrice(price);
				break;
			}
		}
		saveVehicles(all);
		getAllVehicles();
	}

}
