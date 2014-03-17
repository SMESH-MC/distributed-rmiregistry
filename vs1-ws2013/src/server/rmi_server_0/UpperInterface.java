package rmi_server_0;


import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * 	@author SMESH
 */
public interface UpperInterface extends Remote {
	String toUpperCase(String s) throws RemoteException;
}
