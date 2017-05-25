package graph;

public class Edge implements Comparable<Edge> {
	private Vertex source;
	private Vertex dest;
	private int value;
	
	public Edge(Vertex source, Vertex dest) {
		this.source = source;
		this.dest = dest;
		this.value = 1;
	}

	public Edge(Vertex source, Vertex dest, int value) {
		this.source = source;
		this.dest = dest;
		this.value = value;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDest() {
		return dest;
	}

	public void setDest(Vertex dest) {
		this.dest = dest;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Edge otherEdge) {
		if(this.value < otherEdge.value)
			return -1;
		else if(this.value > otherEdge.value)
			return 1;
		
		return 0;
	}
	
	@Override
	public String toString() {
		return this.source.getId() + " -> " + this.dest.getId();
	}	
}
