package detection;

public class PreMicroARN {
	private int positionDebut;
	private int positionFin;
	private String structureAppariements; // La structure des appariements de ce pré-microARN avec les points et les paranthèses
	
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
