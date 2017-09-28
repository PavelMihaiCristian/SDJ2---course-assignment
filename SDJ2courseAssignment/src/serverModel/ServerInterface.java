package serverModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.Date;
import util.LogInEXception;
import utility.observer.RemoteSubject;
import Rentals.Rental;
import Rentals.RentalList;
import Vehicles.Vehicle;
import Vehicles.VehicleList;

public interface ServerInterface extends RemoteSubject<String> 
{
	public VehicleList getAllVehicle() throws RemoteException;
	
	public RentalList getAllRentals() throws RemoteException, LogInEXception;
	
	public void UpdateVehicleList(VehicleList newVList)throws RemoteException;

	public void UpdateRentalList(RentalList newRList)throws RemoteException;
}
