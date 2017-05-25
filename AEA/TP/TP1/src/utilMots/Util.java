package utilMots;

/**
 * Classe utilitaire de quelques fonctions pour la recherche de mots
 * en général, ne travaillant que sur des String pour la classe KMP
 * qui est non fonctionnelle
 * 
 * @author Claire
 *
 */
public class Util {

	/**
	 * Determine si la suite est un bord du mot
	 * @param mot le mot a examiner
	 * @param suite la chaine a tester
	 * @return boolean true si la suite est un bord du mot, false sinon
	 */
	public static boolean estBord(String mot, String suite){
		return (suite != null && !suite.isEmpty() && !suite.equals(mot)
				&& mot.startsWith(suite) && mot.endsWith(suite));
	}
	
}
