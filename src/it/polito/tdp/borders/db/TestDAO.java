package it.polito.tdp.borders.db;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		Map<Integer, Country> countries = dao.loadAllCountries();
		for(Entry<Integer, Country> c : countries.entrySet()) {
			System.out.println(c.getValue().toString());
		}
		
		List<Border> borders = dao.getCountryPairs(1983);
		for(Border b : borders) {
			System.out.println(b.toString());
		}
		
	}
}
