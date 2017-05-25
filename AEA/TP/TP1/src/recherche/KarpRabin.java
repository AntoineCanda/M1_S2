package recherche;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sequence.Mot;

/**
 * Classe KarpRabin implementant :
 * - une version de l'algo "naive" qui est à utiliser pour une recherche d'un seul mot
 * - une version optimisee de l'algo dans le cas de la recherche des occurrences de tous les mots d'une sequence de taille N
 * @author antoine
 *
 */
public class KarpRabin {

	/**
	 * Fonction qui implemente la recherche des occurrences de tous les mots de taille tailleMot
	 * @param sequence la sequence dans laquelle on recherche les occurrences
	 * @param mode le mode que l'on utilise pour determiner quelles formes du mot on considere (mot uniquement ou reverse, complementaire...)
	 * @param tailleMot la taille des mots que l'on considere
	 * @return une Map avec en Key la valeur du hash calcule par Karp-Rabin et en valeur le Couple (Mot, liste des positions)
	 */
	public Map<Double, Couple> rechercheKarpRabin(String sequence, String mode, int tailleMot){
		double hachage, keyPresente;
		Couple cplDepart, cpl;
		Mot motDepart, mot;
		boolean hachageEgal, test;
		
		// Creation de la map contenant les resultats
		Map<Double,Couple> map = new HashMap<Double,Couple>(sequence.length()-tailleMot+1);

		// Initialisation de l'algorithme avec le premier mot de la sequence permettant de calculer la premiere version du hash que l'on mettra simplement a jour ensuite
		motDepart = new Mot(sequence.substring(0,tailleMot), mode);
		hachage = this.hachage(motDepart.getSequenceInit());
		// Creation du couple et ajout de la position 0 car on n'a encore aucun mot dans la map et donc aucun doublon possible
		cplDepart = new Couple(motDepart);
		cplDepart.addPosition(0);
		// Ajout dans la map
		map.put(hachage, cplDepart);

		// Boucle qui va parcourir toute la sequence avec un deplacement de 1 caractere a chaque fois pour visiter tous les mots existant dans la sequence de taille N souhaitee
		for(int i=1; i < sequence.length() - tailleMot +1; i++){
			
			// Creation du mot considere a la position actuelle
			String seq = sequence.substring(i, i+tailleMot);
			mot = new Mot(seq,mode);
			
			// Obtention des autres formes du mot
			List<String> otherSequences = mot.getSequences();
			
			// keyPresente va permettre d'obtenir la cle qui a ete retrouvee dans la map pour obtenir le mot present dans la map de cette derniere 
			keyPresente = 0;
			
			// Mise a jour de la valeur de hachage qui revient a retirer la valeur de la lettre qui precede la premiere lettre du mot et a ajouter la valeur de la derniere lettre du mot considere
			hachage = (4 * (hachage - codeLettre(sequence.charAt(i-1)) * Math.pow(4, tailleMot-1)) + codeLettre(sequence.charAt(i+tailleMot-1))) % Integer.MAX_VALUE;
			
			// Booleen qui permet de detecter une presence eventuelle dans la map du mot considere selon les formes que l'on considere
			hachageEgal = false;
			
			int j = 0;
			
			// Boucle ou on teste si la valeur du hash de chaque forme existe dans la map ou pas avec mise a jour des variables si elle existe
			while(j < otherSequences.size() && !hachageEgal) {
				double keyTestee = this.hachage(otherSequences.get(j));
				if(map.containsKey(keyTestee)){
					keyPresente = keyTestee; 
					hachageEgal = true;
				}
				j++;
			}
			
			// Si le mot est present dans une certaine forme dans la map, on ajoute la position du doublon a la liste correspondante
			if(hachageEgal){ 
				Mot motComp = map.get(keyPresente).getMot();
	
				test = mot.egalite(motComp);
				if(test == true){
					map.get(keyPresente).addPosition(i);
				}
			}
			// Dans le cas d'un mot non present, on cree un couple qui lui est associe et on ajoute ce couple a la map 
			else{
				cpl = new Couple(mot);
				cpl.addPosition(i);
				map.put(hachage, cpl);
			}
		}
		return map;
	}
	
