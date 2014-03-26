import compute.Compute;
import compute.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Computeserver Klasse
 *
 * @author C.Schuetz
 */
public class Server extends UnicastRemoteObject implements Compute {

    private String serverAddr;
    private ArrayList memberList;

    private static final long serialVersionUID = 2576772980585139227L;

    /**
     * Konstruktor, ermittelt die IP Adresse
     *
     * @throws RemoteException
     */
    public Server() throws RemoteException {
        super();
        readMemberFile();
        this.serverAddr = this.locateServerAdress();

    }

    /**
     * execute Methode
     *
     * @param t auszufuehrender Task
     * @return Ergebnis des Tasks
     * @throws RemoteException
     */
    public Object executeTask(Task t) throws RemoteException {
        String tName = t.taskName();
        int ID = t.getClientID();
        if (isMember(ID)) {
            System.out.println("Client-ID: " + ID + "\n" + tName + "\nBerechnung erfolgreich");
            return t.execute();
        } else {
            System.out.println("Client-ID "+ID+ " ist kein Mitglied dieser Domäne!");
            return null;
        }

    }

    /**
     * ermittelt die lokale Server IP
     *
     * @return Server IP
     */
    public String locateServerAdress() {
        String adress;
        try {
            adress = (InetAddress.getLocalHost()).toString();
            String[] parts = adress.split("/");
            return parts[1];
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Getter fuer die Server IP
     *
     * @return Server IP
     */
    public String getServerAddr() {
        return this.serverAddr;
    }

    private void readMemberFile() {
//        System.out.println(new File("").getAbsolutePath());
        String fileName = "members.ini";
        String[] lineArray;
        memberList = new ArrayList();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                lineArray = line.split(",");
                for (int i = 0; i < lineArray.length; i++) {
                    memberList.add(new Integer(lineArray[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isMember(int ID) {
        if (memberList.contains(ID)) {
            return true;
        } else {
            return false;
        }
    }

}
