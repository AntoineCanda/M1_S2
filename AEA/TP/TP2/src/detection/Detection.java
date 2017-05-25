package detection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe implantant un algorithme de détection de détection de pré ARN. 
 * Attention l'implémentation n'est pas terminé et donc n'est pas fonctionnel en l'état
 * @author antoine
 *
 */
public class Detection {

	/**
	 * Sequence dans lequel on cherche un pre ARN
	 */
	private String sequence;
	
	/**
	 * Pointeur vers la position de départ de recherche du pre ARN
	 */
	private int debutRecherche;
	/**
	 * Pointeur vers la position de fin de recherche du pre ARN
	 */
	private int finRecherche;
	
	/**
	 * La table de programmation dynamique de recherche
	 */
	private CaseRecherche [][] tabRecherche;
	
	/**
	 * Le chemin pour trouver les (non-)appariements
	 */
	private Deque<CoordonneesRecherche> chemin;
	
	/**
	 * Le nombre de matchs total dans le pré-microARN en cours d'analyse
	 */
	private int nbMatchsTotal;
	
	public Detection(String sequence){
		this.sequence = sequence;
		/*
		 On considère que la taille maximale d'un pre ARN est 100 donc initialement on recherche de la position 0 à 99.
		 */
		int taille = this.sequence.length();
		this.debutRecherche = 0;

		if(taille >= 100){
			this.finRecherche = 99;
		}
		else{
			this.finRecherche = taille-1;
		}
		
	}
	
	public void parcourirSequence() {
		while(this.debutRecherche <= this.sequence.length() - 100) {
			rechercherPreARN();
			this.debutRecherche++;
		}
	}
	
	/**
	 * Classe interne permettant de définir une case de la recherche.
	 * @author Claire
	 *
	 */
	class CaseRecherche {
		char valeur; // Valeur de la case (soit U, soit M, soit X, soit I)
		int nbMatchsSucc; // Nombre de matchs successifs au moment d'arriver à cette case pour la 1ère fois
		// Les matchs successifs sont par rapport à la case courante si la case courante est marquée 'M',
		// sinon, si elle est marquée 'X', la valeur nbMatchsSucc correspond au nombre de matchs successifs de la
		// dernière succession de matchs trouvée
		CoordonneesRecherche position1ereCaseNonMatchSucc; // Position de la 1ère case "non-match" d'une succession de X courante
		// (si pas concerné, ce champ est mis à la valeur de CoordonneesRecherche par défaut)
		
		// Constructeur par défaut
		CaseRecherche() {
			valeur = 'U';
			nbMatchsSucc = 0;
			position1ereCaseNonMatchSucc = new CoordonneesRecherche();
		}
	}
	
	/**
	 * Classe permettant de se repérer dans le tableau de recherche (ligne, colonne)
	 * @author Claire
	 *
	 */
	class CoordonneesRecherche {
		int ligne;
		int colonne;
		
		// Constructeur par défaut
		CoordonneesRecherche() {
			this.ligne = -1;
			this.colonne = -1;
		}
		
		// Constructeur par valeur
		CoordonneesRecherche(int ligne, int colonne) {
			this.ligne = ligne;
			this.colonne = colonne;
		}
	}
	
	/**
	 * Méthode qui recherche une occurrence d'un pré-microARN dans une séquence
	 * selon le paradigme de la programmation dynamique
	 * @param debut la position de début de recherche dans la séquence
	 * @return la liste des positions dans le pré-microARN obtenu (si trouvé!)
	 * des nucléotides impliqués dans un appariement
	 */
	public PreMicroARN rechercherPreARN() {
		// On cherche une séquence de taille 100 maximum		
		// Couper la séquence à analyser en deux moitiés
		// On a donc un tableau 2D de 50 * 50
		tabRecherche = new CaseRecherche [50][50];
		
		// Initialiser tout le tableau à la valeur de CaseRecherche par défaut
		// (voir constructeur par défaut)
		for(int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				tabRecherche[i][j] = new CaseRecherche();
			}
		}
		
		// Initialiser un tableau "chemin" définissant le chemin parcouru pour trouver le pré-microARN
		chemin = new ArrayDeque<>();
		
		// Commencer à la case [0][0] (c'est-à-dire regarder la première lettre de la séquence et la dernière)
		int i = 0, j = 0;
		
		// Initialiser les variables de repérage
		nbMatchsTotal = 0;
		int nbMatchsSucc = 0;
		boolean casesNonMatchSucc = false; // Si on est dans une succession de cases non-matchs (true) ou pas (false)
		CoordonneesRecherche position1ereCaseNonMatchSucc = new CoordonneesRecherche();
		
		// Caractères aux indices courantes dans la séquence
		char iNucl, jNucl;
		
		// Booléen qui est à true si on rencontre une case pour la première fois
		boolean premiereFois;
		
		// Booléen qui est au départ à true et qui est mis à false dès qu'on a trouvé notre premier match
		boolean debut = true;
		
