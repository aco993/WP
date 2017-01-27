package beans;

import java.io.Serializable;

public class Temp implements Serializable{
	
	private String naslov;

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Temp(String naslov) {
		super();
		this.naslov = naslov;
	}
	
	public Temp(){
		
	}

}
