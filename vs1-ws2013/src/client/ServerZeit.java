/**
 * 
 */
package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * @author Thorsten
 *
 */
public class ServerZeit extends UnicastRemoteObject implements Task<Date> {

	private static final long serialVersionUID = 5598061061413974590L;

	protected ServerZeit() throws RemoteException {
	}

	@Override
	public Date execute() throws RemoteException {
		return new Date();
	}

}
