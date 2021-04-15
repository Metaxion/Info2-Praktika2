package vokabeltrainer;

public class DoppeltverketteListe {

	private String vokabelEnglisch;
	private String vokabelDeutsch;
	private DoppeltverketteListe prev, next;
	
	public DoppeltverketteListe(String vokabelEnglisch, String vokabelDeutsch) {
        this.vokabelEnglisch = vokabelEnglisch;
        this.vokabelDeutsch = vokabelDeutsch;
        next = null;
    }
	
	public boolean hasNext() {
		if(next != null) {
			return true;
		} else {
			return false;
		}
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
