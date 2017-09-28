package clintmodel;

import Rentals.RentalList;
import Vehicles.Vehicle;
import Vehicles.VehicleList;
import util.Date;

public interface ClientModelInterface
{
   public VehicleList getAvailableVehicles(Date begin, Date end);
   public Vehicle[] getMovingTrucks(Date startDate, Date endDate );
   public Vehicle[] getVans( Date startDate, Date endDate);
   public Vehicle[] getAutoCamper( Date startDate, Date endDate);
   public boolean reserveAVehicle( Date startDate,Date endDate,String fName, String lName,
           String address, String phone,Vehicle vehicle,
		   String pickUpLocation,String deliveryLocation);
   public double returnAVehicle( Date startDate,String registrationNumber,int mileage);
   public Vehicle[] getFamilyCars( Date startDate, Date endDate);
   public RentalList getRentals(Date startDate, Date endDate);
   public boolean cancelReservation( Date beginDate,String regNo);
   public boolean addLicenseNumber(String licenseNumber, Date beginDate,String regNo);
   public void setRentalList(RentalList rentals);
   public void setVehicleList(VehicleList vehicles);
   public RentalList getRentalList();
   public VehicleList getVehicleList();
}

