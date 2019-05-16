package it.polito.tdp.borders.model;

public class Border {
	
	private Country country1;
	private Country country2;
	private int year;
	
	public Border(Country country1, Country country2, int year) {
		this.country1 = country1;
		this.country2 = country2;
		this.year = year;
	}

	public Country getCountry1() {
		return country1;
	}

	public Country getCountry2() {
		return country2;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return country1 + ", " + country2 + ", " + year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country1 == null) ? 0 : country1.hashCode());
		result = prime * result + ((country2 == null) ? 0 : country2.hashCode());
		result = prime * result + year;
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
		Border other = (Border) obj;
		if (country1 == null) {
			if (other.country1 != null)
				return false;
		} else if (!country1.equals(other.country1))
			return false;
		if (country2 == null) {
			if (other.country2 != null)
				return false;
		} else if (!country2.equals(other.country2))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	

}
