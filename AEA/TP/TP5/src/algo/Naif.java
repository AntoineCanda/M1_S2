package algo;

import java.util.List;

import graph.Graph;
import graph.Vertex;

public class Naif {
	
	/**
	 * Methode qui trouve la couleur la plus "petite" non utilisee dans une liste de sommets
	 * @param vertices Les sommets a considerer
	 * @return La couleur minimale non utilisee
	 */
	public static int minUnusedColour(List<Vertex> vertices) {
		int minColour = -1;
		for(Vertex v : vertices) {
			if(v.getCouleur() > minColour)
				minColour = v.getCouleur();
		}
		return minColour+1;
	}

	public static void naif(Graph graph) {
		System.out.println("Debut de l'algorithme naif.");
		long startingTime = System.nanoTime();
		
		List<Vertex> verticesGraph = graph.getVertices();
		
		for(Vertex v : verticesGraph) {
			List<Vertex> adjacentVertices = graph.getAdjacentVertices(v);
			v.setCouleur(minUnusedColour(adjacentVertices));
		}
		
		long endingTime = System.nanoTime();
		System.out.println("Fin de l'algorithme naif. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
		
		for(Vertex v : graph.getVertices()){
			System.out.println("Sommet " + v.getId() + " colorie avec la couleur " + v.getCouleur());
		}
	}
}
