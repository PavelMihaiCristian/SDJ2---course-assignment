package serverModel;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import serverController.ServerController;
import serverView.ServerView;

public class SingleServer {
	
	private static SingleServer instance;
	
	private SingleServer()
	{
	   runServer();
	}
	
	public static SingleServer getInstance(){
	   if (instance == null)
	    {
	      instance = new SingleServer();
	    }
		return instance;
	}
	
	private void runServer(){
		try{
			Registry reg=LocateRegistry.createRegistry(1099);	
			ServerView view=new ServerView();
			ServerModel model=new ServerModel();
			ServerInterface server=new ServerController(model, view);
			UnicastRemoteObject.exportObject(server, 0);
			Naming.rebind("V-RENT",server);
			view.showMessage("The server is Started....");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
