package graphe;

public class Liste {
	private int [] successeurs;
	private int next;
	private int size;
	private int iterer;
	private Liste suivant;
	
	public Liste(int nombreSuccMax) {
		successeurs = new int[nombreSuccMax];
		next = 0;
		size = 0;
		iterer = 0;
	}
	/**
	 * Ajoute au tableau des successeurs l'indice du successeur trouve
	 * @param indiceMot
	 */
	public void ajouterSuccesseur(int indiceMot) {
		this.successeurs[this.next] = indiceMot;
		this.next++;
		this.size++;
	}
	
	/**
	 * Retourne la taille de la liste des successeurs 
	 * @return
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Passe la valeur de l'indice de parcours de la liste à 0
	 */
	public void iterer(){
		this.iterer = 0;
	}
	
	/**
	 * Teste si on est en fin de liste ou pas
	 * @return boolean true si il a un element qui suit l'element courant ou false le cas echeant
	 */
	public boolean hasNext(){
		return (this.iterer+1 <= this.getSize());
	}
	
	/**
	 * Retourne l'indice corresppondant au successeurs suivant l'element courant
	 * @return indice du successeur
	 * @throws IndexOutOfBoundsException
	 */
	public int next() throws IndexOutOfBoundsException{
		if(this.iterer <= this.getSize()){
			int res = this.successeurs[this.iterer];
			this.iterer++;
			return res;
		}
		else{
			throw new IndexOutOfBoundsException("Erreur indice");
		}
	}

}
