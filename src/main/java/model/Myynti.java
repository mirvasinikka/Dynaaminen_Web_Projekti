package model;

public class Myynti {

	
	private int id;
	private String etunimi, sukunimi, puhelin, sposti;
	public Myynti() {
		
	}
	public Myynti(int id, String etunimi, String sukunimi, String puhelin, String sposti) {
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhelin = puhelin;
		this.sposti = sposti;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	public String getPuhelin() {
		return puhelin;
	}
	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}
	public String getSposti() {
		return sposti;
	}
	public void setSposti(String sposti) {
		this.sposti = sposti;
	}
	@Override
	public String toString() {
		return "Myynti [id=" + id + ", etunimi=" + etunimi + ", sukunumi=" + sukunimi + ", puhelin=" + puhelin
				+ ", sposti=" + sposti + "]";
	}
	
	
	
}
