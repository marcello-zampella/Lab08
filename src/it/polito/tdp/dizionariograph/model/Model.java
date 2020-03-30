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
		WordDAO dao= new WordDAO();
		ArrayList<String> arrivi=new ArrayList<String>();
		for(int d=0;d<parolaInserita.length();d++) {
			parolaInserita.charAt(d);
			StringBuilder parola = new StringBuilder(parolaInserita);
			parola.setCharAt(d, '_');
			arrivi.addAll(dao.getParticularWords(""+parola));
		}
		while (arrivi.contains(parolaInserita)) {
		arrivi.remove(parolaInserita);
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
