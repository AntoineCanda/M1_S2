package tools;

import java.util.Random;

import graph.Graph;
import graph.GraphImpl;
import graph.Vertex;
import graph.VertexAlreadyExistsException;
import graph.VertexNotFoundException;

public class RandomGraphGenerator {
	public static Graph generateErdosRenyiGraph(int n, float p, boolean poids) throws VertexAlreadyExistsException, VertexNotFoundException {
		Graph graph = new GraphImpl();
		int i, j;
		Random random = new Random();
		double nbRandom;
		int nbRandomN;
		int N = 0;
		
		if(poids)
			N = (int) Math.pow(n, 4);
		
		for(i = 0; i < n; i++) {
			graph.addVertex(new Vertex(Integer.toString(i)));
		}
		
		for(i = 0; i < n; i++) {
			for(j = i+1; j < n; j++) {
				System.out.print("\rSommet " + i + " et sommet " + j);
				nbRandom = random.nextFloat();
				if(poids)
					nbRandomN = random.nextInt(N) + 1;
				else
					nbRandomN = -1;
				if(nbRandom <= p) {
					if(nbRandomN != -1)
						graph.addEdge(graph.getVertexFromId(Integer.toString(i)), graph.getVertexFromId(Integer.toString(j)), nbRandomN);
					else
						graph.addEdge(graph.getVertexFromId(Integer.toString(i)), graph.getVertexFromId(Integer.toString(j)));
				}
			}
		}
		
		System.out.print("\n");
		
		return graph;
	}
}
