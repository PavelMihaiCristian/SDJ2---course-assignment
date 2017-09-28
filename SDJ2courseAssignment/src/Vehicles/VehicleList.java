package Vehicles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Vehicle objects.
 * @author Vito Ilchev
 * @version 1.0
 */
public class VehicleList implements Serializable {

	private ArrayList<Vehicle> vehicles;

	/**
	 * A no argument constructor that initializes the vehicleList.
	 */
	public VehicleList() {
		vehicles = new ArrayList<Vehicle>();
	}

	/**
	 * Gets how many objects are in the list.
	 * @return the amount of Objects in the Vehicle class.
	 */
	public int size() {
		return vehicles.size();
	}

	/**
	 * A get vehicle by registration number.
	 * @param regNo
	 * @return the vehicles by registration number.
	 */
	public Vehicle getVehicleByRegNo(String regNo) {
		Vehicle temp = null;
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getRegNo().equals(regNo)) {
				temp = vehicles.get(i);
				break;
			}
		}
		return temp;
	}

	/**
	 * A get vehicle by index method.
	 * @param index
	 * @return the vehicle by index.
	 */
	public Vehicle getVehicleByIndex(int index) {
		if (index < vehicles.size()) {
			return vehicles.get(index);
		} else
			return null;
	}

	/**
	 * A getter for Family Cars.
	 * @return the family car vehicles from the Vehicle list.
	 */
	public FamilyCar[] getFamilyCars() {
		ArrayList<FamilyCar> nv = new ArrayList<FamilyCar>();
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i) instanceof FamilyCar) {
				nv.add((FamilyCar) vehicles.get(i));
			}
		}
		FamilyCar[] temp = new FamilyCar[nv.size()];
		return nv.toArray(temp);
	}

	/**
	 * A getter for Vans.
	 * @return the vehicles classified as vans from the Vehicle list.
	 */
	public Van[] getVans() {
		ArrayList<Van> vans = new ArrayList<Van>();
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i) instanceof Van) {
				vans.add((Van) vehicles.get(i));
			}
		}
		Van[] temp = new Van[vans.size()];
		return vans.toArray(temp);
	}

	/**
	 * A getter for Moving Trucks.
	 * @return the vehicles specified as moving trucks from the vehicle list.
	 */
	public Vehicle[] getMovingTrucks() {
		ArrayList<MovingTruck> mt = new ArrayList<MovingTruck>();
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i) instanceof MovingTruck) {
				mt.add((MovingTruck) vehicles.get(i));
			}
		}
		MovingTruck[] temp = new MovingTruck[mt.size()];
		return mt.toArray(temp);
	}

	/**
	 * A getter for the Auto Campers.
	 * @return the vehicles identified as auto campers from the vehicle list.
	 */
	public Vehicle[] getAutoCampers() {
		ArrayList<AutoCamper> ac = new ArrayList<AutoCamper>();
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i) instanceof AutoCamper) {
				ac.add((AutoCamper) vehicles.get(i));
			}
		}
		AutoCamper[] temp = new AutoCamper[ac.size()];
		return ac.toArray(temp);
	}

	public void addVehicle(Vehicle vehicle){
		vehicles.add(vehicle);
	}
	
	public void removeVehicle(String regNo){
		vehicles.remove(regNo);
	}
	/**
	 * Gets a String representation of the Vehicle List.
	 * @return a String containing information about all the vehicles objects.
	 */
	public String toString() {
	   String str = "";
	   for(int i = 0; i < vehicles.size(); i++)
	   {
	      str += vehicles.get(i) + "\n";
	   }
	   return str;
	}

}