		// Booléen dont la valeur est mise à jour à chaque itération pour savoir si on fait un backtrack ou non
		boolean faireBackTrack;
		
		// Tant qu'on n'a pas fait trop de "backtrack" (c'est-à-dire tant qu'on n'a pas épuisé
		// tous les chemins possibles de recherche)
		// Nous sommes censés aussi nous arrêter dès qu'une boucle terminale est trouvée
		while(nbMatchsTotal < 24 && i >= 0 && j >= 0) {

			CoordonneesRecherche coordonneesCaseCourante = new CoordonneesRecherche(i, j);
			chemin.push(coordonneesCaseCourante);
			faireBackTrack = false;
			
			// Si on visite cette case pour la première fois
			if(tabRecherche[i][j].valeur == 'U') {	
				
				// Obtenir les caractères dans la séquence correspondant à ces indices de cases
				// du tableau 2D de recherche
				iNucl = this.sequence.charAt(this.debutRecherche + i);
				jNucl = this.sequence.charAt(this.debutRecherche + 99 - j);
				premiereFois = true;
				
			} else {
				// Peu importe les caractères qu'on affecte à iNucl et jNucl,
				// la condition suivante ne sera pas remplie à cause de premiereFois
				// mis à false
				iNucl = 'O';
				jNucl = 'O';
				premiereFois = false;
			}

			// S'il y a un match
			if(premiereFois == true && match(iNucl, jNucl)) {
				
				tabRecherche[i][j].valeur = 'M';
				
				if(casesNonMatchSucc) {
					casesNonMatchSucc = false;
					nbMatchsSucc = 0;
				}
				nbMatchsTotal++;
				nbMatchsSucc++;
				
				tabRecherche[i][j].nbMatchsSucc = nbMatchsSucc;
				
				// Si la case en diagonale n'est pas encore marquée
				// Il aurait fallu gérer le cas quand on arrive à la fin du tableau
				if(tabRecherche[i+1][j+1].valeur == 'U') {
					i++;
					j++;
				} else {
					faireBackTrack = true;
				}
				
				if(debut)
					debut = false;
				
			}	
			// S'il n'y a pas un match, que l'on soit ou non en train de visiter cette case
			// pour la première fois (car il est impossible de visiter une deuxième fois une
			// case 'V')
			else {
				
				// La valeur 'X', ici, doit être interprétée comme une case "non-match".
				// Elle peut être interprétée de 3 façons :
				// - un mismatch si après cette case, on va à la diagonale
				// - une insertion si après cette case, on va en bas
				// - une délétion si après cette case, on va à droite
				if(premiereFois)
					tabRecherche[i][j].valeur = 'X';
				else {
					// On a fait un backtrack pour revenir à cette case encore une fois.
					// On récupère les valeurs au moment d'arriver sur cette case pour
					// la première fois.
					position1ereCaseNonMatchSucc = tabRecherche[i][j].position1ereCaseNonMatchSucc;
					nbMatchsSucc = tabRecherche[i][j].nbMatchsSucc;
				}
				
				// Si on commence tout juste une succession de cases non-matchs,
				// on enregistre la position de la 1ère case non-match successive,
				// et on visualise un carré 3 x 3 dans le tableau 2D dont
				// le coin en haut à gauche est la position de cette 1ère
				// case non-match successive.
				if(!casesNonMatchSucc) {
					position1ereCaseNonMatchSucc = new CoordonneesRecherche(i, j);
					casesNonMatchSucc = true;
				}
				
				tabRecherche[i][j].position1ereCaseNonMatchSucc = position1ereCaseNonMatchSucc;
				
				// S'il y a eu au moins 3 matchs à la suite lors de la dernière série de matchs,
				// ou si on est au début de la recherche, c'est bon, on peut continuer.
				if(debut == true || nbMatchsSucc >= 3) {
					// On vérifie ici la succession des cases non-matchs dans le "carré 3 x 3"
					if(i < position1ereCaseNonMatchSucc.ligne + 3
							&& j < position1ereCaseNonMatchSucc.colonne + 3) {
						tabRecherche[i][j].nbMatchsSucc = nbMatchsSucc;
						i++;
						j++;
					}
					// Si on est en dehors de ce carré 3 x 3, alors il y a eu trop de
					// mismatchs/indels
					else {
						faireBackTrack = true;
					}
				}
				// Sinon, ce chemin ne constitue pas un pré-microARN valide.
				// Il faut revenir en arrière pour trouver un autre chemin possible.
				else {
					faireBackTrack = true;
				}
				
			}
			
			if(faireBackTrack) {
				CoordonneesRecherche cc = backtrack(coordonneesCaseCourante);
				CaseRecherche caseArret = tabRecherche[cc.ligne][cc.colonne];
				
				nbMatchsSucc = caseArret.nbMatchsSucc;
				position1ereCaseNonMatchSucc = caseArret.position1ereCaseNonMatchSucc;
				
				// Si la case en bas n'est pas déjà invalide
				if(tabRecherche[cc.ligne+1][cc.colonne].valeur != 'I') {
					cc.ligne += 1;
				}
				// Si la case à droite n'est pas déjà invalide
				else if(tabRecherche[cc.ligne][cc.colonne+1].valeur != 'I') {
					cc.colonne += 1;
				}
				
				i = cc.ligne;
				j = cc.colonne;
			}
			
		}
		
