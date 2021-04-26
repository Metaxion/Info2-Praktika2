package vokabeltrainer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Exception.KeinSemicolonException;
import Exception.LeereVokabelException;

public class Vokabeltrainer {

	protected ListenVerwaltung listenVerwaltung = new ListenVerwaltung();

	/**
	 * Hauptprogramm des Vokabeltrainers <br>
	 * Verwaltete das Hauptmenue und gibt ueber eine Menuesteuerung Zugriff zu den
	 * einzelnen Funktionalitäten <br>
	 * Die uebergebene Datei (dateiName) wird als Vokabel-Datenbank verwendet, falls
	 * diese Datei nicht exisiteren sollte, wir sie neu erstellt. <br>
	 * Menue-Eingaben: <br>
	 * - einlesen <br>
	 * - speichern <br>
	 * - hinzufuegen <br>
	 * - loeschen <br>
	 * - abfragen <br>
	 * - beenden <br>
	 * (- debug) <br>
	 * TODO Tests schreiben
	 * 
	 * @param dateiName
	 */
	public void start(String dateiName) {
		System.out.println("-- Vokabeltrainer --\n");
		int laeuft = 1;
		Scanner eingabeScanner = new Scanner(System.in);
		while (laeuft == 1) {
			System.out.println(
					"\n-- Hauptmenue --\nEingabemoeglichkeiten:\neinlesen\nspeichern\nhinzufuegen\nloeschen\nabfragen\nbeenden\nEingabe:\n");
			String eingabe = eingabeScanner.nextLine();
			switch (eingabe) {
			case "einlesen":
				try {
					vokabelDateiEinlesen(dateiName);
				} catch (IOException e) {
					System.err.println("Datei oder Reader konnte beim Einlesen nicht richtig geoeffnet werden");
				}
				break;
			case "speichern":
				if (listenVerwaltung.getAnfang() != null) {
					try {
						vokabelDateiSpeichern(dateiName);
					} catch (IOException e) {
						System.err.println("Datei oder Writer konnte beim Speichern nicht richtig geoeffnet werden");
					}
				}
				break;
			case "hinzufuegen":
				if (listenVerwaltung.getAnfang() != null) {
					vokabelHinzufuegen();
				}
				break;
			case "loeschen":
				if (listenVerwaltung.getAnfang() != null) {
					vokabelLoeschen();
				}
				break;
			case "abfragen":
				if (listenVerwaltung.getAnfang() != null) {
					vokabelAbfragen();
				}
				break;
			case "debug":
				if (listenVerwaltung.getAnfang() != null) {
					vokabelnAusgeben();
				}
				break;
			case "beenden":
				System.out.println("\n-- Vokabeltrainer beendet --");
				return;
			default:
				System.out.println("Eingabe ist ungueltig, bitte wiederholen. \n\n");
				break;
			}
		}
		eingabeScanner.close();
	}

	/**
	 * Fragt eine zufaellige Vokabel in einer zufaelligen Uebersetzungsrichtung ab.
	 */
	@SuppressWarnings("resource")
	protected void vokabelAbfragen() {
		Scanner vokabelScanner = new Scanner(System.in);
		String eingabe;
		Random rand = new Random();
		// Zufaellige Vokabel und Uebersetzungsrichtung bestimmen
		int vokabelWahl = rand.nextInt(1000);
		int uebersetzungsRichtung = rand.nextInt(1);
		DoppeltverketteteListe liste = listenVerwaltung.getAnfang();
		for (int i = 0; i < vokabelWahl; i++) {
			if (liste.hasNext()) {
				liste = liste.getNext();
			} else {
				liste = listenVerwaltung.getAnfang();
			}
		}
		// Abfrage
		if (uebersetzungsRichtung == 0) {
			// Englisch zu Deutsch
			System.out.println("Geben sie die deutsche Uebersetzung an: " + liste.getVokabelEnglisch());
			eingabe = vokabelScanner.nextLine();
			if (eingabe.equals(liste.getVokabelDeutsch())) {
				System.out.println("\nRichtig!");
			} else {
				System.out.println("\nFalsch! " + liste.getVokabelDeutsch() + " waere richtig gewesen!");
			}
		} else {
			// Deutsch zu Englisch
			System.out.println("Geben sie die englische Uebersetzung an: " + liste.getVokabelDeutsch());
			eingabe = vokabelScanner.nextLine();
			if (eingabe.equals(liste.getVokabelEnglisch())) {
				System.out.println("\nRichtig!");
			} else {
				System.out.println("\nFalsch! " + liste.getVokabelEnglisch() + " waere richtig gewesen!");
			}
		}
	}

	/**
	 * Laesst den Nutzer eine Vokabel auf deutsch oder englisch angeben und falls
	 * sie vorhanden ist wird sie geloescht. TODO: ueberpruefen aufgrund der
	 * Klammerverschiebung
	 */
	@SuppressWarnings("resource")
	protected void vokabelLoeschen() {
		Scanner vokabelScanner = new Scanner(System.in);
		System.out.println("\nGeben sie die zu loeschende Variabel in deutsch oder englisch an:");
		String eingabe = vokabelScanner.nextLine();
		eingabe = eingabe.trim();
		DoppeltverketteteListe liste = listenVerwaltung.getAnfang();
		if (liste != null) {
			if (liste.getVokabelDeutsch().equals(eingabe) || liste.getVokabelEnglisch().equals(eingabe)) {
				listenVerwaltung.loescheElement(liste);
				System.out.println("\nVokabel wurde geloescht");
			}
			while (liste.hasNext()) {
				liste = liste.getNext();
				if (liste.getVokabelDeutsch().equals(eingabe) || liste.getVokabelEnglisch().equals(eingabe)) {
					listenVerwaltung.loescheElement(liste);
					System.out.println("\nVokabel wurde geloescht");
				}
			}
		}
	}

