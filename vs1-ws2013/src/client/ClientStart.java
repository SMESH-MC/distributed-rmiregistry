/**
 * Startklasse des Client-Programms
 */
package client;

import de.htw.saarland.stl.*;
/**
 * Klasse ClientStart dient als Startpunkt fuer das Clientprogramm.
 * @author Thorsten
 *
 */
public class ClientStart {
	//--------------Klassenkonstanten
	private static ClientStart client;
	private static final int BEENDEN = 0;
	
	//--------------Stringkonstanten
	
	//--------------Variablen
	private int auswahl;
	
	/**
	 * Startpunkt fuer den Client.
	 * @param args
	 */
	public static void main(String[] args) {
		client = new ClientStart();
		client.start();
	}
	
	/**
	 * Konstruktor des Programmstarters
	 */
	public ClientStart(){
		auswahl = -1;
	}
	
	private void start(){
		while(auswahl != BEENDEN){
			try{
                auswahl = auswahlEinlesen();
                ausfuehren(auswahl);
            } catch(RuntimeException e){
                System.out.println(e);
            } catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
		}
	}
	private void printMenue(){
		// TODO Menueausgabe
		StringBuilder menue = new StringBuilder();
        menue.append(BEENDEN           + ": Beenden " + "\n");
        menue.append(" -> ");
        System.out.print(menue);
	}
	
	private int auswahlEinlesen(){
		// TODO Einlesen der benutzerauswahl
		System.out.println();
        printMenue();
        return Stdin.readlnInt();
	}
	
	private void ausfuehren(int auswahl){
		// TODO Abschicken der auswahl und Ausgabe des Ergebnisses
		switch(auswahl)
        {
        case BEENDEN:
            System.out.println("Das Programm wird beendet");
            break;
        
        default:
            System.out.println("Fehleingabe");
            break;
        }
	}
}
