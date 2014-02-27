package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeSquare extends UnicastRemoteObject 
							implements Task<Integer> {

	private static final long serialVersionUID = 7452870205251216131L;

	private int zahl;
	
	public ComputeSquare(int zahl) throws RemoteException {
		this.zahl = zahl;
	}

	@Override
	public Integer execute() throws RemoteException {
		return zahl * zahl;
	}
}
