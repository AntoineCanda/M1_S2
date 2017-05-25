package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		//  if(!this.vertices.contains(v1) && !this.vertices.contains(v2))
			throw new IllegalArgumentException();
		edges.add(new Edge(v1, v2));
	}
	
	@Override
	public void addEdge(Vertex v1, Vertex v2, int value) throws IllegalArgumentException {
		if(v1 == null || v2 == null || value == 0)
		 // if(!this.vertices.contains(v1) && !this.vertices.contains(v2))
			throw new IllegalArgumentException();
		edges.add(new Edge(v1, v2, value));
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
}
