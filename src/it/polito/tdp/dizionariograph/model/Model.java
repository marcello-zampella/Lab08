package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;


public class Model {
	
	private Graph<String,DefaultEdge> grafo;
	private List<String> parole;

	public void createGraph(int numeroLettere) {
		this.grafo=new SimpleDirectedGraph<>(DefaultEdge.class);
		WordDAO dao= new WordDAO();
		parole=dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(grafo, parole);
		int k=0;
		int t=0;
		for(String partenza: grafo.vertexSet()) {
			for(String arrivo: parole) {
				k=0;
				t=0;
				for(int d=0; d<numeroLettere;d++) {
					if(partenza.charAt(d)!=arrivo.charAt(d))
						k++;
					else
						t++;
					if(k==2)
						break;
					if(t==numeroLettere-1 && !partenza.equals(arrivo))
						grafo.addEdge(partenza, arrivo);
				}
			}
		}
		
		
	}

	public ArrayList<String> displayNeighbours(String parolaInserita) {
		if(this.grafo==null)
			return null;
		ArrayList<String> arrivi=new ArrayList<String>();
		for(DefaultEdge e: grafo.edgesOf(parolaInserita)) {
			if(!parolaInserita.equals(grafo.getEdgeSource(e)))
				arrivi.add(grafo.getEdgeSource(e));
			else
				arrivi.add(grafo.getEdgeTarget(e));
			
		}
		return arrivi;
	}

	public int findMaxDegree() {
		if(this.grafo==null)
			return -1;
		int max=0;
		int val;
		for (String temp: grafo.vertexSet()) {
			val=grafo.degreeOf(temp);
			if(val>max)
			max=val;
		}
		return max;
	}
}
