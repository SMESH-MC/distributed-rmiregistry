/**
 * Startklasse des Client-Programms
 */
package client;

import compute.*;
import de.htw.saarland.stl.*;
import exceptions.ParameterException;
import java.rmi.*;
import java.math.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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
        /*	if (System.getSecurityManager() == null) {
         System.setSecurityManager(new SecurityManager());
         }*/

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

    public void connect() {
        int size = serverList.size();
        int counter = 0;
        while (comp == null && counter < size) {
            try {
                //String name = "//127.0.0.1:1099/Compute";
                server = (ServerIP) serverList.get(counter);
                String serverAdress = server.getAdresse();
                System.out.println(serverAdress);
                String name = "//" + serverAdress + "/Compute";
                comp = (Compute) Naming.lookup(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ++counter;
        }
    }

    private void start() {
        while (auswahl != BEENDEN) {
            try {
                auswahl = auswahlEinlesen();
                ausfuehren(auswahl);
            } catch (NullPointerException e) {
                System.out.println("Fehler: Sie sind kein Mitglied dieser Domaene!");
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

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

    private int auswahlEinlesen() {
        System.out.println();
        printMenue();
        return Stdin.readlnInt();
    }

    private int calculateID() {
        return (int) (Math.random() * (100 - 1) + 1);

    }

    private void setID() {
        ID = Stdin.readlnInt("Bitte neue ID eigeben");
    }

    private void ausfuehren(int auswahl) throws RemoteException, ParameterException {
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

    private Double computeRoot(double operand) throws RemoteException, ParameterException {
        connect();
        ComputeClassicRoot task;
        task = new ComputeClassicRoot(operand);
        task.setClientID(ID);
        Double root = (double) (comp.executeTask(task));
        return root;
    }

    private int computeIntRoot(double operand) throws RemoteException, ParameterException {
        connect();
        ComputeIntegerRoot task;
        task = new ComputeIntegerRoot(operand);
        task.setClientID(ID);
        int root = (int) (comp.executeTask(task));
        return root;
    }

    private Double computeSquare(double operand) throws RemoteException, ParameterException {
        connect();
        ComputeSquare task;
        task = new ComputeSquare(operand);
        task.setClientID(ID);
        Double square = (double) (comp.executeTask(task));
        return square;
    }

    private ArrayList readDomainFile() {
        System.out.println(new File("").getAbsolutePath());
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
