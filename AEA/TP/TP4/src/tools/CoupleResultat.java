package tools;

import java.util.List;

import graph.Edge;

public class CoupleResultat {

	private long time;
	private List<Edge> listeEdge;
	
	public CoupleResultat(long time, List<Edge> listeEdge) {
		super();
		this.time = time;
		this.listeEdge = listeEdge;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * @return the listeEdge
	 */
	public List<Edge> getListeEdge() {
		return listeEdge;
	}
	/**
	 * @param listeEdge the listeEdge to set
	 */
	public void setListeEdge(List<Edge> listeEdge) {
		this.listeEdge = listeEdge;
	}

	
	
}
