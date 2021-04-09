package vokabeltrainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Exception.KeinSemicolonException;
import Exception.LeereVokabelException;

public class Vokabeltrainer {

	private ListenVerwaltung listenVerwaltung = new ListenVerwaltung();
	
	public void start(String dateiName) {
		vokabelDateiEinlesen(dateiName);
		vokabelnAusgeben();
		System.out.println("-- Vokabeltrainer --\n\n");
		int laeuft = 1;
		while(laeuft == 1) {
			System.out.println("- Hauptmenue -\nEingabemoeglichkeiten:\neinlesen\nspeichern\nhinzufuegen\nloeschen\nabfragen");
			Scanner eingabeScanner = new Scanner(System.in);
			String eingabe = eingabeScanner.nextLine();
			switch(eingabe) {
				case "einlesen":
					//TODO: umbauen
					break;
				case "speichern":
					//TODO: implementieren
					break;
				case "hinzufuegen":
					//TODO: implementieren
					break;
				case "loeschen":
					//TODO: implementieren
					break;
				case "abfragen":
					//TODO: implementieren
					break;
				default:
					System.out.println("Eingabe ist ungueltig, bitte wiederholen. \n\n");
					break;
			}
		}
	}

	private void vokabelnAusgeben() {
		DoppeltverketteListe momEle = listenVerwaltung.getAnfang();
		while(momEle != null) {
			System.out.println(momEle.getVokabelDeutsch() + ";" + momEle.getVokabelEnglisch());
			momEle = momEle.getNext();
		}
	}

	private void vokabelDateiEinlesen(String dateiName){
		File datei = new File(dateiName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(dateiName));
			String zeile = null;
			while((zeile = reader.readLine()) != null) {
				try {
					if(validiereZeile(zeile) == true) {
						if(listenVerwaltung.getAnfang() == null) {
							listenVerwaltung.erstesElementAnhaengen(zeile);
						} else {
							listenVerwaltung.neuesElementAnhaengen(zeile);
						}
					}
				} catch (KeinSemicolonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LeereVokabelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ueberprueft ob eine Zeile zwei Vokabel getrennt von einem Semikolon enthaelt.
	 * TODO: erweitern?
	 * @param zeile
	 * @return true, wenn die Zeile valide ist ; false, wenn die Zeile invalide ist
	 * @throws KeinSemicolonException 
	 * @throws LeereVokabelException 
	 */
	private boolean validiereZeile(String zeile) throws KeinSemicolonException, LeereVokabelException {
		if(zeile.contains(";")) {
			String[] woerter = zeile.split(";");
			if(!((woerter[0].isBlank() || woerter[0].isEmpty()) || (woerter[1].isBlank() || woerter[1].isEmpty()))) {
				return true;				
			} else {
				throw new LeereVokabelException();
			}
		} else {
			throw new KeinSemicolonException();
		}
	}
	
	
	
	
	
	
	
}
