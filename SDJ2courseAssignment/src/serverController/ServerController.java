package serverController;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serverModel.ServerInterface;
import serverModel.ServerModel;
import serverView.ServerView;
import util.Date;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;
import utility.observer.RemoteSubjectDelegate;
import Rentals.RentalList;
import Vehicles.VehicleList;

public class ServerController implements ServerInterface {

	private ServerModel model;
	private ServerView view;
	private RemoteSubjectDelegate rsd;
	private static VehicleList vehicleList;
	private static RentalList rentalList;
	private Date date;

	public ServerController(ServerModel model, ServerView view)
			throws RemoteException {
		this.view = view;
		this.model = model;
		vehicleList = model.getAllVehicle();
		rentalList = model.getAllRentals();
		date = new Date();
		rsd = new RemoteSubjectDelegate<String>((RemoteSubject<String>) this);
	}

	public void addObserver(RemoteObserver<String> arg0) throws RemoteException {
		rsd.addObserver(arg0);
	}

	public void deleteObserver(RemoteObserver<String> arg0)
			throws RemoteException {
		rsd.deleteObserver(arg0);
	}

	public void UpdateVehicleList(VehicleList newVList) throws RemoteException {
		if (!(vehicleList.equals(newVList))) {
			vehicleList = newVList;
			System.out.println("Vehicle List Size in Server: "
					+ vehicleList.size());
			model.saveVehicles(vehicleList);
			rsd.notifyObservers("vehicle "+date.toString());
		}
	}

	public void UpdateRentalList(RentalList newRList) throws RemoteException {
		if (!(rentalList.equals(newRList))) {
			rentalList = newRList;
			System.out.println("Rental List Size in Server: "
					+ rentalList.size());
			model.saveRentals(rentalList);
			rsd.notifyObservers("rental "+date.toString());
		}
	}
	
	public VehicleList getAllVehicle() throws RemoteException {
		view.showMessage("all vehicles were requested by the client");
		return model.getAllVehicle();
	}

	public RentalList getAllRentals() throws RemoteException {
		view.showMessage("all rentals were requested by the client");
		return model.getAllRentals();
	}
}
