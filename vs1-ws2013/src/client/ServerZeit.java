/**
 * 
 */
package client;

import compute.*;
import java.util.Date;

/**
 * @author Thorsten
 *
 */
public class ServerZeit implements Task {

	private static final long serialVersionUID = 5598061061413974590L;

	protected ServerZeit() {
	}
        
	public Object execute() {
		return new Date();
	}

    @Override
    public String taskName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
