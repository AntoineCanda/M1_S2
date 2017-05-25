package main;

import java.io.File;
//import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import dicos.Dicos;
import graphe.Graphe;

public class Main {

	public static void main(String[] args) {

		/*String[] dico3court = { "gag", "gai", "gaz", "gel", "gks", "gin", "gnu", "glu", "gui", "guy", "gre", "gue",
				"ace", "acm", "agi", "ait", "aie", "ail", "air", "and", "alu", "ami", "arc", "are", "art", "apr", "avr",
				"sur", "mat", "mur" };

		Graphe g3court = new Graphe(dico3court);
		g3court.lettreQuiSaute();
		g3court.display();
		g3court.visit();

		Graphe g4 = new Graphe(Dicos.dico4);
		g4.lettreQuiSaute();
		g4.display();
		g4.visit();*/
		
		try {
			Path path = new File(args[0]).toPath();
			String[] lesMots = Dicos.readFromFile(path);
			if(lesMots != null) {
				//Graphe g = new Graphe(lesMots);
				Graphe g = new Graphe(lesMots, 1, 1);
				g.lettreQuiSaute();
				g.display();
				g.visit2();
		
				/*if(args.length < 2) {
					System.out.println("Le mot lion est dans la composante connexe numéro : " + g4.getComposanteConnexe("lion"));
					System.out.println("Le mot peur est dans la composante connexe numéro : " + g4.getComposanteConnexe("peur"));
					System.out.println("Un chemin entre lion et peur est : " + g4.chemin("lion", "peur", false));
					System.out.println("Le chemin le plus court entre lion et peur est : " + g4.chemin("lion", "peur", true));
				} else {*/
			//	System.out.println("Le mot " + args[1] + " est dans la composante connexe numéro : " + g.getComposanteConnexe(args[1]));
			//	System.out.println("Le mot " + args[2] + " est dans la composante connexe numéro : " + g.getComposanteConnexe(args[2]));
				System.out.println("Un chemin entre " + args[1] + " et " + args[2] + " est : " + g.chemin(args[1], args[2], false));
				//g.afficherCompConnexe();
				if(!g.excentricite(args[1]))
					System.err.println("L'excentricité n'a pas fonctionné");
				System.out.println("Le chemin le plus court entre " + args[1] + " et " + args[2] + " est : " + g.chemin(args[1], args[2], true));
				//}
			//	g.afficherCpts();
			}
		} catch(InvalidPathException e) {
			e.printStackTrace();
		}
				
		
	}

}
