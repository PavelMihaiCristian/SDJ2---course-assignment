package clientController;

import java.rmi.RemoteException;

import clintview.ClientView;

public class ClientMain {
	
	public static void main(String[] args) {
		
		try {
			ClientController controller=new ClientController();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
