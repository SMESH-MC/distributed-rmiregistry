/**
 * Task zur Berechnung der Quadratwurzel
 */
package client;

import java.io.Serializable;

import exceptions.ParameterException;

/**
 * @author derb
 *
 */
public class ComputeClassicRoot implements Task<Double>, Serializable{

	private static final long serialVersionUID = 1517373870379096195L;
	
	private static final String OPERAND = "Operator muss groesser 0 sein!";

	private double operand;
	
	/**
	 * Konstuktor mit erwartetem Operator
	 * @param operator
	 * @throws ParameterException 
	 */
	public ComputeClassicRoot(double operand) throws ParameterException {
		super();
		if (operand < 0){throw new ParameterException(OPERAND);}
		this.operand = operand;
	}


	@Override
	public double execute() {
		return Math.sqrt(operand);
	}

}
