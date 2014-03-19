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

}
