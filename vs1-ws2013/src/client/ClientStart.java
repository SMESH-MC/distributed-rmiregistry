/**
 * Startklasse des Client-Programms
 */
package client;

import compute.*;
import de.htw.saarland.stl.*;
import exceptions.ParameterException;
import java.rmi.*;
import java.math.*;

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
    private static int ID;
    //--------------Stringkonstanten
    //--------------Variablen
    private int auswahl;
    private Compute comp;

    /**
     * Startpunkt fuer den Client.
     *
     * @param args
     */
    public static void main(String[] args) {
        /*	if (System.getSecurityManager() == null) {
         System.setSecurityManager(new SecurityManager());
         }*/
        ID = calculateID();
        System.out.println("Client-ID: " + ID);
        client = new ClientStart();
        client.start();
    }
    private String[] args;

    /**
     * Konstruktor des Programmstarters
     */
    public ClientStart() {
        auswahl = -1;
        try {
            String name = "//127.0.0.1:1099/Compute";
            comp = (Compute) Naming.lookup(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void start() {
        while (auswahl != BEENDEN) {
            try {
                auswahl = auswahlEinlesen();
                ausfuehren(auswahl);
            } catch (RuntimeException e) {
                System.out.println(e);
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
        menue.append(" -> ");
        System.out.print(menue);
    }

    private int auswahlEinlesen() {
        System.out.println();
        printMenue();
        return Stdin.readlnInt();
    }

    private static int calculateID() {
        return (int) (Math.random() * (100 - 1) + 1);

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

            default:
                System.out.println("Fehleingabe");
                break;
        }
    }

    private Double computeRoot(double operand) throws RemoteException, ParameterException {
        ComputeClassicRoot task;
        task = new ComputeClassicRoot(operand);
        task.setClientID(ID);
        Double root = (double) (comp.executeTask(task));
        return root;
    }

    private int computeIntRoot(double operand) throws RemoteException, ParameterException {
        ComputeIntegerRoot task;
        task = new ComputeIntegerRoot(operand);
        task.setClientID(ID);
        int root = (int) (comp.executeTask(task));
        return root;
    }

    private Double computeSquare(double operand) throws RemoteException, ParameterException {
        ComputeSquare task;
        task = new ComputeSquare(operand);
        task.setClientID(ID);
        Double square = (double) (comp.executeTask(task));
        return square;
    }
}
