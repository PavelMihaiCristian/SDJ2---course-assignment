package clintmodel;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Customer.Customer;
import Customer.CustomerFactory;
import Rentals.Rental;
import Rentals.RentalList;
import Rentals.RentalListAdaptor;
import Vehicles.Vehicle;
import Vehicles.VehicleList;
import serverModel.ServerInterface;
import util.Date;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class ClientModelAdministrator implements ClientModelInterface {

	private RentalList rentalList;
	private VehicleList vehicleList;
	
	public ClientModelAdministrator(RentalList rentalList, VehicleList vehicleList) {
		this.vehicleList=vehicleList;
		this.rentalList=rentalList;
	}

	public void setRentalList(RentalList rentals){
		this.rentalList=rentals;
	}
	
	public void setVehicleList(VehicleList vehicles){
		this.vehicleList=vehicles;
	}
	
	public VehicleList getAvailableVehicles(Date startDate, Date endDate) {
		
		VehicleList availableVehicles = new VehicleList();

		for (int i = 0; i < vehicleList.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < rentalList.size(); j++) {
				if (vehicleList.getVehicleByIndex(i).equals(
						rentalList.getRentalAtIndex(j).getVehicle())) {
					Date begin = rentalList.getRentalAtIndex(j).getPickUpTime();
					Date end = rentalList.getRentalAtIndex(j).getReturnTime();
					if (begin.isBefore(endDate) && !(begin.isBefore(startDate))) {
						flag = false;
						break;
					}
					if (end.isBefore(endDate) && !(end.isBefore(startDate))) {
						flag = false;
						break;
					}
					if (begin.isBefore(startDate) && !(end.isBefore(endDate))) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				availableVehicles.addVehicle(vehicleList.getVehicleByIndex(i));
			}
		}
		return availableVehicles;
	}

	@Override
	public Vehicle[] getFamilyCars( Date startDate, Date endDate) {
		VehicleList temp=getAvailableVehicles(startDate, endDate);
		return temp.getFamilyCars();
	}

	@Override
	public Vehicle[] getMovingTrucks( Date startDate, Date endDate) {
		VehicleList temp=getAvailableVehicles(startDate, endDate);
		return temp.getMovingTrucks();
	}

	@Override
	public Vehicle[] getVans( Date startDate, Date endDate) {
		VehicleList temp=getAvailableVehicles(startDate, endDate);
		return temp.getVans();
	}

	@Override
	public Vehicle[] getAutoCamper(Date startDate, Date endDate) {
		VehicleList temp=getAvailableVehicles(startDate, endDate);
		return temp.getAutoCampers();
	}

	@Override
	public boolean reserveAVehicle(  Date startDate,
			Date endDate, String fName, String lName, String address,
			String phone, Vehicle vehicle, String pickUpLocation,
			String deliveryLocation) {
		Rental rental = new Rental(vehicle, fName, lName, null, address, phone,
				startDate, endDate, pickUpLocation, deliveryLocation);
		if (rentalList.checkRentalAvailability(rental)) {
			rentalList.addRental(rental);
			return true;
		} else
			return false;
	}

	@Override
	public double returnAVehicle( Date startDate,
			String registrationNumber, int mileage) {
		double price = 0;
		int oldMileage;
		for (int i = 0; i < rentalList.size(); i++) {
			if (rentalList.getRentalAtIndex(i).getPickUpTime().equals(startDate)
					&& rentalList.getRentalAtIndex(i).getVehicle().getRegNo()
							.equals(registrationNumber)) {
				oldMileage = rentalList.getRentalAtIndex(i).getVehicle()
						.getMileage();
				rentalList.getRentalAtIndex(i).getVehicle().setMileage(mileage);
				price = rentalList.getRentalAtIndex(i).calculateRentalPrice(mileage - oldMileage);
				rentalList.deleteRentalAtIndex(i);
			}
		}
		return price;
	}

	@Override
	public RentalList getRentals(Date startDate, Date endDate) {
		RentalList specificRentals = new RentalList();

		for (int j = 0; j < rentalList.size(); j++) {
			boolean flag = false;
			Date begin = rentalList.getRentalAtIndex(j).getPickUpTime();
			Date end = rentalList.getRentalAtIndex(j).getReturnTime();
			if (begin.isBefore(endDate) && !(begin.isBefore(startDate))) {
				flag = true;
			}
			if (end.isBefore(endDate) && !(end.isBefore(startDate))) {
				flag = true;
			}
			if (begin.isBefore(startDate) && !(end.isBefore(endDate))) {
				flag = true;
			}
			if (flag) {
				specificRentals.addRental(rentalList.getRentalAtIndex(j));
			}
		}
		return specificRentals;
	}
	/* 
	/**
	 * check if the vehicle is booked within period
	 *   Booking     Start |-------------------| End
    *  case0       Start |-------------------| End
    *  case1             Start|-----------|End
    *  case2  Start|-------------------------------|End
    *  case3  Start|-----------|End SAME LIKE CASE 0
    *  case4                      Start|------------|End
    * if we don't want to rent the vehicle the day it comes back to check its situation:
    * if startDateFromSearch.equals(endDate)
	 * @param start Date From Search
	 * @param end Date From Search
	 * @return boolean if the vehicle is booked 
	 
	

public boolean isBooked(MyDate startDateFromSearch, MyDate endDateFromSearch){
	if(startDateFromSearch.equals(endDate)||startDate.equals(startDateFromSearch)||endDateFromSearch.equals(endDate)||
	      startDate.isBefore(startDateFromSearch)&&endDateFromSearch.isBefore(endDate)||
	      startDateFromSearch.isBefore(startDate)&&endDate.isBefore(endDateFromSearch)||
	      startDateFromSearch.isBefore(startDate)&&startDate.isBefore(endDateFromSearch)||
	      startDateFromSearch.isBefore(endDate)&&endDate.isBefore(endDateFromSearch)){
	 
		return true;
		
	}
	else return false;
}
	 */

	@Override
	public boolean cancelReservation(  Date beginDate,String regNo) {
		boolean flag = false;
		for (int i = 0; i < rentalList.size(); i++) {
			Date startDate = rentalList.getRentalAtIndex(i).getPickUpTime();
			String regNumber = rentalList.getRentalAtIndex(i).getVehicle()
					.getRegNo();
			if (beginDate.equals(startDate) && regNo.equals(regNumber)) {
				flag = true;
				rentalList.deleteRentalAtIndex(i);
			}
		}
		return flag;
	}

	@Override
	public boolean addLicenseNumber(String licenseNumber,Date beginDate, String regNo) {
		boolean flag = false;
		for (int i = 0; i < rentalList.size(); i++) {
			Date startDate = rentalList.getRentalAtIndex(i).getPickUpTime();
			String regNumber = rentalList.getRentalAtIndex(i).getVehicle()
					.getRegNo();
			if (beginDate.equals(startDate) && regNo.equals(regNumber)) {
				flag = true;
				rentalList.getRentalAtIndex(i).getCustomer()
						.setLicenseNumber(licenseNumber);
			}
		}
		return flag;
	}

	@Override
	public RentalList getRentalList() {
		return rentalList;
	}

	@Override
	public VehicleList getVehicleList() {
		return vehicleList;
	}

}