		// Si on est sortis de la boucle avec les indices i ou j inférieurs
		// à zéro, alors on peut en conclure qu'il n'y a pas de pré-microARN
		// dans cette fenêtre de recherche.
		if(i < 0 || j < 0) {
			return null;
		}
		
		// Si on est arrivé ici, c'est qu'on a trouvé un pré-microARN.
		// Il faut maintenant reconstituer son structure d'appariements
		// d'après les tableaux chemin et tabRecherche.
		StringBuilder structure = new StringBuilder();
		int indiceAvant = 0, indiceArriere = 1;
		
		while(!chemin.isEmpty()) {
			CoordonneesRecherche coord = chemin.removeLast();
			if(tabRecherche[coord.ligne][coord.colonne].valeur == 'V') {
				structure.insert(indiceAvant, '(');
				structure.insert(indiceArriere, ')');
			} else { // valeur == 'X'
				CoordonneesRecherche coordSuivant = chemin.peekLast();
				// Si la prochaine case est en diagonale (mismatch)
				if(coordSuivant.ligne == coord.ligne + 1 && coordSuivant.colonne == coord.colonne + 1) {
					structure.insert(indiceAvant, '.');
					structure.insert(indiceArriere, '.');
				}
				// Si la prochaine case est en bas (insertion)
				else if(coordSuivant.ligne == coord.ligne + 1 && coordSuivant.colonne == coord.colonne) {
					structure.insert(indiceAvant, '.');
				}
				// Si la prochaine case est à droite (délétion)
				else {
					structure.insert(indiceArriere, '.');
				}
			}
			indiceAvant++;
			indiceArriere++;
		}
		
		// positionDebut et positionFin à changer
		PreMicroARN preMicroARN = new PreMicroARN(this.debutRecherche, this.debutRecherche+48, structure.toString());
		
		return preMicroARN;
	}
	
	/**
	 * Fonction de backtrack, permettant de revenir en arrière dans les cases déjà parcourues
	 * (grâce au tableau chemin), afin de marquer "invalide" les cases traversées.
	 * @return coordArret les coordonnées de la case où on a arrêté le backtrack
	 */
	CoordonneesRecherche backtrack(CoordonneesRecherche coordCC) {
		CaseRecherche caseCourante = tabRecherche[coordCC.ligne][coordCC.colonne];
		
		// Retourner en arrière (en excluant la case de départ) jusqu'à non seulement
		// tomber sur une case 'X', mais aussi où les "voisins" de cette case en diagonale
		// en arrière, à gauche et en haut ne sont pas marqués 'I' (invalide).
		// De plus, si à un moment donné la ligne ou la colonne de la case courante
		// est de -1, alors c'est en dehors du tableau, et dans ce cas on arrête
		// la recherche de pré-microARN dans cette fenêtre de 100 nucléotides.
		
		do {
			chemin.pop();
			coordCC = chemin.peekFirst();
			caseCourante = tabRecherche[coordCC.ligne][coordCC.colonne];
			if(caseCourante.valeur == 'M') {
				nbMatchsTotal--;
				caseCourante.valeur = 'I';
			} else if(caseCourante.valeur == 'X' && tabRecherche[coordCC.ligne-1][coordCC.colonne-1].valeur == 'I'
					&& tabRecherche[coordCC.ligne][coordCC.colonne-1].valeur == 'I'
					&& tabRecherche[coordCC.ligne-1][coordCC.colonne].valeur == 'I') {
				caseCourante.valeur = 'I';
			}
		} while(coordCC.ligne != -1 && coordCC.colonne != -1
				&& caseCourante.valeur == 'I');
		
		return coordCC;
	}
	
	/**
	 * Methode qui retourne vrai si les nucleotides sont complementaire ou faux sinon
	 * Attention ce code ne marche que pour le cas ou on a de l'ARN : il faudrait creer une version pour ADN en remplacant 'U' par 'T'
	 * @param char nucleotide1 le nucleotide considere dans la sequence initiale
	 * @param char nucleotide2 le nucleotide considere dans la sequence reverse
	 * @return boolean true si les nucleotides 1 et 2 sont complementaires faux sinon
	 */
	public boolean match(char nucleotide1, char nucleotide2){
		
		switch(nucleotide1){
		case 'A':
			if(nucleotide2 == 'U')
				return true;
			break;
		case 'U':
			if(nucleotide2 == 'A' || nucleotide2 == 'G')
				return true;
			break;
		case 'C':
			if(nucleotide2 == 'G')
				return true;
			break;
		case 'G':
			if(nucleotide2 == 'U' || nucleotide2 == 'C')
				return true;
			break;
		default:
			throw new IllegalArgumentException("Le caractere n'est pas present.");	
		}
		return false;
	}
	
}
