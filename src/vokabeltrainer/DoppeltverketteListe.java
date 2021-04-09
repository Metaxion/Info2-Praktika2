package vokabeltrainer;

public class DoppeltverketteListe {

	private String vokabelDeutsch;
	private String vokabelEnglisch;
	private DoppeltverketteListe prev, next;
	
	public DoppeltverketteListe(String vokabelDeutsch, String vokabelEnglisch) {
        this.vokabelDeutsch = vokabelDeutsch;
        this.vokabelEnglisch = vokabelEnglisch;
        next = null;
    }
	
	public String getVokabelDeutsch() {
		return vokabelDeutsch;
	}

	public void setVokabelDeutsch(String vokabelDeutsch) {
		this.vokabelDeutsch = vokabelDeutsch;
	}

	public String getVokabelEnglisch() {
		return vokabelEnglisch;
	}

	public void setVokabelEnglisch(String vokabelEnglisch) {
		this.vokabelEnglisch = vokabelEnglisch;
	}

	public DoppeltverketteListe getPrev() {
		return prev;
	}

	public void setPrev(DoppeltverketteListe prev) {
		this.prev = prev;
	}

	public DoppeltverketteListe getNext() {
		return next;
	}

	public void setNext(DoppeltverketteListe next) {
		this.next = next;
	}

}
