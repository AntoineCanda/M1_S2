package recherche;

import java.util.ArrayList;
import java.util.List;

import sequence.Mot;
import utilMots.Util;

/**
 * /!\ 
 * Code non fonctionnel contenant des erreurs
 * Utiliser de preference Karp-Rabin  
 * La documentation est limitee au strict minimum. On a essaye de reprendre le cours sans succes.
 * Le code a ete laisse a titre indicatif mais ne devrait pas etre considere car clairement non finalise. 
 * @author antoine
 *
 */
public class KMP {


	/**
	 * Premiere version du pretraitement (Antoine) sur le mot pour determiner le nombre de caracteres a sauter en cas d'erreur detectee
	 * @param mot motif a rechercher
	 * @return Une liste contenant les tableaux d'entiers representant le nombre de caracteres a sauter en fonction d'une detection d'erreur au caractere correspondant selon la forme du mot 
	 */
	private List<int[]> pretraitement(Mot mot) {
		List<int[]> liste = new ArrayList<int[]>();
		List<String> listeMots = mot.getSequences();
		String mode = mot.getMode();

		// On effectue ce pretraitement pour les differentes formes du motif que l'on a 
		for (String chaine : listeMots) {
			// On cree un tableau de la taille du mot + 1 case pour le cas ou on reconnait le motif
			int res[] = new int[mot.length() + 1];

			res[0] = -1;

			// Pour chaque caractere on determine le nombre de caracteres pouvant etre sautes en cas d'erreur detectee a cette position 
			for (int i = 0; i < mot.length() + 1; i++) {
				int maximum = -1;

				Mot m = new Mot(chaine.substring(0, i), mode);

				// (1) Nous nous sommes rendus compte qu'il fallait en realite mettre la condition j < i
				// car un bord dans un mot peut se chevaucher avec lui-meme (par exemple, le bord "aba" dans le mot "ababa")
				for (int j = 0; j < (i / 2); j++) {
					String suite = chaine.substring(0, j);
					int valeur = -1;

					if (suite.isEmpty() || m.estBord(suite)) {
						if (i < mot.length()) {
							char nucleotide = chaine.charAt(i);

							if (!m.prefixe(suite + nucleotide)) {
								valeur = suite.length();
							}
						} else {
							valeur = suite.length();
						}
					}

					if (valeur > maximum) {
						maximum = valeur;
					}
				}

				res[i] = maximum;
			}
			liste.add(res);
		}
		return liste;
	}
	
	/**
	 * Seconde version du pretraitement (Claire) qui prend en compte le fait qu'une sequence et sa complementaire obtiendront
	 * toujours le meme tableau d'indices de decalage. Au lieu de 4 calculs maximum a effectuer sur le mot, on n'en a plus que
	 * 2 maximum. Le calcul se fait par une autre fonction (calculDecalages). Selon le mode, la liste des tableaux d'indices de
	 * decalage varie en taille et en contenu.
	 * @param mot Le mot a considerer
	 * @return liste des tableaux d'entiers representant le nombre de caracteres pouvant etre sautes en cas de detection d'erreur
	 */
	private List<int[]> pretraitement_v2(Mot mot) {
		List<int[]> liste = new ArrayList<int[]>();
		List<String> listeSeq = mot.getSequences();
		String mode = mot.getMode();
		int[] res1, res2 = new int[mot.length() + 1];;
		
		res1 = calculDecalages(listeSeq.get(0));
		// Ajout du 1er tableau de Next(i)
		liste.add(res1);
		
		if(!mode.equals("-0") && !mode.equals("-1")) {
			if(mode.equals("-6"))
				res2 = calculDecalages(listeSeq.get(2));
			else
				res2 = calculDecalages(listeSeq.get(1));
		}

		if(!mode.equals("-0")) {
			
			// Ajout du 2eme tableau de Next(i)
			if(mode.equals("-1") || mode.equals("-6"))
				liste.add(res1);
			else
				liste.add(res2);
			
			// Ajout du 3eme tableau de Next(i)
			if(mode.equals("-4") || mode.equals("-7"))
				liste.add(res1);
			else if(mode.equals("-5") || mode.equals("-6"))
				liste.add(res2);
			
			// Ajout du 4eme tableau de Next(i)
			if(mode.equals("-7"))
				liste.add(res2);
			
		}
			
		return liste;
	}
	
