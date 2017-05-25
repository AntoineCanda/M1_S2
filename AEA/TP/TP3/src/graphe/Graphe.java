package graphe;

import java.util.ArrayList;
import java.util.Iterator;

public class Graphe {
	private String[] mots; // L'ensemble des mots de départ
	//private Liste[] listeSucc; // La liste des successeurs de chaque mot
	private ArrayList<ArrayList<Integer>> listeSucc;
	private boolean[] dejaVu; // Le tableau des booléens indiquant si le mot d'indice i a déjà été visité ou non
	//private String[][] composanteConnexe; // Le tableau des composantes connexes.
	private int[] pere; // Le tableau des peres
	private int sup; // Le nombre maximum de lettres qu'on peut supprimer pour créer un arc entre un 1er mot et un 2ème mot (peut être 0 pour n'avoir que des mots de même taille)
	private int dif; // Le nombre maximum de lettres qu'on peut changer pour créer un arc entre un 1er mot et un 2ème mot (peut être 0 pour n'effectuer que des suppressions de lettres)
	// Remarque : on ne peut pas avoir sup ET dif à 0 en même temps
	private ArrayList<ArrayList<Integer>> compConnexe;
	private int cpt = 0;
	private ArrayList<Integer> cpts;
	/**
	 * Constructeur d'un Graphe avec une liste de mots définie
	 * @param lesMots Les mots à considérer
	 */
	public Graphe(String[] lesMots) {
		this.mots = lesMots;
		//this.listeSucc = new Liste[lesMots.length];
		this.listeSucc = new ArrayList<ArrayList<Integer>>(lesMots.length);
		this.dejaVu = new boolean[lesMots.length];
		//this.composanteConnexe = new String[lesMots.length][];
		this.pere = new int[lesMots.length];
		// Paramètres par défaut pour sup et dif (version d'avant l'exercice 5)
		this.sup = 0;
		this.dif = 1;
		this.compConnexe = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < this.mots.length; i++) {
			//this.listeSucc[i] = new Liste(lesMots.length - 1);
			this.listeSucc.add(new ArrayList<Integer>());
			//this.compConnexe.add(new ArrayList<Integer>());
			this.dejaVu[i] = false;
	//		this.composanteConnexe[i] = new String[]{""};
		}
	}
	
	public Graphe(String[] lesMots, int sup, int dif) {
		this.mots = lesMots;
		//this.listeSucc = new Liste[lesMots.length];
		this.listeSucc = new ArrayList<ArrayList<Integer>>(lesMots.length);
		this.dejaVu = new boolean[lesMots.length];
	//	this.composanteConnexe = new String[lesMots.length][];
		this.pere = new int[lesMots.length];
		// Paramètres par défaut pour sup et dif (version d'avant l'exercice 5)
		this.sup = sup;
		this.dif = dif;
		this.compConnexe = new ArrayList<ArrayList<Integer>>();
		this.cpts = new ArrayList<Integer>();
		
		for (int i = 0; i < this.mots.length; i++) {
			//this.listeSucc[i] = new Liste(lesMots.length - 1);
			this.listeSucc.add(new ArrayList<Integer>());
			this.compConnexe.add(new ArrayList<Integer>());
			this.dejaVu[i] = false;
	//		this.composanteConnexe[i] = new String[]{""};
		}
	}

	/**
	 * Ajoute une arête dans le graphe (deux mots avec une lettre différente)
	 * @param s indice du premier mot
	 * @param d indice du deuxième mot
	 */
	public void ajouterArete(int indiceMot1, int indiceMot2) {
		this.ajouterArc(indiceMot1, indiceMot2);
		this.ajouterArc(indiceMot2, indiceMot1);
	}
	
	public void ajouterArc(int s, int d) {
		//this.listeSucc[s].ajouterSuccesseur(d);
		this.listeSucc.get(s).add(d);
	}

	/**
	 * Initialise les listes des successeurs des mots du dictionnaire considere
	 */
	public void lettreQuiSaute() {
		for(int i = 0; i < this.mots.length; i++) {
			for(int j = 0; j < this.mots.length; j++) {
		//		if(i % 10000 == 0)
		//			System.out.println("Ca avance tiens bon...");
				// On vérifie d'abord s'il y a au plus dif différences entre les deux mots
				// Si oui, on ajoute deux arcs entre les deux mots (ou une arête dans le
				// cas d'un graphe non orienté)
				if(i != j && calcDiffSupp(this.mots[i], this.mots[j])) {
					ajouterArc(i, j);
				}
				
			}
		}
		System.out.println("Fin de lettre qui saute yes");
	}

	/**
	 * Méthode qui teste s'il y a au plus dif lettres qui diffèrent entre les mots considérés
	 * @param a le premier mot considéré.
	 * @param b le second mot considéré.
	 * @return false s'il y a plus de dif différences entre les deux mots, true s'il y a au plus dif differences.
	 */
	public boolean difference(String a, String b) {
		if(a.length() != b.length())
			return false;
		int nbLettresDiff = 0, i = 0;
		while(nbLettresDiff <= this.dif && i != a.length()) {
			if(a.charAt(i) != b.charAt(i))
				nbLettresDiff++;
			i++;
		}
		
		return (nbLettresDiff <= this.dif);
	}
	
	/**
	 * Méthode qui teste si, pour transformer le mot a en le mot b, on fait au plus sup suppressions et au plus dif changements de lettres.
	 * @param a Le premier mot à considérer
	 * @param b Le deuxième mot à considérer
	 * @return true si la condition est remplie, false sinon.
	 */
	public boolean calcDiffSupp(String a, String b) {
		//System.out.println("mot 1 = "+a+" de taille : " +a.length() + " et mot 2 = "+b+" de taille : "+b.length());
		if(a.length() < b.length()) {
			return false;
		}
		
		int indiceRechLettre, limite, lim, nbLettresSupp = 0, nbLettresDiff = 0, i = 0, j = 0;
		if(a.length()-b.length() <= this.sup) {
			while(nbLettresDiff <= this.dif && nbLettresSupp <= this.sup && j != b.length() && i != a.length()) {
				if(a.charAt(i) == b.charAt(j)) {
					i++;
				} else {
					
					indiceRechLettre = ++i;
					lim = i+a.length()-b.length()-nbLettresSupp;
					if(lim < a.length())
						limite = lim;
					else
						limite = a.length();
					while(indiceRechLettre < limite && a.charAt(indiceRechLettre) != b.charAt(j)) {
						indiceRechLettre++;
					}
					if(indiceRechLettre == limite)
						nbLettresDiff++;
					else {
						i = indiceRechLettre+1;
						nbLettresSupp++;
					}

				}
			//	System.out.println("nbLettresDiff = " + nbLettresDiff);
			//	System.out.println("nbLettresSupp = " + nbLettresSupp);
				j++;
			}
		} else {
			nbLettresSupp = a.length()-b.length();
		}
		
		if(nbLettresDiff > this.dif || nbLettresSupp > this.sup)
			return false;
		
		return true;
	}
	
	/**
	 * Methode qui affiche le graphe. 
	 */
	public void display(){
        StringBuilder result = new StringBuilder();
        result.append("Graphe : \n");
        
     //   for (int i = 0; i < this.listeSucc.length; i++) {
        for (int i = 0; i < this.listeSucc.size(); i++) {

            result.append(this.mots[i] + " : ");
            
          //  this.listeSucc[i].iterer();
          Iterator<Integer> it =  this.listeSucc.get(i).iterator();
            
          /*  while(this.listeSucc[i].hasNext()){
            	int index = this.listeSucc[i].next();
            	result.append(this.mots[index]+" ");
            }
           */
          while(it.hasNext()){
        	  int index = it.next();
        	  result.append(this.mots[index]+" ");
          }
            result.append("\n");
        }
        System.out.println(result);
	}
	
	/**
	 * Recupere le mot en position pos
	 * @param pos
	 * @return le mot a la position pos
	 */
	public String getWord(int pos){
		return this.mots[pos];
	}
	
	/**
	 * Algorithme récursif de parcours de graphe en profondeur à partir du
	 * sommet de position sommet.
	 * 
	 * @param sommet
	 * @return Les sommets visités
	 */
