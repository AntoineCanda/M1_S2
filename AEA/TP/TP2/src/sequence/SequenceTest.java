package sequence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SequenceTest {

	/**
	 * fonction qui crée un fichier de test 
	 * @param fileName
	 * @param entete
	 * @param tailleNuclEncad
	 * @param boucleTerm
	 * @param boucleInter
	 * @param apparGU
	 * @param tailleTigeBoucle
	 */
	public void createFileTest(String fileName, String entete, int tailleNuclEncad, boolean boucleTerm,
			boolean boucleInter, boolean apparGU, int tailleTigeBoucle ){
		File f = new File(fileName);
			try {
				FileWriter fw = new FileWriter(f);
			
				fw.write(">"+entete+"\n");
				
				StringBuilder sb2 = new StringBuilder();
				String seqAlea1 = this.createSequenceAleaEncadrement(tailleNuclEncad);
				String microARN = this.createSequencePreMicroARN(boucleTerm, boucleInter, apparGU, tailleTigeBoucle);
				String seqAlea2 = this.createSequenceAleaEncadrement(tailleNuclEncad);
				sb2.append(seqAlea1+microARN+seqAlea2);
				
				this.miseEnFormeChaine(sb2,fw);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}
	
	private void miseEnFormeChaine(StringBuilder sb2, FileWriter fw) {
		int fin = sb2.length()/70;
		for(int i=0; i<fin;i++){
			try {
				fw.write(sb2.substring(i*70, (i+1)*70)+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw.write(sb2.substring(fin*70));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String createSequencePreMicroARN( boolean boucleTerm, boolean boucleInter, boolean apparGU, int tailleTigeBoucle ){

		Random random = new Random();
		
		char tab[] = new char[tailleTigeBoucle];
		if(boucleTerm){
			int nbNuclBoucleTerm = random.nextInt(8)+1;
			if(tailleTigeBoucle % 2 == 0 && nbNuclBoucleTerm % 2 == 1){
				if(nbNuclBoucleTerm == 1){
					nbNuclBoucleTerm++;
				}
				else{
					nbNuclBoucleTerm--;
				}
			}
			else if(tailleTigeBoucle % 2 == 1 && nbNuclBoucleTerm % 2 == 0){
				if(nbNuclBoucleTerm == 1){
					nbNuclBoucleTerm++;
				}
				else{
					nbNuclBoucleTerm--;
				}
			}
			int debut = (tailleTigeBoucle - nbNuclBoucleTerm)/2;
			for(int i= debut; i<debut+nbNuclBoucleTerm; i++){
				tab[i] = this.getNuclAlea().charAt(0);
			}
		}
		return "";
	}

	private String createSequenceAleaEncadrement(int tailleNuclEncad){
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<tailleNuclEncad; i++){
			sb.append(this.getNuclAlea());
		}
		
		return sb.toString();
	}

	private String getNuclAlea() {
		String res;
		Random random = new Random();
		double alea = random.nextDouble();
		if(alea>0.75){
			res = "A";
		}
		else if(alea>0.5 && alea <=0.75){
			res = "C";		
		}
		else if(alea>0.25 && alea <=0.5){
			res = "G";
		}
		else {
			res = "U";
		}
		return res;
	}
	
	public static void main(String args[]){
		SequenceTest st = new SequenceTest();
		st.createFileTest("fileTest.fasta","Fichier test bidon 1", 100, false, false, false, 10);
	}
}
