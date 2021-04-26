package vokabeltrainer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VokabeltrainerTest {

	private Vokabeltrainer trainerA;
	private Vokabeltrainer trainerB;
	private Vokabeltrainer trainerC;
	private Vokabeltrainer trainerD;
	private Vokabeltrainer trainerE;
	private Vokabeltrainer trainerF;
	private Vokabeltrainer trainerG;
	private Vokabeltrainer trainerH;

	@BeforeAll
	public void initTest() {
		trainerA = new Vokabeltrainer();
		trainerB = new Vokabeltrainer();
		trainerC = new Vokabeltrainer();
		trainerD = new Vokabeltrainer();
		trainerE = new Vokabeltrainer();
		trainerF = new Vokabeltrainer();
		trainerG = new Vokabeltrainer();
		trainerH = new Vokabeltrainer();
	}

	/**
	 * Einlesen von Vokabeln
	 */
	@Test
	public void vokabelDateiEinlesenTest1() {
		try {
			trainerA.vokabelDateiEinlesen("vokabelTest1.txt");
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		String actual = trainerA.erstelleStringAlleVokabeln();
		String expected = "red;rot\n" + "blue;blau\n" + "yellow;gelb\n" + "green;gruen\n" + "garden;garten\n"
				+ "house;haus\n" + "living room;wohnzimmer\n" + "bathroom;badezimmer";
		assertEquals(expected, actual);
		System.out.println("Test - VokabelDateiEinlesen - Erfolgreich");
	}
	
	/**
	 * Einlesen einer nicht vorhandenen Datei
	 */
	@Test
	public void vokabelDateiEinlesenTest2() {
		try {
			trainerB.vokabelDateiEinlesen("vokabelTest2.txt");
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		String actual = trainerB.erstelleStringAlleVokabeln();
		String expected = "";
		assertEquals(expected, actual);
		System.out.println("Test - VokabelDateiEinlesen - Erfolgreich");
	}
	
	/**
	 * Einlesen einer Datei mit verschiedenen unerlaubten Leerzeichen
	 */
	@Test
	public void vokabelDateiEinlesenTest3() {
		try {
			trainerC.vokabelDateiEinlesen("vokabelTest3.txt");
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		String actual = trainerC.erstelleStringAlleVokabeln();
		String expected = "test;Test\n"
				+ "white_spaces;leerzeichen\n"
				+ "that;das";
		assertEquals(expected, actual);
		System.out.println("Test - VokabelDateiEinlesen - Erfolgreich");
	}
	
	/**
	 * Speichern von Vokabeln
	 */
	@Test
	public void vokabelDateiSpeichernTest1() {
		File file = new File("vokabelTest4.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		ListenVerwaltung ver = trainerD.listenVerwaltung;
		ver.erstesElementAnhaengen("german;deutsch");
		ver.neuesElementAnhaengen("pineapple;ananas");
		ver.neuesElementAnhaengen("house;haus");
		ver.neuesElementAnhaengen("english;englisch");
		ver.neuesElementAnhaengen("that;das");
		try {
			trainerD.vokabelDateiSpeichern("vokabelTest4.txt");
			
			StringBuilder builder = new StringBuilder();
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String zeile = null;
			while((zeile = rd.readLine()) != null) {
				builder.append(zeile);
				builder.append("\n");
			}
			rd.close();
			assertEquals("german;deutsch\npineapple;ananas\nhouse;haus\nenglish;englisch\nthat;das\n", builder.toString());
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		file.delete();
	}
	
	/**
	 * Speichern von Vokabeln mit unerlaubten Leerzeichen an verschiedenen Stellen
	 */
	@Test
	public void vokabelDateiSpeichernTest2() {
		File file = new File("vokabelTest5.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		ListenVerwaltung ver = trainerE.listenVerwaltung;
		ver.erstesElementAnhaengen(" test; TEST");
		ver.neuesElementAnhaengen("dies ;das ");
		ver.neuesElementAnhaengen(" gabel ; messer ");
		try {
			trainerE.vokabelDateiSpeichern("vokabelTest5.txt");
			
			StringBuilder builder = new StringBuilder();
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String zeile = null;
			while((zeile = rd.readLine()) != null) {
				builder.append(zeile);
				builder.append("\n");
			}
			rd.close();
			assertEquals("test;TEST\ndies;das\ngabel;messer\n", builder.toString());
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		file.delete();
	}
	
	/**
	 * Leere Vokabel-Liste speichern
	 */
	@Test
	public void vokabelDateiSpeichernTest3() {
		File file = new File("vokabelTest6.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
		try {
			trainerF.vokabelDateiSpeichern("vokabelTest6.txt");
			
			StringBuilder builder = new StringBuilder();
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String zeile = null;
			while((zeile = rd.readLine()) != null) {
				builder.append(zeile);
				builder.append("\n");
			}
			rd.close();
			assertEquals("", builder.toString());
		} catch (IOException e) {
			fail(e.getStackTrace().toString());
		}
	}
	
	/**
	 * Verschiedene Eingaben werden ausprobiert
	 */
	@Test
	public void vokabelHinzufuegenTest() {
	    String input = "englischevokabel\ndeutschevokabel";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerG.vokabelHinzufuegen();
	    
	    input = " car \n auto ";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerG.vokabelHinzufuegen();
	    
	    input = " \n ";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerG.vokabelHinzufuegen();

	    assertEquals("englischevokabel;deutschevokabel\ncar;auto", trainerG.erstelleStringAlleVokabeln());
	}
	
	/**
	 * Loeschen verschiedener Vokabeln an verschiedenen Stellen
	 */
	@Test
	public void vokabelLoeschenTest() {
		ListenVerwaltung ver = trainerH.listenVerwaltung;
		ver.erstesElementAnhaengen("pineapple;ananas");
		ver.neuesElementAnhaengen("red;rot");
		ver.neuesElementAnhaengen("yellow;gelb");
		ver.neuesElementAnhaengen("plain;schlicht");
		
		String input = "ananas";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerH.vokabelLoeschen();
	    assertEquals("red;rot\nyellow;gelb\nplain;schlicht", trainerH.erstelleStringAlleVokabeln());
	    
	    input = "yellow";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerH.vokabelLoeschen();
	    assertEquals("red;rot\nplain;schlicht", trainerH.erstelleStringAlleVokabeln());
	    
	    input = "car";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerH.vokabelLoeschen();
	    assertEquals("red;rot\nplain;schlicht", trainerH.erstelleStringAlleVokabeln());
	    
	    input = "schlicht";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerH.vokabelLoeschen();
	    assertEquals("red;rot", trainerH.erstelleStringAlleVokabeln());
	    
	    input = "red";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    trainerH.vokabelLoeschen();
	    assertEquals("", trainerH.erstelleStringAlleVokabeln());
	}
	

}
