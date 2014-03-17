package rmi_server_3;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorld_SRV implements HelloWorld_IN {
	
	public HelloWorld_SRV() {}

	public String hello() throws RemoteException {
		return "Hello World!";
	}
	
	public static void main(String args[]) {
		
		try {
			
			HelloWorld_SRV obj = new HelloWorld_SRV();	// erschaffe server
			HelloWorld_IN stub = (HelloWorld_IN) UnicastRemoteObject.exportObject(obj, 0); // bereite stub vor für rmi
			
			
			Registry registry = LocateRegistry.getRegistry();	// hol die registry
			registry.bind("Hello", stub);	// lade das interface in die registry
			
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			// Here could go some stuff for firing up a new server if this one crashes
		}
		
	}

}
