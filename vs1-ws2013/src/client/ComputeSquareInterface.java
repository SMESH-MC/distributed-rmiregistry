package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputeSquareInterface extends Remote{
	public int squere(int zahl) throws RemoteException;
}
