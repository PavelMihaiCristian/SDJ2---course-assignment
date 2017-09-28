package clintmodel;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Rentals.RentalList;
import Vehicles.Vehicle;
import Vehicles.VehicleList;
import serverModel.ServerInterface;
import util.Date;
import util.LogInEXception;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class ClientModelUsers implements ClientModelInterface
{  
   private ClientModelAdministrator admin;
   private LogInEXception login;
   
   
	public ClientModelUsers(RentalList rentalList, VehicleList vehicleList) 
	{
	   admin = new ClientModelAdministrator(rentalList,vehicleList);
	   login = new LogInEXception("Access Denied");
	}

	public void setRentalList(RentalList rentals){
		admin.setRentalList(rentals);
	}
	
	public void setVehicleList(VehicleList vehicles){
		admin.setVehicleList(vehicles);
	}
	
   @Override
   public VehicleList getAvailableVehicles(Date begin, Date end)
   {
      return admin.getAvailableVehicles(begin, end);
   }

   @Override
   public Vehicle[] getMovingTrucks(Date startDate, Date endDate)
   {
      throw login;
   }

   @Override
   public Vehicle[] getVans(Date startDate, Date endDate)
   {
      throw login;
   }

   @Override
   public Vehicle[] getAutoCamper(Date startDate, Date endDate)
   {
      throw login;
   }

   @Override
   public boolean reserveAVehicle(Date startDate,Date endDate,String fName, String lName,
           String address, String phone,Vehicle vehicle,
		   String pickUpLocation,String deliveryLocation)
   {
      throw login;
   }

   @Override
   public double returnAVehicle(Date startDate,String registrationNumber,int mileage)
   {
      throw login;
   }

   @Override
   public Vehicle[] getFamilyCars(Date startDate, Date endDate)
   {
      throw login;
   }

   @Override
   public RentalList getRentals(Date startDate, Date endDate)
   {
      return admin.getRentals(startDate, endDate);
   }

	@Override
	public boolean cancelReservation(Date beginDate,String regNo) 
	{
		throw login;
	}
	
	@Override
	public boolean addLicenseNumber(String licenseNumber,Date beginDate,String regNo) 
	{
		throw login;
	}

	@Override
	public RentalList getRentalList() {
		// TODO Auto-generated method stub
		return admin.getRentalList();
	}

	@Override
	public VehicleList getVehicleList() {
		return admin.getVehicleList();
	}
}
