package vokabeltrainer;

public class ListenVerwaltung {

	private DoppeltverketteteListe anfang;
	private DoppeltverketteteListe ende;
	
	/**
	 * Fuellt den Anfang der Liste mit den Vokabeln aus der uebergebenen zeile
	 * @param zeile
	 */
	public void erstesElementAnhaengen(String zeile) {
		String[] woerter = zeile.split(";");
		anfang = new DoppeltverketteteListe(woerter[0].trim(), woerter[1].trim());
		anfang.setPrev(null);
		ende = anfang;
	}
	
	/**
	 * Fuellt die naechste Stelle der Liste mit den Vokabeln aus der uebergebenen zeile
	 * @param zeile
	 */
	public void neuesElementAnhaengen(String zeile) {
		String[] woerter = zeile.split(";");
		ende.setNext(new DoppeltverketteteListe(woerter[0].trim(), woerter[1].trim()));
		ende.getNext().setPrev(ende);
		ende = ende.getNext();
	}
	
	public DoppeltverketteteListe getEnde() {
		return ende;
	}

	public void setEnde(DoppeltverketteteListe ende) {
		this.ende = ende;
	}

	public DoppeltverketteteListe getAnfang() {
		return anfang;
	}

	public void setAnfang(DoppeltverketteteListe anfang) {
		this.anfang = anfang;
	}

	/**
	 * Loescht das uebergebene Element liste aus der Liste
	 * @param liste
	 */
	public void loescheElement(DoppeltverketteteListe liste) {
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
			DoppeltverketteteListe listeNext = liste.getNext();
			DoppeltverketteteListe listePrev = liste.getPrev();
			listePrev.setNext(listeNext);
			listeNext.setPrev(listePrev);
		}
	}

}
