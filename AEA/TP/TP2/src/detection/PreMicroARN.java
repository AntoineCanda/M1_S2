package detection;

public class PreMicroARN {
	private int positionDebut;
	private int positionFin;
	private String structureAppariements; // La structure des appariements de ce pr�-microARN avec les points et les paranth�ses
	
	public PreMicroARN() {
		positionDebut = -1;
		positionFin = -1;
		structureAppariements = null;
	}

	public PreMicroARN(int positionDebut, int positionFin, String structureAppariements) {
		this.positionDebut = positionDebut;
		this.positionFin = positionFin;
		this.structureAppariements = structureAppariements;
	}
	
}
