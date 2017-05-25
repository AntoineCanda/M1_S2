package algo;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import graph.Vertex;

public class WelshPowell {

	public static void welshPowell(Graph graph) {
		System.out.println("Debut de l'algorithme de Welsh-Powell.");
		long startingTime = System.nanoTime();
		
		// Trier les sommets selon leurs degrés décroissants
		graph.getVertices().sort(null);
		
		List<Vertex> verticesNotYetColoured = new ArrayList<>(graph.getVertices());
		int couleur = 0;
		
		while(!verticesNotYetColoured.isEmpty()) {
			Vertex v = verticesNotYetColoured.remove(0);
			v.setCouleur(couleur);
			List<Vertex> nonAdjacentVertices = new ArrayList<>(verticesNotYetColoured);
			nonAdjacentVertices.removeAll(graph.getAdjacentVertices(v));
			Vertex v2;
			while(!nonAdjacentVertices.isEmpty()){
				v2 = nonAdjacentVertices.remove(0);
				verticesNotYetColoured.remove(v2);
				v2.setCouleur(couleur);
				nonAdjacentVertices.removeAll(graph.getAdjacentVertices(v2));
			}
			couleur++;
		}
		
		long endingTime = System.nanoTime();
		System.out.println("Fin de l'algorithme de Welsh-Powell. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
	
		for(Vertex v : graph.getVertices()){
			System.out.println("Sommet " + v.getId() + " colorie avec la couleur " + v.getCouleur());
		}

	}
	
}
