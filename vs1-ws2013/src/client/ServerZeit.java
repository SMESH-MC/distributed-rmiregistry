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

        int ID;
        
	protected ServerZeit() {
	}
        
	public Object execute() {
		return new Date();
	}

    @Override
    public String taskName() {
        return "Server-Zeit: ";
    }

    @Override
    public int getClientID() {
        return this.ID;
    }

    @Override
    public void setClientID(int ID) {
        this.ID = ID;
    }

}
