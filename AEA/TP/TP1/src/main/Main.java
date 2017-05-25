package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dotplot.DotPlot;

import sequence.Sequence;
import recherche.Couple;
import recherche.KarpRabin;
import sequence.Mot;
/**
 * Classe Main contenant la methode principale du projet
 * @author antoine
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/////////////////////////////////////////////////////////////
		// Obtention et creation de la sequence d'ADN a considerer //
		/////////////////////////////////////////////////////////////
		
		Sequence sequence = new Sequence();
		String seq = sequence.createSequence(args[2]);
		
		// Passage en lettres majuscules pour eviter des soucis de case lors de comparaisons
		seq.toUpperCase();

		/////////////////////////////////////////////////////////////
		//  Obtention du mode que l'on considere pour notre etude  //
		/////////////////////////////////////////////////////////////
		
		String mode = args[1];
		
		/////////////////////////////////////////////////////////////
		//   Creation des outils de base : objets recherche, mot   //
		/////////////////////////////////////////////////////////////
		
		// Pour mode manuel ou recherche de sequence a partir d'un second fichier FASTA on cree les objets mot et seqCherche
		Mot mot;
		String seqCherche;
		
		// Objet representant une recherche a l'aide de Karp-Rabin sous forme naive ou optimisee. La forme optimisee l'est pour la recherche de tous les mots de taille N d'un texte.
		KarpRabin kr = new KarpRabin();

		// Objet representant le constructeur de notre fichier .dat pour obtenir les points permettant la construction de notre DotPlot.
		DotPlot d = new DotPlot();

		// Liste permettant de recueillir les positions des occurrences du mot cherche pour les modes manuel et sous forme de fichier
		List<Integer> listePos = new ArrayList<Integer>();
		// Map permettant de recueillir les positions des occurrences des mots recherches pour le mode entier qui calcule toutes les positions de tous les mots de taille N de la sequence
		Map<Double,Couple> map = new HashMap<Double, Couple>();

		////////////////////////////////////////////
		// Switch sur comment j'obtiens mon motif //
		////////////////////////////////////////////
		switch (args[0]) {
		
		/////////////////////////////////////////////////////////////////
		// "-man" mode manuel: on entre a la main une sequence cherche //
		/////////////////////////////////////////////////////////////////
		case "-man":
			seqCherche = args[3];
			mot = new Mot(seqCherche, mode);
			// Algo de Karp-Rabin
			listePos = kr.rechercheKarpRabinNaive(seq, mot, mode);
			// Creation du fichier pour le DotPlot
			d.createDotPlotFile(listePos, args[4], args[0]);
			break;
			
		//////////////////////////////////////////////////////////////////////////////
		// "-s" mode ou on entre la sequence a chercher sous forme de fichier fasta //
		//////////////////////////////////////////////////////////////////////////////
		case "-s":
			seqCherche = sequence.createSequence(args[3]);
			mot = new Mot(seqCherche, mode);
			// Algo de Karp-Rabin
			listePos = kr.rechercheKarpRabinNaive(seq, mot, mode);
			// Creation du fichier pour le DotPlot
			d.createDotPlotFile(listePos, args[4], args[0]);
			break;
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		// "-e" mode ou on entre un entier representant la taille des mots dont on souhaite obtenir les positions dans la sequence //
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
		case "-e":
			// Obtention de la taille N
			int n = Integer.parseInt(args[3]);
			// Algo Karp-Rabin optimise 
			map = kr.rechercheKarpRabin(seq, mode, n);
			// Creation du fichier pour le DotPlot
			d.createDotPlotFile(map, args[4]);
			break;
			
		default:
			throw new IllegalArgumentException("Argument du mode invalide, les modes possibles sont -man, -s et -e");
		}
	}

}
