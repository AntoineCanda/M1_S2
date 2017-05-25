package algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graph.Graph;
import graph.Vertex;
import graph.VertexNotFoundException;

public class DSatur {
	private List<Vertex> verticesNotYetColoured;
	private int dSatMax;
	
	private Vertex getNextUnvisitedVertex() {
		Iterator<Vertex> itVert = verticesNotYetColoured.iterator();
		boolean trouve = false;
		Vertex vertex = null;
		
		while(itVert.hasNext() && !trouve) {
			vertex = itVert.next();
			if(vertex.getCouleursAdj().isEmpty())
				trouve = true;
		}
		
		if(!trouve)
			return null;
		
		return vertex;
	}
	
	private Vertex getFirstWithMaxDSAT() {
		Iterator<Vertex> itVert;
		boolean trouve = false;
		Vertex vertex = null;
		
		while(!trouve) {
			itVert = verticesNotYetColoured.iterator();
			while(itVert.hasNext() && !trouve) {
				vertex = itVert.next();
				if(vertex.getCouleursAdj().size() == dSatMax)
					trouve = true;
			}
			if(!trouve)
				dSatMax--;
		}
		
		if(!trouve)
			return null;
		
		return vertex;
	}
	
	public void dSatur(Graph graph) throws VertexNotFoundException {
		System.out.println("Debut de l'algorithme de DSATUR.");
		long startingTime = System.nanoTime();
		
		// Trier les sommets selon leurs degrés décroissants
		graph.getVertices().sort(null);
		
		verticesNotYetColoured = new ArrayList<>(graph.getVertices());
		Vertex currentVertex = graph.getVertices().get(0);
		
		// Colorer le sommet de degré maximum avec 0
		currentVertex.setCouleur(0);
		
		// Compter le nombre de sommets coloriés
		int nbSomCol = 0;
		
		// Nombre de sommets total
		int nbSomTotal = graph.getVertices().size();
		
		while(!verticesNotYetColoured.isEmpty()) {
			
			// Mise à jour des DSAT pour les sommets adjacents au sommet courant
			List<Vertex> adjacentVertices = graph.getAdjacentVertices(currentVertex);
			for(Vertex v : adjacentVertices) {
				if(v.getDsat() == 0)
					v.setDsat(1);
				else
					v.setDsat(graph.differentColours(v));
			}
			
			if(verticesNotYetColoured.remove(currentVertex)) {
				nbSomCol++;
				System.out.print("\rSommets colories : " + nbSomCol + " sur " + nbSomTotal);
				if(!verticesNotYetColoured.isEmpty()) {
					// Prochain sommet de DSAT maximum ou sinon de degré maximum
					currentVertex = graph.getVertexMaxDsat(verticesNotYetColoured);
					int smallestColour = graph.smallestColourForVertex(currentVertex);
					currentVertex.setCouleur(smallestColour);
				}
			} else {
				throw new VertexNotFoundException("Tentative de suppression du graphe d'une arete qui n'existe pas");
			}
		}
		
		long endingTime = System.nanoTime();
		System.out.println("\nFin de l'algorithme de DSATUR. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
	
		for(Vertex v : graph.getVertices()){
			System.out.println("Sommet " + v.getId() + " colorie avec la couleur " + v.getCouleur());
		}
	}
	
	public void dSaturV2(Graph graph) throws VertexNotFoundException {
		System.out.println("Debut de l'algorithme de DSATUR v2.");
		long startingTime = System.nanoTime();
		
		// Trier les sommets selon leurs degrés décroissants
		graph.getVertices().sort(null);
		
		// On prend d'abord le sommet de degré maximum
		Vertex currentVertex = graph.getVertices().get(0);
		
		// Le colorier avec la couleur 0
		int couleur = 0;
		currentVertex.setCouleur(couleur);
		
		// La liste des sommets non encore coloriés
		verticesNotYetColoured = new ArrayList<>(graph.getVertices());
		
		// On va sauvegarder, pour chaque sommet, la liste des couleurs
		// de ses voisins coloriés. La taille de cette liste détermine
		// son DSAT à un moment donné
		/*@SuppressWarnings("unchecked")
		List<Vertex> listesCouleursAdj[] = (List<Vertex>[])new List[graph.getVertices().size()];
		
		for(List<Vertex> liste : listesCouleursAdj) {
			liste = new ArrayList<Vertex>();
		}*/
		
		// La référence d'un sommet non "visité"
		// On dit d'un sommet qu'il a été visité si sa liste de couleurs de ses sommets
		// adjacents n'est pas vide
		Vertex unvisitedVertex = null;
		
		// On garde en mémoire le DSAT maximum de tous les sommets non coloriés
		dSatMax = 0;
		
		// On garde en mémoire si les sommets ont tous été "visités" ou non
		boolean allVisited = false;
		
		while(!verticesNotYetColoured.isEmpty()) {
			
			// Mise à jour des DSAT pour les sommets adjacents au sommet courant
			List<Vertex> adjacentVertices = graph.getAdjacentVertices(currentVertex);
			for(Vertex v : adjacentVertices) {
				if(v.getCouleur() == -1) {
					v.getCouleursAdj().add(couleur);
					int dSat = v.getCouleursAdj().size();
					if(dSat > dSatMax)
						dSatMax = dSat;
				}
			}
			
			if(verticesNotYetColoured.remove(currentVertex)) {
				/*nbSomCol++;
				System.out.print("\rSommets colories : " + nbSomCol + " sur " + nbSomTotal);*/
				if(!verticesNotYetColoured.isEmpty()) {
					// Prochain sommet de DSAT maximum ou sinon de degré maximum
					if(!allVisited && (unvisitedVertex == null || unvisitedVertex.getDegre() != 0 || !unvisitedVertex.getCouleursAdj().isEmpty())) {
						unvisitedVertex = getNextUnvisitedVertex();
						if(unvisitedVertex != null) {
							currentVertex = unvisitedVertex;
							if(currentVertex.getDegre() <= dSatMax)
								currentVertex = getFirstWithMaxDSAT();
						} else {
							allVisited = true;
							currentVertex = getFirstWithMaxDSAT();
						}
					} else {
						currentVertex = getFirstWithMaxDSAT();
					}
					
					couleur = graph.smallestColour(currentVertex);
					currentVertex.setCouleur(couleur);
				}
			} else {
				throw new VertexNotFoundException("Tentative de suppression du graphe d'une arete qui n'existe pas");
			}
		}
		
		long endingTime = System.nanoTime();
		System.out.println("Fin de l'algorithme de DSATUR v2. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
	
		for(Vertex v : graph.getVertices()){
			System.out.println("Sommet " + v.getId() + " colorie avec la couleur " + v.getCouleur());
		}
	}

}
