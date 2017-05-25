package sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cette classe permet de rassembler une sequence d'origine
 * et tout ce qui est consideree comme etant une occurrence de
 * ce mot, en fonction du mode choisi par l'utilisateur
 * 
 * @author Claire
 *
 */
public class Mot {
	// Les differentes formes prises par le mot recherche (sequence d'origine, reverse, complementaire et reverse complementaire)
	private List<String> sequences;
	// Le mode considere d'etude de la sequence qui determine quelles formes on souhaite utiliser.
	private String mode;
	
	/**
	 * Constructeur de Mot
	 * @param seqOrigine La sequence d'origine
	 * @param mode Le mode a considerer
	 */
	public Mot(String seqOrigine, String mode) {
		this.mode = mode;
		this.sequences = getOtherSequences(seqOrigine);
	}
	
	/**
	 * Getter permettant d'obtenir la liste des formes du mot considere
	 * @return la liste des formes du mot
	 */
	public List<String> getSequences() {
		return sequences;
	}

	/**
	 * La fonction calcule a partir de la sequence recherche sa sequence reverse, complementaire et le reverse de sa complementaire
	 * @param sequence la sequence a considerer
	 * @return une liste de String contenant les differentes sequences
	 */
	private List<String> getOtherSequences(String sequence) {
		List<String> liste = new ArrayList<String>(4);
		
		StringBuilder reverse = new StringBuilder("");
		StringBuilder complementaire = new StringBuilder("");
		StringBuilder reverseCompl = new StringBuilder("");
		
		// On obtient le tableau des caracteres de notre sequence d'ADN que l'on souhaite chercher de base
		char[] seq = sequence.toCharArray();
		
		// Action que l'on repete pour chaque lettre du mot
		for(char c : seq){
			// Methode qui permet d'obtenir la lettre complementaire de celle consideree
			char cComp = getComplementaire(c, false);
			// On ajoute la lettre toujours en premiere position du mot, ainsi la premiere lettre du mot sera en derniere position et la derniere en premiere...
			reverse.insert(0, c);
			// On ajoute la lettre complementaire a celle consideree dans l'ordre ou on les rencontre
			complementaire.append(cComp);
			// On ajoute la lettre complementaire en premiere position comme pour le reverse
			reverseCompl.insert(0, cComp);
		}

		// En fonction du mode choisi on ajoute les mots a la liste ou pas
		switch (this.mode) {
		// "-0" mode ou l'on cherche uniquement la sequence passe en parametre
		// (mode unique)
		case "-0":
			liste.add(sequence);
			break;
		// "-1" mode unique + son complementaire
		case "-1":
			liste.add(sequence);
			liste.add(complementaire.toString());
			break;
		// "-2" mode unique + son reverse
		case "-2":
			liste.add(sequence);
			liste.add(reverse.toString());
			break;
		// "-3" mode unique + son reverse complementaire
		case "-3":
			liste.add(sequence);
			liste.add(reverseCompl.toString());
			break;
		// "-4" mode unique + reverse + complementaire
		case "-4":
			liste.add(sequence);
			liste.add(reverse.toString());
			liste.add(complementaire.toString());
			break;
		// "-5" mode unique + reverse + reverse complementaire
		case "-5":
			liste.add(sequence);
			liste.add(reverse.toString());
			liste.add(reverseCompl.toString());
			break;
		// "-6" mode unique + complementaire + reverse complementaire
		case "-6":
			liste.add(sequence);
			liste.add(complementaire.toString());
			liste.add(reverseCompl.toString());
			break;
		// "-7" mode unique + reverse + complementaire + reverse complementaire
		case "-7":
			liste.add(sequence);
			liste.add(reverse.toString());
			liste.add(complementaire.toString());
			liste.add(reverseCompl.toString());
			break;
		default:
			throw new IllegalArgumentException("Le mode n'existe pas, il est compris entre \"-0\" et \"-7\"");	

		}
		return liste;
	}
	
