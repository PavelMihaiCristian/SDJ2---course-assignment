package serverModel;

import java.io.Serializable;
import java.rmi.RemoteException;

import util.Date;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;
import utility.observer.RemoteSubjectDelegate;
import Adaptor.VehicleAdapter;
import Rentals.RentalList;
import Rentals.RentalListAdaptor;
import Vehicles.VehicleList;

public class ServerModel{

	private VehicleAdapter adapter;
	private RentalListAdaptor rentalsAdapter;
	
	public ServerModel() {
		adapter=new VehicleAdapter("vehicles.bin");
		rentalsAdapter=new RentalListAdaptor("rentals.bin");
		
	}
	
	public synchronized VehicleList getAllVehicle() throws RemoteException {
		
		return adapter.getAllVehicles();
	}

	public synchronized RentalList getAllRentals() throws RemoteException {
		
		return rentalsAdapter.getAllRentals();
	}

	public synchronized void saveVehicles(VehicleList vehicleList){
	      adapter.saveVehicles(vehicleList);
	}
	
	public synchronized void saveRentals(RentalList rentalList)
	{
	   rentalsAdapter.saveRentalList(rentalList);
	}
}
