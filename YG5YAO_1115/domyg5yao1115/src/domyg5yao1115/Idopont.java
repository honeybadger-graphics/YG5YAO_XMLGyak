package domyg5yao1115;

public class Idopont {

	public Idopont() {
		super();
	}
	String Nap;
	String Kezdet;
	String Vege;
	
	public Idopont(String nap, String kezdet, String vege) {
		super();
		Nap = nap;
		Kezdet = kezdet;
		Vege = vege;
	}
	public String getNap() {
		return Nap;
	}
	public void setNap(String nap) {
		Nap = nap;
	}
	public String getKezdet() {
		return Kezdet;
	}
	public void setKezdet(String kezdet) {
		Kezdet = kezdet;
	}
	public String getVege() {
		return Vege;
	}
	public void setVege(String vege) {
		Vege = vege;
	}
	@Override
	public String toString() {
		return "Idopont [Nap=" + Nap + ", Kezdet=" + Kezdet + ", Vege=" + Vege + "]";
	}
	

}
