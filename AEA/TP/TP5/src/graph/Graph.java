package graph;

import java.util.List;

public interface Graph {
	public void addVertex(Vertex v) throws VertexAlreadyExistsException;
	public void addEdge(Vertex v1, Vertex v2) throws IllegalArgumentException;
	public void addEdge(Vertex v1, Vertex v2, int value) throws IllegalArgumentException;
	public Vertex getVertexFromId(String id) throws VertexNotFoundException;
	public List<Vertex> getVertices();
	public void setVertices(List<Vertex> vertices);
	public List<Edge> getEdges();
	public void setEdges(List<Edge> edges);
	public List<Edge> getEdgesFromVertex(Vertex v);
	public List<Vertex> getAdjacentVertices(Vertex v);
	public void resetColours();
	public int getMaxDegree();
	public Vertex getVertexMaxDegree(List<Vertex> listVertices);
     
     /**
      * Retourne le nombre de couleurs différentes dans le voisinage du sommet v,
      * ou dans tout le graphe si v == null.
      * @param v Le sommet considéré ou null
      * @return Le nombre de couleurs différentes
      */
	public int differentColours(Vertex v);
     
	public int getMaxDsat(List<Vertex> listVertices);
     
     /**
      * Recherche du ou des sommet(s) ayant un DSAT maximum dans la liste de sommets indiquée.
		S'il y en a plusieurs, c'est celui avec un degré maximum qui sera retourné.
      */
	public Vertex getVertexMaxDsat(List<Vertex> listVertices);
     
     /**
      * Recherche la "plus petite" couleur à donner au sommet v selon les couleurs de ses voisins.
      * @param v Le sommet à considérer
      * @return Le "plus petite" couleur pour v
      */
	public int smallestColourForVertex(Vertex v);
	
	public int smallestColour(Vertex v);
}
