package dotplot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import recherche.Couple;
/**
 * Cette classe permet de creer le fichier .dat contenant la position des points du DotPlot.
 * Ces points sont la combinaison deux par deux des positions d'une occurrence.
 * Ainsi un nombre n de positions d'occurrences dans la chaine d'ADN donnera n² points dans le DotPlot.
 * @author antoine
 *
 */
public class DotPlot {
	
	/**
	 * Methode qui cree un fichier .dat a partir des positions obtenues par la
	 * methode naive
	 * 
	 * @param listeDoublons liste de listes des positions des mots
	 * @param nom le nom du fichier en sortie
	 * @param mode le mode de recherche
	 */
	public void createDotPlotFile(List<Integer> listeDoublons, String nom, String mode) {

		if (mode.equals("-man") || mode.equals("-s")) {
			// Creation du fichier
			File file = new File(nom);
			try {
				FileWriter fw = new FileWriter(file);
				// Pour chaque point, d'une liste correspondant aux positions
				// d'un mot, on calcule les différentes combinaisons possibles
				// deux par deux des positions
				for (int pt : listeDoublons) {
					for (int pt2 : listeDoublons) {
						fw.write(pt + "\t" + pt2 + "\n");
					}
				}
				fw.close();
			} catch (IOException e) {
				System.err.println("Erreur avec le fichier");
			}
		} else {
			System.err.println("Erreur de mode.");
		}
	}
	
	/**
	 * Methode permettant de creer le fichier .dat permettant l'affichage du DotPlot pour la methode optimisee	
	 * @param map une HashMap contenant pour chaque mot les positions des occurrences de ce mot
	 * @param nom le nom du fichier
	 */
	public void createDotPlotFile(Map<Double,Couple> map, String nom){
		// Creation du fichier
		File file = new File(nom);
		try {
			FileWriter fw = new FileWriter(file);
			// Pour chaque point, d'une liste correspondant aux positions
			// d'un mot, on calcule les différentes combinaisons possibles
			// deux par deux des positions
			for(Entry<Double, Couple> entry: map.entrySet()){
				List<Integer> doublon = entry.getValue().getPositions();
				for(int pt: doublon){
					for(int pt2: doublon){
						fw.write(pt+"\t"+pt2+"\n");
					}
				}
			}
			fw.close();
		} catch (IOException e) {
			System.err.println("Erreur avec le fichier");
		}
	}
}
