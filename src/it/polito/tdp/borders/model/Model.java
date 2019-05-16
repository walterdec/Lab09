package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Graph<Country, DefaultEdge> grafo;
	BordersDAO dao;

	public Model() {
		dao = new BordersDAO();
	}

	public void calcolaConfini(int anno) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		List<Country> countries = new ArrayList<Country>();
		Map<Integer, Country> countriesMap = dao.loadAllCountries();
		for(Entry<Integer, Country> c : countriesMap.entrySet()) {
			countries.add(c.getValue());
		}
		Graphs.addAllVertices(grafo, countries);
		
		List<Border> borders = dao.getCountryPairs(anno);
		for(Border b : borders) {
			grafo.addEdge(b.getCountry1(), b.getCountry2());
		}
	}
	
	public int getGradoVertice(Country country) {
		return grafo.degreeOf(country);
	}
	
	public List<Country> getVicini(Country country){
		return Graphs.neighborListOf(grafo, country);
	}
	
	public Set<Country> getVertici(){
		return grafo.vertexSet();
	}

}
