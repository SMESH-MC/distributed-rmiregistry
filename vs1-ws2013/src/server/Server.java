package server;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import compute.*;

public class Server extends UnicastRemoteObject implements Compute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2576772980585139227L;

	protected Server() throws RemoteException {
		super();
	}

	@Override
	public Object executeTask(Task t) throws RemoteException {
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
	
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {
			Compute server = new Server();
			String name = "rmi://" + ((Server) server).getServerAdress() +"/Compute";
			Naming.rebind(name, server);
			System.out.println("Server bound");
		}
		catch(Exception e) {
			System.err.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
