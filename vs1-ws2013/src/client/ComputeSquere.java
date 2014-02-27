package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeSquere extends UnicastRemoteObject 
							implements ComputeSquereInterface {

	private static final long serialVersionUID = 7452870205251216131L;

	public ComputeSquere() throws RemoteException {
	}

	@Override
	public int squere(int zahl) throws RemoteException {
		return zahl * zahl;
	}
}
