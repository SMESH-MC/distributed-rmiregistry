package rmi_server_1;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * One server that handles the math functions listed in Math_IN
 * @author C.Schuetz
 */
public class Math_SRV implements Math_IN {
	
	/*
	 * Contructor
	 */
	public Math_SRV() {}

	/*
	 * Implementation of addition
	 * @see rmi_server_1.Math_IN#addition(int, int)
	 */
	public int addition(int x, int y) throws RemoteException {
		return x + y;
	}

	/*
	 * Implementation of substraction
	 * @see rmi_server_1.Math_IN#substraction(int, int)
	 */
	public int substraction(int x, int y) throws RemoteException {
		return x - y;
	}
	
	/*
	 * Starting up the server. Loads a Math_IN Object into the registry to provide the functions.
	 * Domain: Math
	 */
	public static void main(String args[]) {
		
		try {
			
			Math_SRV obj = new Math_SRV();
			Math_IN stub = (Math_IN) UnicastRemoteObject.exportObject(obj, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Math", stub);
			
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			// Here could go some stuff for firing up a new server if this one crashes
		}
		
	}
}
