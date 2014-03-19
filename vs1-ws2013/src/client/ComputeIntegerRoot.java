/**
 * Task zur Berechnung der Quadratwurzel
 */
package client;

import compute.*;
import exceptions.ParameterException;

/**
 * @author Thorsten
 *
 */
public class ComputeIntegerRoot implements Task {

	private static final long serialVersionUID = 1517373870379096195L;
	
	private static final String OPERAND = "Operator muss groesser 0 sein!";

	private int operand;
	
	/**
	 * Konstuktor mit erwartetem Operator
	 * @param operator
	 * @throws ParameterException 
	 */
	public ComputeIntegerRoot(int operand) throws ParameterException {
		super();
		if (operand < 0){throw new ParameterException(OPERAND);}
		this.operand = operand;
	}


	@Override
	public Object execute() {
		return (int) Math.sqrt(operand);
	}

}
