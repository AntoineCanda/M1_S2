package algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import graph.Edge;
import graph.GraphImpl;
import tools.CoupleResultat;
/**
 * 
 * Classe implémentant l'algorithme de Kruskal
 * @author antoine et claire
 *
 */
public class Kruskal {
	
	/**
	 * Fonction permettant de savoir dans quel numero de composante connexe dans la liste se trouve un sommet (par son identifiant)
	 * @param idVertex L'identifiant du sommet a verifier
	 * @param compConnexes La liste des composantes connexes de l'arbre couvrant a l'etat actuel
	 * @return Le numero de la composante connexe trouvee
	 */
	public static int compConnexe(String idVertex, ArrayList<ArrayList<Edge>> compConnexes) {
		boolean found = false;
		Iterator<ArrayList<Edge>> it = compConnexes.iterator();
		ArrayList<Edge> comp = null;
		Iterator<Edge> itEdge;
		Edge edge;
		int numComp = -1;
		
		// On cherche dans chaque composante connexe tant qu'on n'a pas trouve
		while(it.hasNext() && !found) {
			comp = (ArrayList<Edge>)it.next();
			numComp++;
			itEdge = comp.iterator();
			// On parcourt toutes les aretes de cette composante connexe jusqu'a
			// les avoir toutes parcourues ou avoir trouve une arete qui comporte
			// le sommet que l'on cherche.
			while(itEdge.hasNext() && !found) {
				edge = itEdge.next();
				if(edge.getDest().getId().equals(idVertex) || edge.getSource().getId().equals(idVertex))
					found = true;
			}
		}
		
		if(!found)
			return -1;
		else
			return numComp;
	}
	
	/**
	 * Methode de fusion des composantes connexes de l'arbre couvrant a l'etat actuel
	 * @param numC1 Le numero de la premiere composante connexe a fusionner
	 * @param numC2 Le numero de la deuxieme composante connexe a fusionner
	 * @param compConnexes La liste des composantes connexes de l'arbre couvrant a l'etat actuel
	 */
	public static void mergeCompConnexe(int numC1, int numC2, ArrayList<ArrayList<Edge>> compConnexes) {
		compConnexes.get(numC1).addAll(compConnexes.get(numC2));
		// Ne pas oublier de retirer la deuxieme composante connexe
		// maintenant qu'elle est fusionnee avec la premiere
		compConnexes.remove(numC2);
	}
	
	/**
	 * Fonction qui verifie la presence d'un cycle ou non dans l'arbre couvrant a l'etat actuel si l'on rajoute une arete
	 * @param idsVerticesVisited L'ensemble des identifiants des sommets deja visites
	 * @param edge L'arete qu'on veut ajouter a l'arbre couvrant si possible
	 * @param compConnexes La liste des composantes connexes de l'arbre couvrant a l'etat actuel
	 * @return Booleen a true si un cycle est present, false sinon
	 * @throws Exception
	 */
	public static boolean checkCycle(Set<String> idsVerticesVisited, Edge edge, ArrayList<ArrayList<Edge>> compConnexes) throws Exception {
		String eDestId = edge.getDest().getId(),
				eSrcId = edge.getSource().getId();
		// Comme on travaille avec un Set, si en essayant d'ajouter
		// l'identifiant du sommet au Set on retourne false de la fonction,
		// alors c'est que cet identifiant de sommet y etait deja present.
		boolean destAdded = idsVerticesVisited.add(eDestId),
				sourceAdded = idsVerticesVisited.add(eSrcId);
		int numC1, numC2;
		
		// Si aucun des identifiants des deux sommets de l'arete n'etaient presents
		// dans la Set, alors c'est que cette arete n'est pas encore liee a l'arbre.
		// C'est donc une nouvelle composante connexe.
		if(sourceAdded && destAdded) {
			ArrayList<Edge> newCompConnexe = new ArrayList<>();
			newCompConnexe.add(edge);
			compConnexes.add(newCompConnexe);
		}
		
		// Sinon, si soit l'identifiant de la source de l'arete, soit l'identifiant de la
		// destination de l'arete etaient deja presents, alors il faut chercher la composante
		// connexe ou se trouve cet identifiant et y ajouter l'arete
		else if(sourceAdded && !destAdded) {
			numC1 = compConnexe(eDestId, compConnexes);
			if(numC1 == -1)
				throw new Exception("Sommet d'identifiant " + eDestId + " non trouve dans une composante connexe !");
			compConnexes.get(numC1).add(edge);
		} else if(!sourceAdded && destAdded) {
			numC2 = compConnexe(eSrcId, compConnexes);
			if(numC2 == -1)
				throw new Exception("Sommet d'identifiant " + eSrcId + " non trouve dans une composante connexe !");
			compConnexes.get(numC2).add(edge);
		}
		
		// Sinon, si les deux identifiants des deux sommets de l'arete etaient deja presents,
		// alors il faut verifier une condition
		else { // if(!sourceAdded && !destAdded)
			numC1 = compConnexe(eDestId, compConnexes);
			numC2 = compConnexe(eSrcId, compConnexes);
			if(numC1 == -1)
				throw new Exception("Sommet d'identifiant " + eDestId + " non trouve dans une composante connexe !");
			else if(numC2 == -1)
				throw new Exception("Sommet d'identifiant " + eSrcId + " non trouve dans une composante connexe !");
			// Si les deux sommets sont dans la meme composante connexe, alors il y a un cycle !
			if(numC1 == numC2)
				return true;
			// Sinon, l'arete est une liaison entre les deux composantes connexes (l'une contenant le sommet
			// source et l'autre contenant le sommet destination). Il faut alors fusionner ces deux composantes
			// connexes en une seule.
			else
				mergeCompConnexe(numC1, numC2, compConnexes);
		}
		return false;
	}

	/**
	 * Fonction permettant de trouver l'arbre couvrant de poids minimal dans un graphe passe en parametre
	 * @param graph Le graphe a considerer
	 * @return La liste des aretes faisant partie de l'arbre couvrant de poids minimal
	 */
	public static CoupleResultat kruskal(GraphImpl graph) {
		System.out.println("Debut de l'algorithme de Kruskal");
		long startingTime = System.nanoTime();
		
		List<Edge> res = new ArrayList<>();
		Set<String> idsVerticesVisited = new TreeSet<>();
		ArrayList<ArrayList<Edge>> composantesConnexes = new ArrayList<>();
		graph.getEdges().sort(null);
		Iterator<Edge> it = graph.getEdges().iterator();
		Edge edge;
		
		while(it.hasNext() &&
				( idsVerticesVisited.size() != graph.getVertices().size()
					|| ( (idsVerticesVisited.size() == graph.getVertices().size()) && composantesConnexes.size() != 1) )
				) {
			edge = it.next();
			try {
				if(!checkCycle(idsVerticesVisited, edge, composantesConnexes)) {
					res.add(edge);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		long endingTime = System.nanoTime();
		System.out.println("Fin de l'algorithme de Kruskal. Temps d'execution : "+ (endingTime-startingTime)/Math.pow(10, 9));
		CoupleResultat cpl = new CoupleResultat(endingTime - startingTime,res);
		return cpl;
	}
}
