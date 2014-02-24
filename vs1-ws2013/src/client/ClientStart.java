/**
 * Startklasse des Client-Programms
 */
package client;

/**
 * @author Thorsten
 *
 */
public class ClientStart {
	
	private static ClientStart client;
	private static final int BEENDEN = 0;
	
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
			client.printMenue();
			client.auswahlEinlesen();
			client.ausfuehren();
		}
	}
	private void printMenue(){
		// TODO Menueausgabe
	}
	
	private void auswahlEinlesen(){
		// TODO Einlesen der benutzerauswahl
	}
	
	private String ausfuehren(){
		// TODO Abschicken der auswahl und Ausgabe des Ergebnisses
		return null;
	}
}