	/**
	 * Recherche toutes les occurrences d'un mot dans la sequence donnee
	 * @param sequence La sequence dans laquelle chercher le mot
	 * @param mot Le mot a rechercher
	 * @return La liste des positions de ce mot (une position est l'indice
	 * de la premiere lettre de l'occurrence trouvee sachant qu'on commence
	 * par l'indice 0)
	 */
	public List<Integer> rechercheKarpRabinNaive(String sequence, Mot mot, String mode) {
		double[] hachagesMots = new double[4];
		List<String> seq = mot.getSequences();
		
		// Calcul des hachages des sequences du mot a rechercher
		hachagesMots[0] = hachage(seq.get(0));
		if(seq.size() > 1)
			hachagesMots[1] = hachage(seq.get(1));
		else
			hachagesMots[1] = Double.MIN_VALUE;
		if(seq.size() > 2)
			hachagesMots[2] = hachage(seq.get(2));
		else
			hachagesMots[2] = Double.MIN_VALUE;
		if(seq.size() > 3)
			hachagesMots[3] = hachage(seq.get(3));
		else
			hachagesMots[3] = Double.MIN_VALUE;
		
		// Taille du mot passe en parametre
		int tailleMot = seq.get(0).length();
		
		// Calcul du hachage de la premiere suite de lettres
		// de taille mot.length() dans la sequence
		double hachageMotSeq = hachage(sequence.substring(0, tailleMot));
		
		// Booleen permettant de sortir de la boucle quand on a parcouru
		// toute la sequence
		boolean fini = false;
		
		// Booleen permettant de determiner si le hachage de la portion actuelle
		// de la sequence ADN est egal a l'un des hachages des sequences du mot.
		boolean hachageEgal;
		
		// Indice permettant de se reperer dans la sequence. C'est la position
		// de la premiere lettre de la sous-sequence a etudier.
		int i = 0;
		
		// Indice de parcours du tableau des hachages des sequences du mot.
		int j;
		
		// Liste de toutes les positions des sous-sequences trouvees par rapport
		// au mot
		List<Integer> listePos = new ArrayList<>();
		
		// Parcours de toute la sequence pour chercher les occurrences
		while(!fini){
			hachageEgal = false;
			j = 0;
			// On verifie si une des formes possibles de la sequence correspond (reverse, complementaire...)
			while(j < 4 && !hachageEgal) {
				if(hachageMotSeq == hachagesMots[j])
					hachageEgal = true;
				j++;
			}
			// Ajout de la position de l'occurrence en cas d'egalite detectee
			if(hachageEgal && mot.egalite(new Mot(sequence.substring(i, i+tailleMot),mode))) {
				listePos.add(i);
			}
			// Indicateur de fin de parcours
			if(i == sequence.length() - (tailleMot+1))
				fini = true;
			else {
				// Mise a jour du hash en soustrayant la valeur de la premiere lettre qui n'est plus presente et en ajoutant celle de la nouvelle lettre qui la remplace
				hachageMotSeq =
				(4 * (hachageMotSeq - codeLettre(sequence.charAt(i)) * Math.pow(4, tailleMot-1))
				+ codeLettre(sequence.charAt(i+tailleMot))) % Integer.MAX_VALUE;
				i++;
			}
		}
		
		return listePos;	
	}
	
	/**
	 * Recupere le code correspondant a une lettre de l'alphabet (A, C, G, U)
	 * On considere ici des nombres impairs et premiers pour chaque nucleotide dans l'idee d'essayer d'eviter que
	 * deux nucleotides mis a une certaine puissance et ajoutes deviennent egaux
	 * @param lettre La lettre a encoder
	 * @return Le code correspondant
	 */
	private int codeLettre(char lettre) {
		switch(lettre) {
		case 'A' :
			return 1;
		case 'C' :
			return 5;
		case 'G' :
			return 7;
		case 'U' :
			return 11;
		default :
			throw new IllegalArgumentException("Caractere invalide : "
					+ "doit faire partie de l'alphabet (A, C, G, U).");
		}
	}
	
	/**
	 * Effectue le hachage d'un mot (calcul)
	 * @param mot Le mot a considerer
	 * @return Le hash de ce mot
	 */
	private double hachage(String mot) {
		double code = 0;
		int tailleMot = mot.length(), i = tailleMot-1;
		char[] motChar = mot.toCharArray();
		for(char lettre : motChar) {
			// Ici, codeLettre(lettre) correspond a u indice i, 4 correspond a d
			// et i correspond a la puissance de d (voir cours)
			code += codeLettre(lettre) * Math.pow(4, i);
			i--;
		}
		return code % Integer.MAX_VALUE;
	}
}
