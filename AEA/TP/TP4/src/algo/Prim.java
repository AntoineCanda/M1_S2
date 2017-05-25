package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Edge;
import graph.GraphImpl;
import graph.Vertex;
import tools.CoupleResultat;
import tools.FibonacciHeap;
import tools.FibonacciHeap.Node;

public class Prim {

	public static CoupleResultat prim(GraphImpl graph) {
	//	System.out.println("Debut de l'algorithme de Prim");
		long startingTime = System.nanoTime();
		
		List<Edge> res = new ArrayList<>();
		
		// On obtient la liste des arrêtes du graphe que l'on mélange aleatoirement
		ArrayList<Edge> edges = (ArrayList<Edge>) graph.getEdges();
		//Collections.shuffle(edges);
		
		// On cree la liste des sommets visites et on ajoute le sommet source de la premiere arrete de la liste
		List<Vertex> vertexVisited = new ArrayList<Vertex>();
		vertexVisited.add(edges.get(0).getSource());
		//edges.remove(0);
		
		// Booleen qui sert de test d'arret de la boucle
		boolean enCours = true;
		
		// Poids minimum d'une arrete initialise sur la plus grande valeur possible
		int poidsMin = Integer.MAX_VALUE;
		
		// Arrete de poids minimum retenue initialisee a null
		Edge edgeMin = null;
		
		Vertex vertexCourantNonVisite = null;
		
		while(enCours){
			// Position dans la liste de l'arrete la plus faible retenu
			int pos_edge_min = 0;
			
			// Si on a pas d'arrete lors de la boucle alors on a fini 
			enCours = false;
			
			for(int i = 0; i< edges.size(); i++){
				// On verifie si le sommet source ou cible est present dans l'ensemble visite pour trouver une arrete adjacente a ces derniers
				boolean sommetSourcePresent , sommetDestinationPresent;
				Edge edge = edges.get(i);
				sommetSourcePresent = vertexVisited.contains(edge.getSource());
				sommetDestinationPresent = vertexVisited.contains(edge.getDest());

				// Test d'adjacence de l'arrete aux sommets visites
				if((sommetSourcePresent || sommetDestinationPresent) && (!sommetSourcePresent || !sommetDestinationPresent)){
					// Maj des informations si on a une arrete de plus faible poids. 
					if(edge.getValue() < poidsMin){
						poidsMin = edge.getValue();
						edgeMin = edge;
						pos_edge_min = i;
						vertexCourantNonVisite = (sommetSourcePresent)?edge.getDest():edge.getSource();
						enCours = true;
					}
				}
				
			}
			
			// Si on a une arrete de plus faible valeur on ajoute et on met a jour les listes
			if(enCours){
				res.add(edgeMin);
				edges.remove(pos_edge_min);
				vertexVisited.add(vertexCourantNonVisite);
				poidsMin = Integer.MAX_VALUE;
			}
			
		}
		
		long endingTime = System.nanoTime();
		//System.out.println("Fin de l'algorithme de Prim. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
		CoupleResultat cpl = new CoupleResultat(endingTime - startingTime,res);
		return cpl;
	}
	
	/**
	 * Version légèrement optimisée de l'algorithme de Prim utilisant un tas de Fibonacci 
	 * @param graph
	 * @return
	 */
	public static CoupleResultat primOpti(GraphImpl graph) {
	//	System.out.println("Debut de l'algorithme de Prim");
		long startingTime = System.nanoTime();

		List<Edge> res = new ArrayList<>();

		List<Vertex> vertices = graph.getVertices();
		
		Vertex vertex = vertices.remove(0);
		
		// On obtient la liste des arrêtes du graphe que l'on mélange
		// aleatoirement
		List<Edge> edges = graph.getEdgesFromVertex(vertex);
		FibonacciHeap fh = new FibonacciHeap();

		for (Edge edge : edges) {
			fh.insert(edge, edge.getValue());
		}
		// On cree la liste des sommets visites et on ajoute le sommet source de
		// la premiere arrete de la liste
		List<Vertex> vertexVisited = new ArrayList<Vertex>();

		// On recupere la premiere arete de poids min et on ajoute sommet source
		// et destination à l'ensemble
		Edge edge = (Edge)fh.removeMin();

		res.add(edge);
		vertexVisited.add(vertex);
		Vertex newVertex = (edge.getSource().equals(vertex))? edge.getDest() : edge.getSource();
		vertexVisited.add(newVertex);
		
		for (Edge e : graph.getEdgesFromVertex(newVertex)) {
			fh.insert(e, e.getValue());
		}
		
		while (!fh.isEmpty()) {

			
			Edge edgeMin;

			// On recupere l'arrête de poids min
			//
			
			edgeMin = (Edge)fh.removeMin();

			// On verifie si le sommet source ou cible est present dans
			// l'ensemble visite pour trouver une arrete adjacente a ces
			// derniers
			boolean sommetSourcePresent, sommetDestinationPresent;
			sommetSourcePresent = vertexVisited.contains(edgeMin.getSource());
			sommetDestinationPresent = vertexVisited.contains(edgeMin.getDest());

			// On ajoute arrête et sommet nouvellement visité aux ensembles
			if ((sommetSourcePresent || sommetDestinationPresent)
					&& (!sommetSourcePresent || !sommetDestinationPresent)) {
				res.add(edgeMin);
				Vertex vertexCourantNonVisite = (sommetSourcePresent) ? edgeMin.getDest() : edgeMin.getSource();
				vertexVisited.add(vertexCourantNonVisite);
				
				for (Edge e : graph.getEdgesFromVertex(vertexCourantNonVisite)) {
					fh.insert(e, e.getValue());
				}
			}
		}

		long endingTime = System.nanoTime();
		//System.out.println( "Fin de l'algorithme de Prim. Temps d'execution : " + (endingTime - startingTime) / Math.pow(10, 9));
		CoupleResultat cpl = new CoupleResultat(endingTime - startingTime, res);
		return cpl;
	}
	
}
