package compute;

/**
 * Task zur Berechnung der Quadratwurzel
 */


import compute.*;
import compute.exceptions.ParameterException;

/**
 * @author Thorsten
 *
 */
public class ComputeIntegerRoot implements Task {

    private static final long serialVersionUID = 1517373870379096195L;

    private static final String OPERAND = "Operator muss groesser 0 sein!";

    private double operand;
    private int ID;

    /**
     * Konstuktor mit erwartetem Operand
     *
     * @param operand
     * @throws ParameterException
     */
    public ComputeIntegerRoot(double operand) throws ParameterException {
        super();
        if (operand < 0) {
            throw new ParameterException(OPERAND);
        }
        this.operand = operand;
    }

    @Override
    public Object execute() {
        return (int) Math.sqrt(operand);
    }

    @Override
    public String taskName() {
        return "Wurzel als Integer aus " + operand + " berechnen...";
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
