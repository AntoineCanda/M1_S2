package graphe;

/**
 * Class File qui permet d'implementer une FIFO
 * Sert pour un parcours en largeur iteratif
 * @author antoine
 *
 */
public class File {

	private int[] fifo;
	private int posCourante;
	private int finFile;
	
	public File(int taille){
		this.fifo = new int[taille];
		this.posCourante = 0;
		this.finFile = 0;
	}
	
	public void add(int pos){
		this.fifo[this.finFile] = pos;
		this.finFile++;
	}
	
	public int remove(){
		return this.fifo[this.posCourante++];
	}
	
	public boolean isEmpty(){
		return this.posCourante == this.finFile;
	}
	
	public void clear(){
		this.posCourante = 0;
		this.finFile = 0;
		for(int i=0; i<this.fifo.length; i++){
			this.fifo[i] = -1;
		}
	}
}
