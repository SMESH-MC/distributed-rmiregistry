package compute;



import compute.*;
import java.rmi.RemoteException;

/**
 * Classe zum Berechnen des Quadrates
 * @author Thorsten
 */
public class ComputeSquare implements Task {

    private static final long serialVersionUID = 7452870205251216131L;

    private double operand;
    private int ID;

    /**
     * Konstruktor mit einem Double Parameter der als Operand hinterlegt wird.
     * @param operand
     * @throws RemoteException 
     */
    public ComputeSquare(double operand) throws RemoteException {
        this.operand = operand;
    }

    /**
     * Berechnung des Quadrates
     * @return Quadrat
     */
    @Override
    public Object execute() {
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