/*	public String dfsRec(int sommet) {

		if (dejaVu[sommet]) {
			return "";
		}

		dejaVu[sommet] = true;
	//	StringBuilder res = new StringBuilder(this.getWord(sommet) + " ");
		StringBuilder res = new StringBuilder(sommet + " ");
		
		/*
		 * this.listeSucc[sommet].iterer(); while
		 * (this.listeSucc[sommet].hasNext()) { int indice =
		 * this.listeSucc[sommet].next(); res.append(this.dfsRec(indice));
		 * pere[indice] = sommet; }
		 *
		Iterator<Integer> it = this.listeSucc.get(sommet).iterator();

		while (it.hasNext()) {
			int indice = it.next();
			res.append(this.dfsRec(indice));
			pere[indice] = sommet;
		}
		return res.toString();
	}
	*/
	public void dfsRec(int sommet, int indiceListe){
		this.cpt++;
		if(this.cpt % 10000 == 0)
			System.out.println(this.cpt);
		
		if (dejaVu[sommet]) {
			return;
		}

		dejaVu[sommet] = true;
		this.compConnexe.get(indiceListe).add(sommet);
		
		/*
		 * this.listeSucc[sommet].iterer(); while
		 * (this.listeSucc[sommet].hasNext()) { int indice =
		 * this.listeSucc[sommet].next(); res.append(this.dfsRec(indice));
		 * pere[indice] = sommet; }
		 */
		Iterator<Integer> it = this.listeSucc.get(sommet).iterator();

		while (it.hasNext()) {
			int indice = it.next();
			this.dfsRec(indice,indiceListe);
			pere[indice] = sommet;
		}
	}
	
	/**
	 * Methode qui va effectuer le parcours en profondeur a partir du sommet d'indice
	 * sommet avec les tableaux pere et dejaVu qui vont correspondre a cette configuration
	 * @param sommet L'indice du sommet a considerer
	 * @return Les sommets visites
	 */
	/*public void dfs(int sommet) {
		resetTab(true, true);
		 this.dfsRec(sommet);
	}
	*/
	public void dfs(int sommet, int indiceListe){
		resetTab(true, true);
		this.compConnexe.add(new ArrayList<Integer>());
		this.dfsRec(sommet,indiceListe);
	}
	
	/**
	 * Methode qui parcours la table dejaVu et retourne l'indice du premier sommet non visité ou -1 
	 * @return -1 si tout les sommets ont été visités ou l'indice du premier sommet non visite.
	 */
	public int getComposanteNonVue(){
		for(int i=0; i<this.dejaVu.length;i++){
			if(!this.dejaVu[i]){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Methode qui calcule toute les composantes connexes du graphe. 
	 */
	/*public void visit(){
		int index = 0;
		StringBuilder res = new StringBuilder("");
		int i = 0;
		String dfs;
		while((index = this.getComposanteNonVue()) != -1){
			pere[index] = -1;
			dfs = this.dfsRec(index);
			this.composanteConnexe[i] = dfs.split(" ");
			res.append(++i +": " + dfs +"\n");
		}
		
		System.out.println(res.toString());
	}*/
	
	/**
	 * Methode qui calcule toute les composantes connexes du graphe. 
	 */
	public void visit2(){
		int index = 0;
		int i = 0;
		while((index = this.getComposanteNonVue()) != -1){
			this.compConnexe.add(new ArrayList<Integer>());
			this.cpt = 0;
			pere[index] = -1;
			this.dfsRec(index,++i);
			this.cpts.add(this.cpt);
		}
		
		int indice = 1;
		for(ArrayList<Integer> liste : this.compConnexe){
			System.out.print(indice+": ");
			for(Integer value: liste){
				System.out.print(value+" ");
			}
			System.out.print("\n");
			indice++;
		}
	}
	
	
	/**
	 * Methode qui cherche la composante connexe où se trouve le mot mot. Elle
	 * retourne la position +1 pour correspondre à l'affichage qui débute à 1
	 * 
	 * @param mot
	 *            le mot recherche
	 * @return l'indice +1 correspondant à la composante connexe ou -1 si elle
	 *         n'existe pas (ce qui revient à dire que le mot ne fait pas parti
	 *         du dictionnaire).
	 */
	/*
	 * public int getComposanteConnexe(String mot){ int pos = -1; int i = 0;
	 * while(i < this.mots.length && pos == -1){
	 * if(this.composanteConnexe[i][0].equals("")){ break; } else{ int l =
	 * this.composanteConnexe[i].length; int n = 0; while(n < l){
	 * if(this.getWord(Integer.valueOf(this.composanteConnexe[i][n])).equals(mot
	 * )){ pos = ++i; break; } n++; } } i++; } return pos; }
	 */
	public int getComposanteConnexe(String mot) {
		int pos = -1;
	
		for (int j = 0; j < this.compConnexe.size(); j++) {
			for (int n = 0; n < this.compConnexe.get(j).size(); n++) {
				System.out.println(this.getWord(this.compConnexe.get(j).get(n)));
				if (this.getWord(this.compConnexe.get(j).get(n)).equals(mot)) {
					pos = ++j;
					break;
				}
			}
		}
		return pos;
	}
	
	/**
	 * Obtenir l'indice de word dans le tableau de mots
	 * @param word
	 * @return l'indice du mot si il existe sinon déclenche une erreur
	 */
	private int getIndiceWord(String word, String[] tab){
		 for (int i=0 ; i<tab.length ; i++){
		        if (word.equals(tab[i])) 
		        	return i ;
		 }
		    
		 throw new Error (word + " n'est pas dans le tableau.");
		 
	}
	
	public String chemin(String from, String to, boolean lePlusCourt) {
		// On parcourt le graphe a partir du sommet de from
		int indiceFrom = this.getIndiceWord(from, this.mots), indiceTo = this.getIndiceWord(to, this.mots);

		if (!lePlusCourt) {
			this.dfs(indiceFrom,0);
			// On verifie qu'ils sont dans une même composante connexe
			this.afficherCompConnexe();
			int numComposanteFrom = this.getComposanteConnexe(from);
			int numComposanteTo = this.getComposanteConnexe(to);
			System.out.println(numComposanteFrom + " " + numComposanteTo);
			// S'ils ne le sont pas, on peut dire en avance qu'il n'y a
			// aucun chemin possible entre les deux mots
			if (numComposanteFrom != numComposanteTo || numComposanteFrom == -1) {
				return "Il n'y a aucun chemin possible entre les mots " + from + " et " + to;
			}

		} else
			this.bfs(indiceFrom);

		StringBuilder res = new StringBuilder(to);
		int indiceWord = indiceTo;
		String word;

		// Pour retracer le chemin de from à to, il faut commencer au mot
		// d'indice to
		// et aller de pere en pere jusqu'au mot d'indice from.
		while (indiceWord != indiceFrom) {
			indiceWord = pere[indiceWord];
			if (indiceWord == -1) {
				return "Il n'y a aucun chemin possible entre les mots " + from + " et " + to;
			}
			word = this.mots[indiceWord];
			res.insert(0, " -> ");
			res.insert(0, word);
		}

		return res.toString();
	}
	
	public boolean excentricite(String mot) {
		// On cherche dans quelle composante connexe se trouve le mot
		int numComposante = this.getComposanteConnexe(mot), resMax = 0;
		String motCourant, res, motMax = null;
		System.out.println("Le mot est "+mot);
		if(numComposante != -1) {
			System.out.println("Taille = " + this.compConnexe.get(numComposante).size());
			for(int i = 0; i < this.compConnexe.get(numComposante).size(); i++) {
				motCourant = this.getWord(this.compConnexe.get(numComposante).get(i));
				System.out.println("Mot courant : " + motCourant);
				if(!motCourant.equals(mot)) {
					res = chemin(mot, motCourant, true);
					String[] cheminRes = res.split("->");
					System.out.println("Taille de cheminRes = " + cheminRes.length);
					if(cheminRes.length > resMax) {
						resMax = cheminRes.length;
						motMax = motCourant;
					}
				}
			}
			System.out.println("L'excentricité est : " + resMax + " et concerne le mot : " + motMax);
		} else {
			return false;
		}
		
		return true;
	}
	
	// RAZ du tableau dejaVu et/ou du tableau pere
	public void resetTab(boolean rstDejaVu, boolean rstPere){
		
		if(!rstDejaVu && !rstPere) {
			// Rien n'est modifie
			return;
		}
		
		for(int i = 0; i < this.dejaVu.length; i++){
			if(rstDejaVu)
				this.dejaVu[i] = false;
			if(rstPere)
				this.pere[i] = -1;
		}
		
	}
	
	public void afficherCompConnexe(){
		int i = 0;
		ArrayList<Integer> liste = this.compConnexe.get(0);
		System.out.print(i++ +": ");
			for(Integer val : liste){
				System.out.print(this.getWord(val)+ " ");
			}
			System.out.print("\n");
	System.out.println(this.compConnexe.get(0).size());
	}
	
	public void afficherCpts(){
		System.out.println("taille composante 1 = " + this.cpts.get(0));	
	}
	
	public void bfs(int sommet) {
		// Remise à 0 des table dejaVu et pere dans le cas où on les a déjà
		// utilisés
		this.resetTab(true, true);

		// Creation de la file
		File file = new File(this.mots.length);

		// On ajoute la position du premier mot que l'on considere
		file.add(sommet);

		// on passe le sommet que l'on considere en pos courante et on passe le
		// tableau a vrai
		int posCourante = sommet;
		this.dejaVu[posCourante] = true;

		// On fait toute la file
		while (!file.isEmpty()) {

			// Nouveau sommet courant est le premier depile
			posCourante = file.remove();

			// On va itere sur la liste des mots associes a ce sommet
			/*
			 * this.listeSucc[posCourante].iterer();
			 * while(this.listeSucc[posCourante].hasNext()){ int successeur =
			 * this.listeSucc[posCourante].next();
			 */
			Iterator<Integer> it = this.listeSucc.get(posCourante).iterator();

			while (it.hasNext()) {
				int successeur = it.next();
				// On regarde si on est deja passe sur ce sommet
				if (!dejaVu[successeur]) {
					// Si non on ajoute a la file, on ajoute le sommet pere et
					// on passe a visite
					file.add(successeur);
					pere[successeur] = posCourante;
					dejaVu[successeur] = true;
				}
			}
		}
	}
    
}
