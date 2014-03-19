package server;

import compute.Compute;
import compute.Task;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Compute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2576772980585139227L;

	public Server() throws RemoteException {
		super();
	}

	public Object executeTask(Task t) throws RemoteException {
                String tName = t.taskName();
                int ID =t.getClientID();
                System.out.println("Client-ID: "+ID+"\n"+tName+"\nBerechnung erfolgreich");
		return t.execute();
	}
	
	private String getServerAdress() {
		String adress;
		try {
			adress = (InetAddress.getLocalHost()).toString();
			return adress;
		}
		catch (Exception e) {
			System.err.println("Server exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		/*if (System.getSecurityManager() == null) {
                    
			System.setSecurityManager(new RMISecurityManager());
		}*/
		LocateRegistry.createRegistry(1099);
		try {//+ ((Server) server).getServerAdress() +"
			Compute server = new Server();
			String name = "rmi://127.0.0.1:1099/Compute";
			Naming.rebind(name, server);
			System.out.println("Server bound");
		}
		catch(Exception e) {
			System.err.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
