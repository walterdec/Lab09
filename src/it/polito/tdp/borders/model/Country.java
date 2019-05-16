package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{
	
	private String StateAbb;
	private int CCode;
	private String StateNme;
	
	
	public Country(String stateAbb, int cCode, String stateNme) {
		StateAbb = stateAbb;
		CCode = cCode;
		StateNme = stateNme;
	}
	
	public String getStateAbb() {
		return StateAbb;
	}

	public int getCCode() {
		return CCode;
	}

	public String getStateNme() {
		return StateNme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (CCode != other.CCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return StateNme;
	}

	@Override
	public int compareTo(Country altro) {
		return this.StateNme.compareTo(altro.StateNme);
	}
	
	
}
