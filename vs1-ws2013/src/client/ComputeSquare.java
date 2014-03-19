package client;

import compute.*;
import java.rmi.RemoteException;

public class ComputeSquare implements Task{

	private static final long serialVersionUID = 7452870205251216131L;

	private int zahl;
	
	public ComputeSquare(int zahl) throws RemoteException {
		this.zahl = zahl;
	}

	public Object execute(){
		return zahl * zahl;
	}

    @Override
    public String taskName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
