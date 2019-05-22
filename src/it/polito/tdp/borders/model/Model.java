package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private class EdgeTraversedGraphListener implements TraversalListener<Country, DefaultEdge> {

		@Override
		public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> ev) {
			Country sourceVertex = grafo.getEdgeSource(ev.getEdge());
			Country targetVertex = grafo.getEdgeTarget(ev.getEdge());
			
			if(!visite.containsKey(targetVertex) && visite.containsKey(sourceVertex)) {
				visite.put(targetVertex, sourceVertex);
			} else if(!visite.containsKey(sourceVertex) && visite.containsKey(targetVertex)) {
				visite.put(sourceVertex, targetVertex);
			}			
			
		}

		@Override
		public void vertexFinished(VertexTraversalEvent<Country> arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<Country> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private Graph<Country, DefaultEdge> grafo;
	BordersDAO dao;
	Map<Country, Country> visite;

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
	
	
	public List<Country> paesiRaggiungibili(Country source){
	/*	List<Country> result = new LinkedList<Country>();
		visite = new HashMap<Country, Country>();
		
		GraphIterator<Country, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo, source);
		it.addTraversalListener(new Model.EdgeTraversedGraphListener());
		
		visite.put(source, null);
		
		while(it.hasNext()) {
			result.add(it.next());
		}
		result.remove(source);
		return result;*/
		return Graphs.neighborListOf(grafo, source);
	}
	
	public int getGradoVertice(Country country) {
		return grafo.degreeOf(country);
	}
	
	public List<Country> getVicini(Country country){
		return Graphs.neighborListOf(grafo, country);
	}
	
	public List<Country> getVertici(){
		List<Country> vertici = new LinkedList<Country>();
		vertici.addAll(grafo.vertexSet());
		Collections.sort(vertici);
		
		return vertici;
	}
	
	public int getComponentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(this.grafo);
		return ci.connectedSets().size();
	}

}
