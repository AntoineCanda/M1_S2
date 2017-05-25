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
}
