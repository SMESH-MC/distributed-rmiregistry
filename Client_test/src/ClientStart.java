/**
 * Startklasse des Client-Programms
 */


import compute.*;
import de.htw.saarland.stl.*;
import compute.exceptions.NotAMemberException;
import compute.exceptions.ParameterException;
import java.io.*;
import java.rmi.*;
import java.util.ArrayList;

/**
 * Klasse ClientStart dient als Startpunkt fuer das Clientprogramm.
 *
 * @author Thorsten
 *
 */
public class ClientStart {

    //--------------Klassenkonstanten
    private static ClientStart client;
    private static final int BEENDEN = 0;
    private static final int ROOT = 1;
    private static final int INTROOT = 2;
    private static final int SQUARE = 3;
    private static final int PRINT_DOMAIN = 4;
    private static final int SET_ID = 5;

    //--------------Stringkonstanten
    //--------------Variablen
    private int auswahl;
    private Compute comp;
    private ArrayList serverList;
    private ServerIP server;
    private int ID;

    /**
     * Startpunkt fuer den Client.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        client = new ClientStart();
        client.start();
    }

    /**
     * Konstruktor des Programmstarters
     */
    public ClientStart() {
        auswahl = -1;
        serverList = readDomainFile();
        ID = calculateID();
        System.out.println("Client-ID: " + ID);
    }

    /**
     *  Methode zum Verbinden mit dem Server
     */
    public void connect() {
        int size = serverList.size();
        int counter = 0;
        while (comp == null && counter < size) {
            try {
                //String name = "//127.0.0.1:1099/Compute";
                server = (ServerIP) serverList.get(counter);
                String serverAdress = server.getAdresse();
                String name = "//" + serverAdress + "/Compute";
                comp = (Compute) Naming.lookup(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ++counter;
        }
    }

    /**
     *  Methode enthaelt den generellen Programmablauf
     */
    private void start() {
        while (auswahl != BEENDEN) {
            try {
                auswahl = auswahlEinlesen();
                ausfuehren(auswahl);
            } catch (NotAMemberException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Methode zum Ausgeben des Hauptmenues
     */
    private void printMenue() {
        StringBuilder menue = new StringBuilder();
        menue.append(ROOT + ": Wurzel berechnen " + "\n");
        menue.append(INTROOT + ": Ganzzahl-Anteil einer Wurzel berechnen " + "\n");
        menue.append(SQUARE + ": Quadrat berechnen " + "\n");
        menue.append(BEENDEN + ": Beenden " + "\n");
        menue.append(PRINT_DOMAIN + ": Domäne anzeigen " + "\n");
        menue.append(SET_ID + ": ID ändern " + "\n");
        menue.append(" -> ");
        System.out.print(menue);
    }

    /**
     * Methode zum Einlesen der Benutzereingabe
     * @return Benutzereingabe
     */
    private int auswahlEinlesen() {
        System.out.println();
        printMenue();
        return Stdin.readlnInt();
    }

    /**
     * Methode zum Erzeugen einer zufaelligen Client-ID
     * @return erzeugte ID
     */
    private int calculateID() {
        return (int) (Math.random() * (100 - 1) + 1);

    }

    /**
     * Methode zum Setzen einer neuen Client-ID
     */
    private void setID() {
        ID = Stdin.readlnInt("Bitte neue ID eigeben");
    }

    /**
     * Verzweigt den Programmablauf emsprechend der Benutzereingabe fuer 
     * die Auswahl.
     * @param auswahl
     * @throws RemoteException
     * @throws ParameterException
     * @throws NotAMemberException 
     */
    private void ausfuehren(int auswahl) throws RemoteException, ParameterException, NotAMemberException {
        double operand;
        switch (auswahl) {
            case ROOT:
                operand = Stdin.readlnDouble("Bitte Operand eingeben");
                double root = computeRoot(operand);
                System.out.println("Wurzel aus " + operand + " ist " + root);
                break;

            case INTROOT:
                operand = Stdin.readlnDouble("Bitte Operand eingeben");
                double introot = computeIntRoot(operand);
                System.out.println("Der Ganzzahl-Anteil der Wurzel aus " + operand + " ist " + introot);
                break;

            case SQUARE:
                operand = Stdin.readlnDouble("Bitte Operand eingeben");
                double square = computeSquare(operand);
                System.out.println("Das Quadrat von " + operand + " ist " + square);
                break;

            case BEENDEN:
                System.out.println("Das Programm wird beendet");
                break;
            case PRINT_DOMAIN:
                System.out.println("Client greift auf folgende Domäne zu: \n"
                        + server.getDomain());
                break;
            case SET_ID:
                setID();
                System.out.println("Client-ID: "+ID);
                break;
            default:
                System.out.println("Fehleingabe");
                break;
        }
    }
    
    /**
     * Methode zum Ausfuehren des uebergebenen Tasks
     * @param task
     * @return object
     * @throws RemoteException
     * @throws NotAMemberException 
     */
    private Object execute(Task task) throws RemoteException,NotAMemberException{
        Object obj = comp.executeTask(task);
        if(obj == null){
            throw new NotAMemberException("Fehler: Sie sind kein Mitglied dieser Domaene!");
        }
        return obj;
    }

    /**
     * Programmroutine um Berechnung der Quadratwurzel zu beginnen.
     * @param operand
     * @return root
     * @throws RemoteException
     * @throws ParameterException
     * @throws NotAMemberException 
     */
    private Double computeRoot(double operand) throws RemoteException, ParameterException, NotAMemberException {
        connect();
        ComputeClassicRoot task;
        task = new ComputeClassicRoot(operand);
        task.setClientID(ID);
        Double root = (double) execute(task);
        return root;
    }

    /**
     * Programmroutine um Berechnung der ganzzahligen Quadratwurzel zu beginnen.
     * @param operand
     * @return root
     * @throws RemoteException
     * @throws ParameterException
     * @throws NotAMemberException 
     */
    private int computeIntRoot(double operand) throws RemoteException, ParameterException, NotAMemberException {
        connect();
        ComputeIntegerRoot task;
        task = new ComputeIntegerRoot(operand);
        task.setClientID(ID);
        int root = (int) execute(task);
        return root;
    }

    /**
     * Programmroutine um Berechnung des Quadrates zu beginnen.
     * @param operand
     * @return square Quadrat
     * @throws RemoteException
     * @throws ParameterException
     * @throws NotAMemberException 
     */
    private Double computeSquare(double operand) throws RemoteException, ParameterException, NotAMemberException {
        connect();
        ComputeSquare task;
        task = new ComputeSquare(operand);
        task.setClientID(ID);
        Double square = (double) execute(task);
        return square;
    }

    /**
     * Methode zum Einlesen der Domaenenkomfiguration.
     * @return Serverliste
     */
    private ArrayList readDomainFile() {
        String fileName = "domain.ini";
        String[] lineArray;
        ArrayList serverList = new ArrayList();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                lineArray = line.split(",");
                for (int i = 1; i < lineArray.length; i++) {
                    ServerIP newServer = new ServerIP(lineArray[0], lineArray[i]);
                    serverList.add(newServer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverList;
    }

}
