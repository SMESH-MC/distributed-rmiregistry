/**
 * Task zur Berechnung der Quadratwurzel
 */
package client;

import compute.*;
import java.io.Serializable;

import exceptions.ParameterException;

/**
 * @author derb
 *
 */
public class ComputeClassicRoot implements Task{

	private static final long serialVersionUID = 1517373870379096195L;
	
	private static final String OPERAND = "Operator muss groesser 0 sein!";

	private double operand;
	
	/**
	 * Konstuktor mit erwartetem Operator
	 * @param operand
	 * @throws ParameterException 
	 */
	public ComputeClassicRoot(double operand) throws ParameterException {
		super();
		if (operand < 0){throw new ParameterException(OPERAND);}
		this.operand = operand;
	}
        
        

	@Override
	public Object execute() {
		return Math.sqrt(operand);
	}

    @Override
    public String taskName() {
        return "Wurzel aus "+operand+" berechnen...";
    }
        
        
}
