package detection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe implantant un algorithme de d�tection de d�tection de pr� ARN. 
 * Attention l'impl�mentation n'est pas termin� et donc n'est pas fonctionnel en l'�tat
 * @author antoine
 *
 */
public class Detection {

	/**
	 * Sequence dans lequel on cherche un pre ARN
	 */
	private String sequence;
	
	/**
	 * Pointeur vers la position de d�part de recherche du pre ARN
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
	 * Le nombre de matchs total dans le pr�-microARN en cours d'analyse
	 */
	private int nbMatchsTotal;
	
	public Detection(String sequence){
		this.sequence = sequence;
		/*
		 On consid�re que la taille maximale d'un pre ARN est 100 donc initialement on recherche de la position 0 � 99.
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
	 * Classe interne permettant de d�finir une case de la recherche.
	 * @author Claire
	 *
	 */
	class CaseRecherche {
		char valeur; // Valeur de la case (soit U, soit M, soit X, soit I)
		int nbMatchsSucc; // Nombre de matchs successifs au moment d'arriver � cette case pour la 1�re fois
		// Les matchs successifs sont par rapport � la case courante si la case courante est marqu�e 'M',
		// sinon, si elle est marqu�e 'X', la valeur nbMatchsSucc correspond au nombre de matchs successifs de la
		// derni�re succession de matchs trouv�e
		CoordonneesRecherche position1ereCaseNonMatchSucc; // Position de la 1�re case "non-match" d'une succession de X courante
		// (si pas concern�, ce champ est mis � la valeur de CoordonneesRecherche par d�faut)
		
		// Constructeur par d�faut
		CaseRecherche() {
			valeur = 'U';
			nbMatchsSucc = 0;
			position1ereCaseNonMatchSucc = new CoordonneesRecherche();
		}
	}
	
	/**
	 * Classe permettant de se rep�rer dans le tableau de recherche (ligne, colonne)
	 * @author Claire
	 *
	 */
	class CoordonneesRecherche {
		int ligne;
		int colonne;
		
		// Constructeur par d�faut
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
	 * M�thode qui recherche une occurrence d'un pr�-microARN dans une s�quence
	 * selon le paradigme de la programmation dynamique
	 * @param debut la position de d�but de recherche dans la s�quence
	 * @return la liste des positions dans le pr�-microARN obtenu (si trouv�!)
	 * des nucl�otides impliqu�s dans un appariement
	 */
	public PreMicroARN rechercherPreARN() {
		// On cherche une s�quence de taille 100 maximum		
		// Couper la s�quence � analyser en deux moiti�s
		// On a donc un tableau 2D de 50 * 50
		tabRecherche = new CaseRecherche [50][50];
		
		// Initialiser tout le tableau � la valeur de CaseRecherche par d�faut
		// (voir constructeur par d�faut)
		for(int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				tabRecherche[i][j] = new CaseRecherche();
			}
		}
		
		// Initialiser un tableau "chemin" d�finissant le chemin parcouru pour trouver le pr�-microARN
		chemin = new ArrayDeque<>();
		
		// Commencer � la case [0][0] (c'est-�-dire regarder la premi�re lettre de la s�quence et la derni�re)
		int i = 0, j = 0;
		
		// Initialiser les variables de rep�rage
		nbMatchsTotal = 0;
		int nbMatchsSucc = 0;
		boolean casesNonMatchSucc = false; // Si on est dans une succession de cases non-matchs (true) ou pas (false)
		CoordonneesRecherche position1ereCaseNonMatchSucc = new CoordonneesRecherche();
		
		// Caract�res aux indices courantes dans la s�quence
		char iNucl, jNucl;
		
		// Bool�en qui est � true si on rencontre une case pour la premi�re fois
		boolean premiereFois;
		
		// Bool�en qui est au d�part � true et qui est mis � false d�s qu'on a trouv� notre premier match
		boolean debut = true;
		
		// Bool�en dont la valeur est mise � jour � chaque it�ration pour savoir si on fait un backtrack ou non
		boolean faireBackTrack;
		
		// Tant qu'on n'a pas fait trop de "backtrack" (c'est-�-dire tant qu'on n'a pas �puis�
		// tous les chemins possibles de recherche)
		// Nous sommes cens�s aussi nous arr�ter d�s qu'une boucle terminale est trouv�e
		while(nbMatchsTotal < 24 && i >= 0 && j >= 0) {

			CoordonneesRecherche coordonneesCaseCourante = new CoordonneesRecherche(i, j);
			chemin.push(coordonneesCaseCourante);
			faireBackTrack = false;
			
			// Si on visite cette case pour la premi�re fois
			if(tabRecherche[i][j].valeur == 'U') {	
				
				// Obtenir les caract�res dans la s�quence correspondant � ces indices de cases
				// du tableau 2D de recherche
				iNucl = this.sequence.charAt(this.debutRecherche + i);
				jNucl = this.sequence.charAt(this.debutRecherche + 99 - j);
				premiereFois = true;
				
			} else {
				// Peu importe les caract�res qu'on affecte � iNucl et jNucl,
				// la condition suivante ne sera pas remplie � cause de premiereFois
				// mis � false
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
				
				// Si la case en diagonale n'est pas encore marqu�e
				// Il aurait fallu g�rer le cas quand on arrive � la fin du tableau
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
			// pour la premi�re fois (car il est impossible de visiter une deuxi�me fois une
			// case 'V')
			else {
				
				// La valeur 'X', ici, doit �tre interpr�t�e comme une case "non-match".
				// Elle peut �tre interpr�t�e de 3 fa�ons :
				// - un mismatch si apr�s cette case, on va � la diagonale
				// - une insertion si apr�s cette case, on va en bas
				// - une d�l�tion si apr�s cette case, on va � droite
				if(premiereFois)
					tabRecherche[i][j].valeur = 'X';
				else {
					// On a fait un backtrack pour revenir � cette case encore une fois.
					// On r�cup�re les valeurs au moment d'arriver sur cette case pour
					// la premi�re fois.
					position1ereCaseNonMatchSucc = tabRecherche[i][j].position1ereCaseNonMatchSucc;
					nbMatchsSucc = tabRecherche[i][j].nbMatchsSucc;
				}
				
				// Si on commence tout juste une succession de cases non-matchs,
				// on enregistre la position de la 1�re case non-match successive,
				// et on visualise un carr� 3 x 3 dans le tableau 2D dont
				// le coin en haut � gauche est la position de cette 1�re
				// case non-match successive.
				if(!casesNonMatchSucc) {
					position1ereCaseNonMatchSucc = new CoordonneesRecherche(i, j);
					casesNonMatchSucc = true;
				}
				
				tabRecherche[i][j].position1ereCaseNonMatchSucc = position1ereCaseNonMatchSucc;
				
				// S'il y a eu au moins 3 matchs � la suite lors de la derni�re s�rie de matchs,
				// ou si on est au d�but de la recherche, c'est bon, on peut continuer.
				if(debut == true || nbMatchsSucc >= 3) {
					// On v�rifie ici la succession des cases non-matchs dans le "carr� 3 x 3"
					if(i < position1ereCaseNonMatchSucc.ligne + 3
							&& j < position1ereCaseNonMatchSucc.colonne + 3) {
						tabRecherche[i][j].nbMatchsSucc = nbMatchsSucc;
						i++;
						j++;
					}
					// Si on est en dehors de ce carr� 3 x 3, alors il y a eu trop de
					// mismatchs/indels
					else {
						faireBackTrack = true;
					}
				}
				// Sinon, ce chemin ne constitue pas un pr�-microARN valide.
				// Il faut revenir en arri�re pour trouver un autre chemin possible.
				else {
					faireBackTrack = true;
				}
				
			}
			
			if(faireBackTrack) {
				CoordonneesRecherche cc = backtrack(coordonneesCaseCourante);
				CaseRecherche caseArret = tabRecherche[cc.ligne][cc.colonne];
				
				nbMatchsSucc = caseArret.nbMatchsSucc;
				position1ereCaseNonMatchSucc = caseArret.position1ereCaseNonMatchSucc;
				
				// Si la case en bas n'est pas d�j� invalide
				if(tabRecherche[cc.ligne+1][cc.colonne].valeur != 'I') {
					cc.ligne += 1;
				}
				// Si la case � droite n'est pas d�j� invalide
				else if(tabRecherche[cc.ligne][cc.colonne+1].valeur != 'I') {
					cc.colonne += 1;
				}
				
				i = cc.ligne;
				j = cc.colonne;
			}
			
		}
		
		// Si on est sortis de la boucle avec les indices i ou j inf�rieurs
		// � z�ro, alors on peut en conclure qu'il n'y a pas de pr�-microARN
		// dans cette fen�tre de recherche.
		if(i < 0 || j < 0) {
			return null;
		}
		
		// Si on est arriv� ici, c'est qu'on a trouv� un pr�-microARN.
		// Il faut maintenant reconstituer son structure d'appariements
		// d'apr�s les tableaux chemin et tabRecherche.
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
				// Si la prochaine case est � droite (d�l�tion)
				else {
					structure.insert(indiceArriere, '.');
				}
			}
			indiceAvant++;
			indiceArriere++;
		}
		
		// positionDebut et positionFin � changer
		PreMicroARN preMicroARN = new PreMicroARN(this.debutRecherche, this.debutRecherche+48, structure.toString());
		
		return preMicroARN;
	}
	
	/**
	 * Fonction de backtrack, permettant de revenir en arri�re dans les cases d�j� parcourues
	 * (gr�ce au tableau chemin), afin de marquer "invalide" les cases travers�es.
	 * @return coordArret les coordonn�es de la case o� on a arr�t� le backtrack
	 */
	CoordonneesRecherche backtrack(CoordonneesRecherche coordCC) {
		CaseRecherche caseCourante = tabRecherche[coordCC.ligne][coordCC.colonne];
		
		// Retourner en arri�re (en excluant la case de d�part) jusqu'� non seulement
		// tomber sur une case 'X', mais aussi o� les "voisins" de cette case en diagonale
		// en arri�re, � gauche et en haut ne sont pas marqu�s 'I' (invalide).
		// De plus, si � un moment donn� la ligne ou la colonne de la case courante
		// est de -1, alors c'est en dehors du tableau, et dans ce cas on arr�te
		// la recherche de pr�-microARN dans cette fen�tre de 100 nucl�otides.
		
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
