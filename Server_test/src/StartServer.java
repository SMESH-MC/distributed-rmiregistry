

import compute.Compute;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Klasse zum starten und beenden eines Servers
 * @author C.Schuetz
 */
public class StartServer {
    
    private Server server;
    private Compute stub;
    private String name;
    
    /**
     * Konstruktor, setzt Attribute auf null bzw leerstring
     */
    public StartServer() throws RemoteException {
        this.server = null;
        this.stub = null;
        this.name = "";
    }
    
    /**
     * Methode zum Starten des Servers. Legt eine Registry an falls es keine gibt und registriert den Server
     */
    public void startServer() {
        try {
            try {
                LocateRegistry.createRegistry(1099);
            } catch (RemoteException e) {
                // Registry existiert bereits
            }
            this.server = new Server();
            this.stub = (Compute) this.server;
            String ip = this.server.getServerAddr();
            this.name = "rmi://" + ip + ":1099/Compute";
            System.out.println("Server IP: " + ip);
            Naming.rebind(this.name, this.stub);
            System.out.println("Server bound");
            System.out.println("Server gestartet");
        }
	catch(Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
            System.err.println("Server konnte nicht gestartet werden!");
	}
    }
    
    /**
     * Methode zum beenden des Servers
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException 
     */
    public void stopServer() throws RemoteException, NotBoundException, MalformedURLException {
        if (this.server != null) {
            Naming.unbind(this.name);
            System.out.println("Server Unbound");
            this.server = null;
            this.stub = null;
            this.name = "";
            System.out.println("Server gestoppt!");
        } else {
            System.out.println("Server konnte nicht gestoppt werden - Server ist offline!");
        }
    }
    
    /**
     * Methode zum neustarten des Servers
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException 
     */
    public void restartServer() throws RemoteException, NotBoundException, MalformedURLException {
        if (this.server != null) {
            System.out.println("Starte Restart Methode");
            this.stopServer();
            System.out.println("-- Starte Server neu");
            this.startServer();
            System.out.println("Restart Methode finished");
        } else {
            System.out.println("Server konnte nicht neu gestartet werden - Server ist offline!");
        }
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        StartServer start = new StartServer();
        try {
            start.startServer();
        } 
        catch (Exception e) {
            if (start.server != null) {
                System.out.println("Fehler entdeckt, starte Server neu!");
                start.restartServer();
            }
        }
        
        // DEBUG
        //start.restartServer();
        
        //start.stopServer();
        //start.stopServer();
    }
        
}