	/**
	 * Fordert die Eingabe einer Vokabel und speichert sie in der Liste.
	 */
	@SuppressWarnings("resource")
	protected void vokabelHinzufuegen() {
		Scanner vokabelScanner = new Scanner(System.in);
		System.out.println("Geben sie die englisch Vokabel ein:");
		String eingabeEnglisch = vokabelScanner.nextLine();
		System.out.println("\nGeben sie die deutsche Vokabel ein:");
		String eingabeDeutsch = vokabelScanner.nextLine();

		try {
			if(validiereZeile(eingabeEnglisch + ";" + eingabeDeutsch)) {
				
			if(listenVerwaltung.getAnfang() != null) {
				listenVerwaltung.neuesElementAnhaengen(eingabeEnglisch + ";" + eingabeDeutsch);
			} else {
				listenVerwaltung.erstesElementAnhaengen(eingabeEnglisch + ";" + eingabeDeutsch);
			}
			System.out.println("\nVokabel hinzugefuegt");
			}
		} catch (KeinSemicolonException e) {
			e.printStackTrace();
		} catch (LeereVokabelException e) {
			System.out.println("Die Eingabe Vokenthaelt keine Vokabel");
		}
	}

	/**
	 * Liest die einzelnen Vokabeln aus der Liste aus und speichert sie Zeile fuer
	 * Zeile in der Vokabel Datei (dateiName).
	 * 
	 * @param dateiName
	 * @throws IOException
	 */
	protected void vokabelDateiSpeichern(String dateiName) throws IOException {
		if (listenVerwaltung.getAnfang() != null) {

			File datei = new File(dateiName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(datei));

			DoppeltverketteteListe liste = listenVerwaltung.getAnfang();
			if (liste != null) {
				writer.append(liste.getVokabelEnglisch() + ";" + liste.getVokabelDeutsch());
			}
			while (liste.hasNext()) {
				writer.append("\n");
				liste = liste.getNext();
				writer.append(liste.getVokabelEnglisch() + ";" + liste.getVokabelDeutsch());
			}
			writer.close();
		}
	}

	/**
	 * Gibt alle Vokabeln aus der Liste auf der Konsole aus
	 */
	protected void vokabelnAusgeben() {
		System.out.println(erstelleStringAlleVokabeln());
	}

	/**
	 * Erstellt ein String aus allen Vokabeln der Vokabelliste
	 * 
	 * @return String der Vokabelliste
	 */
	protected String erstelleStringAlleVokabeln() {
		DoppeltverketteteListe momEle = listenVerwaltung.getAnfang();
		StringBuilder builder = new StringBuilder();
		while (momEle != null) {
			builder.append(momEle.getVokabelEnglisch() + ";" + momEle.getVokabelDeutsch());
			momEle = momEle.getNext();
			if (momEle != null) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	/**
	 * Liest alle Vokabel aus der Vokabel Datei (dateiName) aus und fuellt mit den
	 * einzelnen Vokabeln die Liste.
	 * 
	 * @param dateiName
	 * @throws IOException
	 */
	protected void vokabelDateiEinlesen(String dateiName) throws IOException {
		File datei = new File(dateiName);
		if (!datei.exists()) {
			datei.createNewFile();
		}
		BufferedReader reader = new BufferedReader(new FileReader(datei));
		String zeile = null;
		int zeilenNummer = 0;
		int vokabelAnzahl = 0;
		while ((zeile = reader.readLine()) != null) {
			zeilenNummer++;
			try {
				if (validiereZeile(zeile) == true) {
					if (listenVerwaltung.getAnfang() == null) {
						listenVerwaltung.erstesElementAnhaengen(zeile);
					} else {
						listenVerwaltung.neuesElementAnhaengen(zeile);
					}
					vokabelAnzahl++;
				}
			} catch (KeinSemicolonException e) {
				System.out.println("Zeile " + zeilenNummer + " enthaelt kein Semicolon");
			} catch (LeereVokabelException e) {
				System.out.println("Zeile " + zeilenNummer + " enthaelt keine Vokabel");
			}
		}
		reader.close();
		System.out.println("\n" + vokabelAnzahl + " Vokabeln wurden erfolgreich eingelesen!");
	}

	/**
	 * Ueberprueft ob eine Zeile zwei Vokabel getrennt von einem Semikolon enthaelt.
	 * TODO: erweitern?
	 * 
	 * @param zeile
	 * @return true, wenn die Zeile valide ist ; false, wenn die Zeile invalide ist
	 * @throws KeinSemicolonException
	 * @throws LeereVokabelException
	 */
	protected boolean validiereZeile(String zeile) throws KeinSemicolonException, LeereVokabelException {
		if (zeile.contains(";")) {
			String[] woerter = zeile.split(";");
			if (!((woerter[0].isBlank() || woerter[0].isEmpty()) || (woerter[1].isBlank() || woerter[1].isEmpty()))) {
				return true;
			} else {
				throw new LeereVokabelException();
			}
		} else {
			throw new KeinSemicolonException();
		}
	}

}
