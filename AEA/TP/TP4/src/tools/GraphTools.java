package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.GraphImpl;
import graph.Vertex;
import graph.VertexAlreadyExistsException;
/**
 * Classe de quelques outils permettant de créer un fichier représentant un graphe ou au contraire le chercher ainsi que le calcul du poids de toutes ses arrêtes.
 * @author antoine
 *
 */
public class GraphTools {
	/**
	 * Méthode permettant de générer un graphe au fichier texte
	 * @param g le graphe que l'on va utiliser pour générer le fichier
	 * @param file le nom du fichier
	 */
	public static void toTextFormat(Graph g, String file) {
		StringBuilder res = new StringBuilder("");
		List<Edge> edgesToWrite = g.getEdges();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for(Vertex v : g.getVertices()) {
				if(edgesToWrite.isEmpty())
					break;
				res.append(v.getId());
				for(Edge e : g.getEdgesFromVertex(v)) {
					if(e.getSource().equals(v))
						res.append(" " + e.getDest().getId() + " " + e.getValue());
					else // e.getDest().equals(v)
						res.append(" " + e.getSource().getId() + " " + e.getValue());
					edgesToWrite.remove(e);
				}
				res.append("\n");
			}
			bw.write(res.toString());
			System.out.println("Fichier ecrit");
		} catch (IOException e) {
			System.err.println("Erreur d'ecriture dans le fichier " + file);
			e.printStackTrace();
			return;
		}
	}
 
	/**
	 * Fonction permettant de construire un graphe a partir d'un fichier
	 * @param file Le nom du fichier ou se trouve le graphe a lire
	 * @return Le graphe construit
	 */
	public static Graph loadGraphFromFile(String file) {
		Graph graph = new GraphImpl();
		String line;
		String[] elementsLine;
		int i, numLine = 0;
		Vertex v1 = null, v2 = null;
		
		// Dans ce qui suit, comprendre "mot" par "suite de caracteres sur une ligne sans espace"
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while((line = br.readLine()) != null) {
				elementsLine = line.split(" ");
				i = 0;
				while(i < elementsLine.length) {
					// Le premier mot de la ligne courante represente le sommet source courant
					if(i == 0) {
						v1 = new Vertex(elementsLine[i]);
						try {
							graph.addVertex(v1);
						} catch (VertexAlreadyExistsException e) {
							// On ne gere pas cette exception dans ce cas
							// car c'est un cas normal pour cette fonction
						}
					}
					
					// Un mot sur deux, en commencant immediatement apres le premier mot,
					// c'est un sommet destination a partir du sommet source courant
					else if(i % 2 == 1) {
						v2 = new Vertex(elementsLine[i]);
						try {
							graph.addVertex(v2);
						} catch (VertexAlreadyExistsException e) {
							// On ne gere pas cette exception dans ce cas
							// car c'est un cas normal pour cette fonction
						}
					}
					
					// Chaque autre mot represente une valeur de l'arete
					// en cours (dont on a le sommet source et le sommet destination)
					else { // i % 2 == 0 et i != 0
						try {
							graph.addEdge(v1, v2, Integer.parseInt(elementsLine[i]));
						} catch (NumberFormatException e) {
							System.err.println("Erreur : le poids situe au mot " + i + " lu a la ligne " + numLine + " du fichier " + file +" n'est pas un nombre.");
							e.printStackTrace();
							return null;
						}
					}
					
					i++;
				}
				
				numLine++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichier " + file + "non trouve.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("Erreur de lecture dans le fichier " + file);
			e.printStackTrace();
			return null;
		}
		
		return graph;
	}

	/**
	 * Fonction retournant le poids d'un arbre couvrant de poids minimal
	 * @param minSpanningTree L'arbre couvrant de poids minimal a considerer
	 * @return Le poids de cet arbre
	 */
	public static long poids(List<Edge> minSpanningTree) {
		long sum = 0;
		for(Edge edge : minSpanningTree) {
			sum += (long)edge.getValue();
		}
		return sum;
	}
	
}
