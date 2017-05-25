package sequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * La classe Sequence contient les methodes qui creent les sequences de
 * nucleotides.
 * 
 * @author Antoine
 *
 */
public class Sequence {

	/**
	 * Methode qui a partir d'un fichier cree la sequence d'ADN et ne conserve pas l'identifiant associe 
	 * @param file le fichier contenant l'identifiant et la sequence souhaitee
	 * @return String La sequence d'ADN du fichier FASTA
	 */
	public String createSequence(String file) {
		File f = new File(file);
		FileReader freader;
		StringBuilder sequence = new StringBuilder("");
	
		try {
			freader = new FileReader(f);
			BufferedReader buffer = new BufferedReader(freader);
			String tmp = new String();

			// Lecture de l'identifiant que l'on ne conserve pas.
			buffer.readLine();
			// Lecture de la sequence d'ADN que l'on souhaite conserver. 
			while ((tmp = buffer.readLine()) != null) {
				if (tmp.length() > 0) {
					sequence.append(tmp);
				}
			}
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return sequence.toString();
		
	}

	
}