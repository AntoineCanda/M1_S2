package tools;

import java.util.Random;

import graph.Graph;
import graph.GraphImpl;
import graph.Vertex;
import graph.VertexAlreadyExistsException;
import graph.VertexNotFoundException;
/**
 * Class RandomGraphGenerator permettant de générer un graphe d'Erdos-Renyi de façon aléatoire
 * @author antoine et claire
 *
 */
public class RandomGraphGenerator {
	/**
	 * Générateur de graphe d'Erdos-Renyi
	 * @param n le nombre de sommet du graphe
	 * @param p la probabilité que deux sommets soient reliés par une arrête
	 * @return un graph g
	 * @throws VertexAlreadyExistsException
	 * @throws VertexNotFoundException
	 */
	public static Graph generateErdosRenyiGraph(int n, float p) throws VertexAlreadyExistsException, VertexNotFoundException {
		// Initialisation du graphe et du générateur de nombre aléatoire.
		Graph graph = new GraphImpl();
		int i, j;
		Random random = new Random();
		double nbRandom;
		int nbRandomN;
		// Borne supérieure de la valeur d'une arrête
		int N = (int) Math.pow(n, 4); 
		
		// Ajout des sommets au graphe
		for(i = 0; i < n; i++) {
			graph.addVertex(new Vertex(Integer.toString(i)));
		}
		
		// Ajout des arrêtes valuées au graphe
		for(i = 0; i < n; i++) {
			for(j = i+1; j < n; j++) {
				// Calcul d'un flottant entre 0 et 1 dont le but est de déterminer si on ajoute une arrête entre les sommets i et j.
				nbRandom = random.nextFloat();

				if(nbRandom <= p) {
					// Calcul de la valeur de l'arrête
					nbRandomN = random.nextInt(N) + 1;
					graph.addEdge(graph.getVertexFromId(Integer.toString(i)), graph.getVertexFromId(Integer.toString(j)), nbRandomN);
				}
			}
		}
		
		return graph;
	}
}
