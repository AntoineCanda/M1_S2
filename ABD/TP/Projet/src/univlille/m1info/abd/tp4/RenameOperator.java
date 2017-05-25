package univlille.m1info.abd.tp4;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.DefaultRelationSchema;
import univlille.m1info.abd.schema.RelationSchema;

public class RenameOperator implements PhysicalOperator{

	
	private PhysicalOperator subOp;
	private String oldAttributeName;
	private String newAttributeName;

	public RenameOperator(PhysicalOperator subOp, String oldAttributeName, String newAttributeName){
		this.subOp = subOp;
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
		this.reset();
	}
	
	@Override
	public String[] nextTuple() {
		// TODO Auto-generated method stub
		String[] tuples = this.subOp.nextTuple();
		if(tuples == null)
			return null;
		else 
			return tuples;
	}

	@Override
	public RelationSchema resultSchema() {
		// TODO Auto-generated method stub
		RelationSchema schema = this.subOp.resultSchema();
		String attributes[] = schema.getSort();
		
		for(int i=0; i<attributes.length;i++){
			if(attributes[i].equals(this.oldAttributeName)){
				attributes[i] = this.newAttributeName;
				break;
			}
		}
		
		RelationSchema newSchema =  new DefaultRelationSchema("newNomATrouver", attributes);
		
		return newSchema;
	}

	@Override
	public void reset() {
		this.subOp.reset();
	}

	@Override
	public int nextPage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
