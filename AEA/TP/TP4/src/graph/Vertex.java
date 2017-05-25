package graph;

public class Vertex {
	private String id;
	
	public Vertex(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean equals(Object o){
		if(o == null)
			return false;
		if(o == this)
			return true;
		if(o instanceof Vertex){
			Vertex v = (Vertex)o;
			
			return this.getId().equals(v.getId());
		}
		
		return false;
	}
}
