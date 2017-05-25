package main;

import algo.DSatur;
import algo.Naif;
import algo.WelshPowell;
import graph.Graph;
import graph.VertexAlreadyExistsException;
import graph.VertexNotFoundException;
import tools.RandomGraphGenerator;

public class Main {

	public static void main(String[] args) {

		try {
			System.out.println("Construction du graphe...");
			Graph g = RandomGraphGenerator.generateErdosRenyiGraph(500, (float) 0.5, false);
			
			System.out.println("Degre max du graphe : " + g.getMaxDegree());
			
			WelshPowell.welshPowell(g);
			System.out.println("Le nombre de couleurs differentes apres Welsh-Powell est : " + g.differentColours(null));
			g.resetColours();
			
			Naif.naif(g);
			System.out.println("Le nombre de couleurs differentes apres Naif est : " + g.differentColours(null));
			g.resetColours();
			
			DSatur dSat = new DSatur();
			dSat.dSatur(g);
			System.out.println("\nLe nombre de couleurs differentes apres DSATUR est : " + g.differentColours(null));
			g.resetColours();
			
			dSat = new DSatur();
			dSat.dSaturV2(g);
			System.out.println("\nLe nombre de couleurs differentes apres DSATUR v2 est : " + g.differentColours(null));
		} catch (VertexAlreadyExistsException | VertexNotFoundException e) {
			e.printStackTrace();
		}
	}

}
