package recherche;

import java.util.ArrayList;
import java.util.List;

import sequence.Mot;
/**
 * Classe representant un couple de valeurs (Mot, List<Integer>)
 * La liste correspond a la liste des positions des occurrences du mot associe dans la sequence
 * @author antoine
 *
 */
public class Couple {
	// Mot considere
	private Mot mot;
	// Liste des positions de ce mot
	private List<Integer> positions;
	
	/**
	 * Constructeur de la liste
	 * @param mot le mot considere dans le couple
	 */
	public Couple(Mot mot){
		this.mot = mot;
		this.positions = new ArrayList<Integer>();
	}
	
	/**
	 * Ajoute la position pos a la liste des positions du couple
	 * @param pos la position d'une occurrence du mot
	 */
	public void addPosition(int pos){
		this.positions.add(pos);
	}
	
	/**
	 * Getter sur le mot du couple
	 * @return Mot mot considere
	 */
	public Mot getMot(){
		return this.mot;
	}
	
	/**
	 * Getter sur la liste des positions du couple
	 * @return une liste d'entiers
	 */
	public List<Integer> getPositions(){
		return this.positions;
	}
}
