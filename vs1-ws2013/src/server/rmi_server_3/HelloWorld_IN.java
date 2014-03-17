package rmi_server_3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld_IN extends Remote {
	String hello() throws RemoteException;
}
