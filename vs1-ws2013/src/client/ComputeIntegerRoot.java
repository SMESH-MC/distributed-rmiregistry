/**
 * Task zur Berechnung der Quadratwurzel
 */
package client;

import java.io.Serializable;

import exceptions.ParameterException;

/**
 * @author Thorsten
 *
 */
public class ComputeIntegerRoot implements Task<Integer>, Serializable{

	private static final long serialVersionUID = 1517373870379096195L;

	private int operator;
	
	/**
	 * Konstuktor mit erwartetem Operator
	 * @param operator
	 * @throws ParameterException 
	 */
	public ComputeIntegerRoot(int operator) throws ParameterException {
		super();
		if (operator < 0){throw new ParameterException();}
		this.operator = operator;
	}


	@Override
	public Integer execute() {
		return (int) Math.sqrt(operator);
	}

}
