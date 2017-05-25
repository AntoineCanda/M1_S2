package main;

import java.util.List;

import algo.Kruskal;
import algo.Prim;
import graph.Edge;
import graph.Graph;
import graph.GraphImpl;
import graph.VertexAlreadyExistsException;
import graph.VertexNotFoundException;
import tools.GraphTools;
import tools.RandomGraphGenerator;

public class Main {

	public static void main(String[] args) throws VertexAlreadyExistsException, VertexNotFoundException {
		
		/*
		GraphImpl graph = (GraphImpl) GraphTools.loadGraphFromFile(args[0]);
		
		List<Edge> minSpanningTreeK = Kruskal.kruskal(graph).getListeEdge();
		if(minSpanningTreeK != null) {
			System.out.println("L'arbre couvrant de poids minimum du graphe a un poids de " + GraphTools.poids(minSpanningTreeK)
			+ " et passe par les aretes suivantes :");
			for(Edge edge : minSpanningTreeK) {
				System.out.println(edge.toString());
			}
		}
		
		List<Edge> minSpanningTreeP = Prim.primOpti(graph).getListeEdge();
		if(minSpanningTreeP != null) {
			System.out.println("L'arbre couvrant de poids minimum du graphe a un poids de " + GraphTools.poids(minSpanningTreeP)
			+ " et passe par les aretes suivantes :");
			for(Edge edge : minSpanningTreeP) {
				System.out.println(edge.toString());
			}
		}
		*/
		/*
		int n = Integer.parseInt(args[1]);
		float p = Float.parseFloat(args[2]);
		try {
			Graph graphR = RandomGraphGenerator.generateErdosRenyiGraph(n, p);
			System.out.println("graphe genere");
			GraphTools.toTextFormat(graphR, args[0]);
		} catch (VertexAlreadyExistsException | VertexNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		*/
		
		/*
		for(float p=(float) 0.1; p<=1.0; p+=0.2){
			long timeK = 0;
			long timeP = 0;
			for(int i=0; i<50; i++){
				GraphImpl g = (GraphImpl) RandomGraphGenerator.generateErdosRenyiGraph(100, p);
				//timeK += Kruskal.kruskal(g).getTime();
				timeP += Prim.primOpti(g).getTime();
			}
			//System.out.println("Temps moyen d'éxécution pour l'algorithme de Kruskal avec n = 100 et p = "+p+" = " + (timeK/50)/Math.pow(10, 9) );
			System.out.println("Temps moyen d'éxécution pour l'algorithme de Prim avec n = 100 et p = "+p+" = " + (timeP/50)/Math.pow(10, 9) );

		}*/
		
		for(float p=(float) 0.1; p<=1.0; p+=0.2){
			long timeK = 0;
			for(int i=0; i<1; i++){
				GraphImpl g = (GraphImpl) RandomGraphGenerator.generateErdosRenyiGraph(1000, p);
				timeK += Prim.primOpti(g).getTime();
			}
			System.out.println("Temps moyen d'éxécution pour l'algorithme de Prim avec n = 1000 et p = "+p+" = " + (timeK/1)/Math.pow(10, 9) );
		}
		
		/*
		for(float p=(float) 0.1; p<=1.0; p+=0.2){
			long timeK = 0;
			for(int i=0; i<1; i++){
				GraphImpl g = (GraphImpl) RandomGraphGenerator.generateErdosRenyiGraph(5000, p);
				timeK += Kruskal.kruskal(g).getTime();
			}
			System.out.println("Temps moyen d'éxécution pour l'algorithme de Kruskal avec n = 5000 et p = "+p+" = " + (timeK/1)/Math.pow(10, 9) );
		}*/
	}

}
