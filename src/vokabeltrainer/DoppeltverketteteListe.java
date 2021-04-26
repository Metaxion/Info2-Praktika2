package vokabeltrainer;

public class DoppeltverketteteListe {

	private String vokabelEnglisch;
	private String vokabelDeutsch;
	private DoppeltverketteteListe prev, next;
	
	public DoppeltverketteteListe(String vokabelEnglisch, String vokabelDeutsch) {
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

	public DoppeltverketteteListe getPrev() {
		return prev;
	}

	public void setPrev(DoppeltverketteteListe prev) {
		this.prev = prev;
	}

	public DoppeltverketteteListe getNext() {
		return next;
	}

	public void setNext(DoppeltverketteteListe next) {
		this.next = next;
	}

}
