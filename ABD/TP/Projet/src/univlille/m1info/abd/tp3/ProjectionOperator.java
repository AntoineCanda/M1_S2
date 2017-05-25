package univlille.m1info.abd.tp3;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.DefaultRelationSchema;
import univlille.m1info.abd.schema.RelationSchema;

public class ProjectionOperator implements PhysicalOperator{
	
	private PhysicalOperator subOp;
	private String[] attributes;

	public ProjectionOperator(PhysicalOperator subOp, String[] attributes){
		this.subOp = subOp;
		this.attributes = attributes;
		this.reset();

	}
	
	@Override
	public String[] nextTuple() {
		String tuples[] = this.subOp.nextTuple();
		if(tuples == null){
			return null;
		}
		else
		{
			String newTuple[] = new String[this.attributes.length];
			RelationSchema schema = this.subOp.resultSchema();
			
			for(int i=0; i < this.attributes.length; i++){
				newTuple[i] = schema.getAttributeValue(tuples, this.attributes[i]); 
			}
			return newTuple;
		}
	}

	@Override
	public RelationSchema resultSchema() {
		RelationSchema schema = new DefaultRelationSchema("newNomATrouver", this.attributes);
		return schema;
	}

	@Override
	public void reset() {
		this.subOp.reset();
	}

	@Override
	public int nextPage() {
		
		return 0;
	}

}
