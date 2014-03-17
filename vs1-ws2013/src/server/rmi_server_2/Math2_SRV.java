package rmi_server_2;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Math2_SRV implements Math2_IN {
	
	public Math2_SRV() {}

	public int multiplicate(int x, int y) throws RemoteException {
		return x * y;
	}
	
	public static void main(String args[]) {
		
		try {
			
			Math2_SRV obj = new Math2_SRV();
			Math2_IN stub = (Math2_IN) UnicastRemoteObject.exportObject(obj, 0);
			
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Math2", stub);
			
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			// Here could go some stuff for firing up a new server if this one crashes
		}
		
	}
}
