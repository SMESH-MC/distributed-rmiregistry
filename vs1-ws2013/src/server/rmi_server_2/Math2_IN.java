package rmi_server_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Math2_IN extends Remote {
	int multiplicate(int x, int y) throws RemoteException;
}
