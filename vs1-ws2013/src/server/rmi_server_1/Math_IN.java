package rmi_server_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Interface for simple Math additions and substractions for RMI purposes
 * @author C.Schuetz
 */
public interface Math_IN extends Remote {
	/*
	 * @param x first value
	 * @param y second value
	 */
	int addition(int x, int y) throws RemoteException;
	/*
	 * @param x first value
	 * @param y second value
	 */
	int substraction(int x, int y) throws RemoteException;
}
