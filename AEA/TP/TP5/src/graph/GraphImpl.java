package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GraphImpl implements Graph {
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	public GraphImpl() {
		this.vertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}

	public GraphImpl(List<Vertex> vertices, List<Edge> edges){
		this.vertices = vertices;
		this.edges = edges;
	}
	
	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}

	@Override
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public void addVertex(Vertex v) throws VertexAlreadyExistsException {
		Iterator<Vertex> it = vertices.iterator();
		Vertex vertex;
		boolean trouve = false;
		
		while(it.hasNext() && !trouve) {
			vertex = it.next();
			if(vertex.getId().equals(v.getId()))
				trouve = true;
		}
		
		if(trouve)
			throw new VertexAlreadyExistsException("Le sommet d'identifiant " + v.getId() + "existe deja dans le graphe.");
		
		vertices.add(v);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) throws IllegalArgumentException {
		if(v1 == null || v2 == null)
			throw new IllegalArgumentException();
		edges.add(new Edge(v1, v2));
		v1.incrementeDegre();
		v2.incrementeDegre();
		v1.incrementeDsat();
		v2.incrementeDsat();
	}
	
	@Override
	public void addEdge(Vertex v1, Vertex v2, int value) throws IllegalArgumentException {
		if(v1 == null || v2 == null || value == 0)
			throw new IllegalArgumentException();
		edges.add(new Edge(v1, v2, value));
		v1.incrementeDegre();
		v2.incrementeDegre();
	}

	@Override
	public Vertex getVertexFromId(String id) throws VertexNotFoundException {
		Iterator<Vertex> it = vertices.iterator();
		Vertex v = null;
		boolean trouve = false;
		
		while(it.hasNext() && !trouve) {
			v = it.next();
			if(v.getId().equals(id))
				trouve = true;
		}
		
		if(!trouve)
			throw new VertexNotFoundException("Le sommet d'identifiant " + id + " n'existe pas.");
		
		return v;
	}
	
	@Override
	public List<Edge> getEdgesFromVertex(Vertex v) {
		List<Edge> edgesFromVertex = new ArrayList<>();
		
		for(Edge e : this.getEdges()) {
			if(e.getSource().equals(v) || e.getDest().equals(v)) {
				edgesFromVertex.add(e);
			}
		}
		
		return edgesFromVertex;
	}
	
	/**
	 * Methode qui permet de recuperer les sommets adjacent au sommet v
	 * @param v le sommet que l'on considere
	 * @return la liste des sommets adjacent au sommet v
	 */
	@Override
	public List<Vertex> getAdjacentVertices(Vertex v) {
		List<Edge> edgesFromVertex = this.getEdgesFromVertex(v);
		List<Vertex> adjacentVertices = new ArrayList<Vertex>();
		
		for(Edge e : edgesFromVertex){
			Vertex vertex = (e.getSource().equals(v))? e.getDest(): e.getSource();
			adjacentVertices.add(vertex);
		}
		return adjacentVertices;
	}

	@Override
	public void resetColours() {
		for(Vertex v : this.getVertices()) {
			v.setCouleur(-1);
		}
	}
	
	@Override
	public int getMaxDegree() {
		int maxDegree = 0;
		for(Vertex v : vertices) {
			if(v.getDegre() > maxDegree)
				maxDegree = v.getDegre();
		}
		return maxDegree;
	}
	
	@Override
	public Vertex getVertexMaxDegree(List<Vertex> listVertices) {
		Vertex vertexMaxDegree = null;
		if(listVertices != null && !listVertices.isEmpty()) {
			vertexMaxDegree = listVertices.get(0);
			int maxDegree = 0;
			for(Vertex v : listVertices) {
				if(v.getDegre() > maxDegree) {
					vertexMaxDegree = v;
					maxDegree = v.getDegre();
				}
			}
		}
		return vertexMaxDegree;
	}
	
	@Override
	public int differentColours(Vertex v) {
		List<Vertex> adjacentVertices;
		if(v != null)
			adjacentVertices = this.getAdjacentVertices(v);
		else
			adjacentVertices = this.getVertices();
		Set<Integer> differentColours = new TreeSet<>();
		
		for(Vertex adjV : adjacentVertices) {
			int couleur = adjV.getCouleur();
			if(couleur != -1)
				differentColours.add(couleur);
		}
		
		if(differentColours.isEmpty())
			return v.getDegre();
		
		return differentColours.size();
	}
	
	@Override
	public int getMaxDsat(List<Vertex> listVertices) {
		int maxDsat = 0;
		for(Vertex v : listVertices) {
			if(v.getDsat() > maxDsat)
				maxDsat = v.getDsat();
		}
		return maxDsat;
	}
	
	@Override
	public Vertex getVertexMaxDsat(List<Vertex> listVertices) {
		List<Vertex> verticesMaxDsat = new ArrayList<>();
		int maxDsat = getMaxDsat(listVertices);
		
		for(Vertex v : listVertices) {
			if(v.getDsat() == maxDsat)
				verticesMaxDsat.add(v);
		}
		
		if(verticesMaxDsat.size() == 1)
			return verticesMaxDsat.get(0);
		
		return getVertexMaxDegree(verticesMaxDsat);
	}
	
	public int getMaxColour(List<Vertex> vertices) {
		int maxColour = -1;
		for(Vertex v : vertices) {
			if(v.getCouleur() > maxColour)
				maxColour = v.getCouleur();
		}
		return maxColour;
	}
	
	@Override
	public int smallestColourForVertex(Vertex v) {
		List<Vertex> adjacentVertices = this.getAdjacentVertices(v);
		List<Integer> listColours = new ArrayList<>();
		int smallestColour = -1;
		boolean smallestColourFound = false;
		
		for(Vertex vAdj : adjacentVertices) {
			listColours.add(vAdj.getCouleur());
		}
		
		listColours.sort(null);
		
		int colour = 0, previousColour = -1;
		Iterator<Integer> itColour = listColours.iterator();
		while(itColour.hasNext() && !smallestColourFound) {
			colour = itColour.next();
			if(colour > previousColour+1) {
				smallestColour = previousColour+1;
				smallestColourFound = true;
			}
			previousColour = colour;
		}
		
		if(!smallestColourFound) 
			return colour+1;
		
		return smallestColour;
	}
	
	@Override
	public int smallestColour(Vertex v) {
		int smallestColour = 0;
		
		List<Integer> coloursAdjSorted = new ArrayList<>(v.getCouleursAdj());
		if(coloursAdjSorted.isEmpty())
			return smallestColour;
		
		coloursAdjSorted.sort(null);
		
		while(smallestColour < coloursAdjSorted.size() && coloursAdjSorted.get(smallestColour) == smallestColour) {
			smallestColour++;
		}
		
		return smallestColour;
	}
	
}
