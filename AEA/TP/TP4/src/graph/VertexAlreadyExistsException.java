package graph;

@SuppressWarnings("serial")
public class VertexAlreadyExistsException extends Exception {
	public VertexAlreadyExistsException(String message){
		super(message);
	}
}
