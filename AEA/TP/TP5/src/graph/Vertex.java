package graph;

import java.util.Set;
import java.util.TreeSet;

public class Vertex implements Comparable<Vertex> {
	private String id;
	private int degre;
	private int dsat;
	private Set<Integer> couleursAdj;
	private int couleur;
	
	public Vertex(String id) {
		this.id = id;
		this.degre = 0;
		this.couleursAdj = new TreeSet<>();
		this.dsat = 0;
		this.couleur = -1;
	}
	
	public String getId() {
		return id;
	}
	
	public int getDegre(){
		return this.degre;
	}
	
	public int getCouleur() {
		return couleur;
	}

	public Set<Integer> getCouleursAdj() {
		return couleursAdj;
	}

	public int getDsat() {
		return dsat;
	}
	
	public void incrementeDegre(){
		this.degre++;
	}
	
	public void setDsat(int dsat) {
		this.dsat = dsat;
	}
	
	public void incrementeDsat(){
		this.dsat++;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Sommet ");
		str.append(id);
		str.append(" :\nDegre : ");
		str.append(degre);
		str.append("\nDSAT : ");
		if(dsat == 0)
			str.append("indefini");
		else
			str.append(dsat);
		str.append("\nCouleurs des sommets adjacents : ");
		for(int couleur : couleursAdj) {
			str.append(couleur);
			str.append(" ");
		}
		str.append("\nCouleur : ");
		if(couleur == -1)
			str.append("non colorie");
		else
			str.append(couleur);
		str.append("\n\n");
		return str.toString();
	}
	
	@Override
	public int compareTo(Vertex otherVertex) {
		if(this.degre < otherVertex.degre)
			return 1;
		else if(this.degre > otherVertex.degre)
			return -1;
		
		return 0;
	}
}