	/**
	 * Methode qui retourne le nucleotide complementaire a celui passe en parametre
	 * Attention ce code ne marche que pour le cas ou on a de l'ARN : il faudrait creer une version pour ADN en remplacant 'U' par 'T'
	 * @param car Le nucleotide d'origine
	 * @return char cComp son complementaire
	 */
	public char getComplementaire(char car, boolean apparGU){
		Random random = new Random();
		char cComp;
		switch(car){
		case 'A':
			cComp='U';
			break;
		case 'U':
			if(apparGU){
				double alea = random.nextDouble();
				if(alea>0.5){
					cComp = 'A';
				}
				else{
					cComp = 'C';
				}
			}
			else{
			cComp='A';
			}
			break;
		case 'G':
			if(apparGU){
				double alea = random.nextDouble();
				if(alea>0.5){
					cComp = 'C';
				}
				else{
					cComp = 'U';
				}
			}
			else{
			cComp='C';
			}
			break;
		case 'C':
			cComp='G';
			break;
		default:
			throw new IllegalArgumentException("Le caractere n'est pas present.");	
		}
		return cComp;
	}
	
	/**
	 * Retourne la taille d'un mot
	 * @return la taille en entier d'un mot
	 */
	public int length(){
		return this.sequences.get(0).length();
	}
		
	/**
	 * Retourne la sequence initiale 
	 * @return String la sequence initiale
	 */
	public String getSequenceInit(){
		return this.getSequences().get(0);
	}
	
	/**
	 * Retourne le mode utilise
	 * @return String le mode utilise
	 */
	public String getMode(){
		return this.mode;
	}
	
	/**
	 * Affichage d'un mot (toutes ses sequences) sur la sortie standard
	 */
	public void afficherMot() {
		System.out.println("Le mot " + sequences.get(0)
		+ " et ses differentes formes : ");
		for(String seq : sequences) {
			System.out.println(seq);
		}
	}
	
	/**
	 * Verifie l'egalite d'un mot avec un autre, qui n'est
	 * pas l'egalite stricte voulue par la methode equals()
	 * de la classe Object qui est redefinie dans cette classe.
	 * Par exemple, avec cette fonction, les mots (ACAU, UGUA, UACA, AUGU)
	 * et (AUGU, UACA, UGUA, ACAU) sont egaux.
	 * @param autreMot L'autre mot avec lequel tester l'egalite
	 * @return true si les mots sont egaux selon la definition ci-dessus, false sinon
	 */
	public boolean egalite(Mot autreMot) {
		// Condition ci-dessous necessaire et suffisante pour
		// que deux instances de la classe Mot soient egales
		if (sequences.contains(autreMot.sequences.get(0)))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sequences == null) ? 0 : sequences.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mot other = (Mot) obj;
		if (sequences == null) {
			if (other.sequences != null)
				return false;
		} else if (!sequences.equals(other.sequences))
			return false;
		return true;
	}
	
	///////////////////////
	// Methodes pour KMP //
	///////////////////////
	
	/**
	 * Determiner si le mot a un prefixe donne
	 * @param prefixe la chaine a tester
	 * @return true si le mot a un prefixe, false sinon
	 */
	public boolean prefixe(String prefixe){
		String s = this.sequences.get(0);
		return s.startsWith(prefixe);
	}
	
	/**
	 * Determine si le mot a un suffixe donne
	 * @param suffixe la chaine a tester
	 * @return true si le mot a un suffixe, false sinon
	 */
	public boolean suffixe(String suffixe){
		String s = this.sequences.get(0);
		return s.endsWith(suffixe);
	}
	
	/**
	 * Determine si la suite est un bord du mot
	 * @param suite la chaine a tester
	 * @return true si le suite est un bord du mot, false sinon
	 */
	public boolean estBord(String suite){
		String s = this.sequences.get(0);
		return (!suite.isEmpty() && !suite.equals(s) && this.prefixe(suite) && this.suffixe(suite));
	}
	
}
