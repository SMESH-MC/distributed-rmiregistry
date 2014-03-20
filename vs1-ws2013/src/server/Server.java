package server;

import compute.Compute;
import compute.Task;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Computeserver Klasse
 * @author C.Schuetz
 */

public class Server extends UnicastRemoteObject implements Compute {
    
    private String serverAddr;
    
    private static final long serialVersionUID = 2576772980585139227L;

    /**
     * Konstruktor, ermittelt die IP Adresse
     * @throws RemoteException 
     */
    public Server() throws RemoteException {
	super();
        this.serverAddr = this.locateServerAdress();
    }

    /**
     * execute Methode
     * @param t auszufuehrender Task
     * @return Ergebnis des Tasks
     * @throws RemoteException 
     */
    public Object executeTask(Task t) throws RemoteException {
        String tName = t.taskName();
        int ID =t.getClientID();
        System.out.println("Client-ID: "+ID+"\n"+tName+"\nBerechnung erfolgreich");
	return t.execute();
    }
       
    /**
     * ermittelt die lokale Server IP
     * @return Server IP
     */
    public String locateServerAdress() {
        String adress;
	try {
            adress = (InetAddress.getLocalHost()).toString();
            String[] parts = adress.split("/");
            return parts[1];
	}
	catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
            return null;
	}
    }
        
    /**
     * Getter fuer die Server IP
     * @return Server IP
     */
    public String getServerAddr() {
        return this.serverAddr;
    }
}
