package client;

import compute.*;
import java.rmi.RemoteException;

public class ComputeSquare implements Task{

	private static final long serialVersionUID = 7452870205251216131L;

	private double operand;
        private int ID;
	
	public ComputeSquare(double operand) throws RemoteException {
		this.operand = operand;
	}

	public Object execute(){
		return operand * operand;
	}

    @Override
    public String taskName() {
        return "Quadrat aus " + operand + " berechnen...";
    }

    @Override
    public int getClientID() {
        return ID;
    }

    @Override
    public void setClientID(int ID) {
        this.ID = ID;
    }
}
