package clientController;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import Rentals.RentalList;
import Vehicles.Vehicle;
import Vehicles.VehicleList;
import clintmodel.ClientModelAdministrator;
import clintmodel.ClientModelInterface;
import clintmodel.ClientModelUsers;
import clintview.ClientView;
import serverModel.ServerInterface;
import util.Date;
import util.LogInEXception;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubject;

public class ClientController implements RemoteObserver<String> {

	private ServerInterface server;
	private ClientModelInterface cmi;
	private ClientView view;
	private Date startDate;
	private Date endDate;

	public ClientController() throws RemoteException {
		connect();
		view = new ClientView();
		execute();
	}

	private void connect() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			server = (ServerInterface) Naming
					.lookup("rmi://localhost:1099/V-RENT");
			UnicastRemoteObject.exportObject(this, 0);
			server.addObserver(this);
		} catch (Exception e) {
			System.out.println("SERVER CONNECTION FAILED");
			System.exit(0);
		}
	}

	private void execute() {
		String registrationNumber, licenseNumber;
		int mileage, select;
		double price;
		String pickUpLocation, deliveryLocation, fName, lName, address, phone;
		Vehicle[] specificVehicle = null;
		Vehicle vehicle;
		view.login();
		int choise = view.readIntFromUser();
		if (choise == 1) {
			try {
				cmi = new ClientModelAdministrator(server.getAllRentals(),
						server.getAllVehicle());
			} catch (RemoteException | LogInEXception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				cmi = new ClientModelUsers(server.getAllRentals(),
						server.getAllVehicle());
			} catch (RemoteException | LogInEXception e) {
				System.out.println(e.getMessage());
			}
		}

		do {
			view.menu();
			choise = view.readIntFromUser();
			switch (choise) {
			case 1:
				view.insertDate(1);
				startDate = view.getDate();
				view.insertDate(2);
				endDate = view.getDate();
				view.showMessage(cmi.getAvailableVehicles(startDate, endDate)
						.toString());
				break;
			case 2:
				view.insertDate(1);
				startDate = view.getDate();
				view.insertDate(2);
				endDate = view.getDate();
				view.showMessage(cmi.getRentals(startDate, endDate).toString());
				break;
			case 3:
				view.insertDate(1);
				startDate = view.getDate();
				view.insertDate(2);
				endDate = view.getDate();
				view.showVehicleType();
				select = view.readIntFromUser();
				do {
					switch (select) {
					case 0:
						view.showMessage("Canceled selection");
						break;
					case 1:
						view.showMessage("List of selected vehicle type");
						specificVehicle = cmi.getFamilyCars(startDate, endDate);
						view.printVehicles(specificVehicle);
						break;
					case 2:
						view.showMessage("List of selected vehicle type");
						specificVehicle = cmi.getVans(startDate, endDate);
						view.printVehicles(specificVehicle);
						break;
					case 3:
						view.showMessage("List of selected vehicle type");
						specificVehicle = cmi.getMovingTrucks(startDate,
								endDate);
						view.printVehicles(specificVehicle);
						break;
					case 4:
						view.showMessage("List of selected vehicle type");
						specificVehicle = cmi.getAutoCamper(startDate, endDate);
						view.printVehicles(specificVehicle);
						break;
					default:
						view.showMessage("Not a valid choise");
						select = view.readIntFromUser();
						break;
					}
				} while (select < 0 && select > 4);
				if (select == 0)
					break;
				view.showMessage("Select the desired vehicle from the list");
				do {
					select = view.readIntFromUser();
				} while (select <= 0 && select > specificVehicle.length);
				vehicle = specificVehicle[select - 1];

				view.readStringFromUser();
				fName = view.getCustomerData("first name");
				lName = view.getCustomerData("last name");
				address = view.getCustomerData("addresse");
				phone = view.getCustomerData("phone number");
				pickUpLocation = view
						.getCustomerData("desired pick up location");
				deliveryLocation = view
						.getCustomerData("desired delivery location");
				try {
					if (cmi.reserveAVehicle(startDate, endDate, fName, lName,
							address, phone, vehicle, pickUpLocation,
							deliveryLocation)) {
						view.showMessage("Reservation stored in the system");
						try {
							server.UpdateRentalList(cmi.getRentalList());
						} catch (RemoteException e) {
							System.out.println(e.getMessage());
						}
					} else {
						view.showMessage("The vehicle selected was not available for that period");
					}

				} catch (LogInEXception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				view.showMessage("Enter registration number of the vehicle");
				view.readStringFromUser();
				registrationNumber = view.readStringFromUser();
				view.insertDate(1);
				startDate = view.getDate();
				view.readStringFromUser();
				view.showMessage("Enter the new mileage");
				mileage = view.readIntFromUser();
				try {
					price = cmi.returnAVehicle(startDate, registrationNumber,
							mileage);
					if (price == 0) {
						view.showMessage("Rental not found in the system");
					} else {
						view.showMessage("Mileage of vehicle updated succesfully and the rental price is "
								+ price + " DKK");
						try {
							server.UpdateRentalList(cmi.getRentalList());
							server.UpdateVehicleList(cmi.getVehicleList());
						} catch (RemoteException e) {
							System.out.println(e.getMessage());
						}
					}
				} catch (LogInEXception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				view.showMessage("Enter licence number");
				view.readStringFromUser();
				licenseNumber = view.readStringFromUser();
				view.insertDate(1);
				startDate = view.getDate();
				view.readStringFromUser();
				view.getRegistrationNumber();
				registrationNumber = view.readStringFromUser();
				try {
					if (cmi.addLicenseNumber(licenseNumber, startDate,
							registrationNumber)) {
						view.showMessage("The booking has been made");
						try {
							server.UpdateRentalList(cmi.getRentalList());
						} catch (RemoteException e) {
							System.out.println(e.getMessage());
						}
					} else {
						view.showMessage("No reservation in the system with that data");
					}
				} catch (LogInEXception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				startDate = view.getDate();
				view.readStringFromUser();
				view.getRegistrationNumber();
				registrationNumber = view.readStringFromUser();
				try {
					if (cmi.cancelReservation(startDate, registrationNumber)) {
						view.showMessage("The reservation has been deleted");
						try {
							server.UpdateRentalList(cmi.getRentalList());
						} catch (RemoteException e) {
							System.out.println(e.getMessage());
						}
					} else {
						view.showMessage("No reservation in the system with that data");
					}
				} catch (LogInEXception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 0:
				System.out.println("you are exiting the system");
				break;
			default:
				System.out.println("this is not a valid choise. pick again");
				break;
			}
		} while (choise != 0);
		System.exit(0);
	}

	public void update(RemoteSubject<String> arg0, String updateMsg)
			throws RemoteException {
		System.out.println("got an update: " + updateMsg + " server=" + arg0);
		if (updateMsg.contains("vehicle")) {
			cmi.setVehicleList(server.getAllVehicle());
		}
		if (updateMsg.contains("rental")) {
			cmi.setRentalList(server.getAllRentals());
		}
	}
}
