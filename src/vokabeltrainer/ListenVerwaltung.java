package vokabeltrainer;

public class ListenVerwaltung {

	private DoppeltverketteListe anfang;
	private DoppeltverketteListe ende;
	
	public ListenVerwaltung() {
	}
	
	/**
	 * Fuellt den Anfang der Liste mit den Vokabeln aus der uebergebenen zeile
	 * @param zeile
	 */
	public void erstesElementAnhaengen(String zeile) {
		String[] woerter = zeile.split(";");
		anfang = new DoppeltverketteListe(woerter[0].trim(), woerter[1].trim());
		anfang.setPrev(null);
		ende = anfang;
	}
	
	/**
	 * Fuellt die naechste Stelle der Liste mit den Vokabeln aus der uebergebenen zeile
	 * @param zeile
	 */
	public void neuesElementAnhaengen(String zeile) {
		String[] woerter = zeile.split(";");
		ende.setNext(new DoppeltverketteListe(woerter[0].trim(), woerter[1].trim()));
		ende.getNext().setPrev(ende);
		ende = ende.getNext();
	}
	
	public DoppeltverketteListe getEnde() {
		return ende;
	}

	public void setEnde(DoppeltverketteListe ende) {
		this.ende = ende;
	}

	public DoppeltverketteListe getAnfang() {
		return anfang;
	}

	public void setAnfang(DoppeltverketteListe anfang) {
		this.anfang = anfang;
	}

	/**
	 * Loescht das uebergebene Element liste aus der Liste
	 * @param liste
	 */
	public void loescheElement(DoppeltverketteListe liste) {
		//Erstes Element
		if(liste.getPrev() == null) {
			//Und kein weiteres Element
			if(liste.getNext() == null) {
				anfang = null;
				ende = anfang;
			} 
			//Erstes Element von mehreren
			else {
				anfang = anfang.getNext();
				anfang.setPrev(null);
			}
		}
		//Letztes Element
		else if(liste.getNext() == null) {
			ende = ende.getPrev();
			ende.setNext(null);
		}
		//Mitten drin
		else {
			DoppeltverketteListe listeNext = liste.getNext();
			DoppeltverketteListe listePrev = liste.getPrev();
			listePrev.setNext(listeNext);
			listeNext.setPrev(listePrev);
		}
	}

}
