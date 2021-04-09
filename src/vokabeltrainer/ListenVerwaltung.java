package vokabeltrainer;

public class ListenVerwaltung {

	private DoppeltverketteListe anfang;
	private DoppeltverketteListe ende;
	
	public ListenVerwaltung() {
	}
	
	public void erstesElementAnhaengen(String zeile) {
		String[] woerter = zeile.split(";");
		anfang = new DoppeltverketteListe(woerter[0].trim(), woerter[1].trim());
		anfang.setPrev(null);
		ende = anfang;
	}
	
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

}