	/**
	 * Fonction utilitaire calculant le decalage necessaire pour la fonction pretraitement_v2
	 * @param sequence La sequence pour laquelle on veut calculer les decalages
	 * @return le tableau des decalages du mot considere
	 */
	private int[] calculDecalages(String sequence) {	
		int res[] = new int[sequence.length() + 1];

		res[0] = -1;

		for (int i = 1; i < sequence.length() + 1; i++) {
			int maximum = -1;
			int j = i / 2; // Meme remarque que (1) dans la fonction pretraitement (1ere version)
			boolean maximumTrouve = false;

			String subStr = sequence.substring(0, i);

			while (!maximumTrouve && j > -1) {
				String suite = sequence.substring(0, j);
				int valeur = -1;

				if (suite.isEmpty() || Util.estBord(subStr, suite)) {
					if (i < sequence.length()) {
						char nucleotide = sequence.charAt(i);

						if (!subStr.startsWith(suite + nucleotide)) {
							valeur = suite.length();
						}
					} else {
						valeur = suite.length();
					}
				}

				if (valeur > maximum) {
					maximum = valeur;
					maximumTrouve = true;
				}
				j--;
			}

			res[i] = maximum;
		}
		
		return res;	
	}
	
	/**
	 * Fonction qui indique si l'algo doit se terminer a cette position
	 * @param positionSequence La liste des positions dans la sequence a un moment donne pour toutes les formes du mot a rechercher
	 * @param posMax La position a laquelle l'algo doit se terminer
	 * @return un booleen, true si l'algorithme doit se terminer, false sinon
	 */
	private boolean finAlgo(List<Integer> positionSequence, int posMax){
		for(int i : positionSequence){
			if(i <= posMax){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Algorithme de recherche base sur KMP
	 * Attention : non fonctionnel, utilisez plutot Karp-Rabin
	 * @param sequence dans laquelle on recherche les occurrences
	 * @param mot le motif a rechercher dans la sequence
	 * @return la liste des entiers des positions des occurrences
	 */
	public List<Integer> rechKMP(String sequence, Mot mot){
		List<int[]> listePretraitement = this.pretraitement(mot);
		List<String> listeMotifs = mot.getSequences();
		List<Integer> listePosDoublons = new ArrayList<Integer>();
		List<Integer> positionSequences = new ArrayList<Integer>();
		
		for(int i = 0; i < listePretraitement.size(); i++){
			positionSequences.add(0);
		}
		
		while(!finAlgo(positionSequences, sequence.length() - mot.length())){
			for(int i = 0; i < listePretraitement.size(); i++){

				int[] next = listePretraitement.get(i);
				int pos = positionSequences.get(i);
				String motif = listeMotifs.get(i);
				if(pos <= sequence.length() - mot.length()){
					String suite = sequence.substring(pos, pos + motif.length());
					
					int positionNucleotideDifferent = 0;
					boolean test = true;
					while(positionNucleotideDifferent < mot.length() && test){
						if(suite.charAt(positionNucleotideDifferent) != motif.charAt(positionNucleotideDifferent)){
							test = false;
						}
						else {
							positionNucleotideDifferent++;
						}
					}
					
					if(positionNucleotideDifferent == mot.length()){
						listePosDoublons.add(pos);
						System.out.println("Doublon pos: "+pos);
					}
					
					positionSequences.set(i, pos+(positionNucleotideDifferent - next[positionNucleotideDifferent]));

				}
			}
		}
		return listePosDoublons;
	}
}
