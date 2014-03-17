package rmi_server_0;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/*
 * 	@author SMESH
 */
public class UpperServer implements UpperInterface {

	public String toUpperCase(String input) throws RemoteException {
		return input.toUpperCase();
	}

	public static void main(String args[]) {
		
		try {
			UpperServer		us1 = new UpperServer();
			UpperInterface	ui1 = (UpperInterface) UnicastRemoteObject.exportObject(us1, 0); 
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Upper", ui1);
			
			System.err.println("Server bereit");
		} catch (Exception e) {
			System.err.println("Fehler aufgetreten: " + e.toString());
			e.printStackTrace();
		}
		
	}

}
