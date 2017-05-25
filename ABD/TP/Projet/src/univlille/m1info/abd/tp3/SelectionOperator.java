package univlille.m1info.abd.tp3;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.RelationSchema;

public class SelectionOperator implements PhysicalOperator{
	
	private PhysicalOperator subOp;
	private String attribut;
	private String value;
	
	public SelectionOperator(PhysicalOperator subOp, String attributName, String value){
		this.subOp = subOp;
		this.attribut = attributName;
		this.value = value;
		this.reset();
	}
	@Override
	public String[] nextTuple() {
		String[] tuples = this.subOp.nextTuple();
		RelationSchema schema = this.resultSchema();
		boolean test = true;
		while(test){
			if(tuples == null){
				test = false;
			}
			else {
				if(this.value.equals(schema.getAttributeValue(tuples,this.attribut))){
					return tuples;
				}
				else{
					tuples = this.subOp.nextTuple();
				}
			}
		}
		return null;

	}
	
	@Override
	public RelationSchema resultSchema() {
		// TODO Auto-generated method stub
		return this.subOp.resultSchema();
	}
	@Override
	public void reset() {
		this.subOp.reset();
	}

	public PhysicalOperator getSubOperator(){
		return this.subOp;
	}
	
	public void setSubOperator(PhysicalOperator op){
		this.subOp = op;
	}
	@Override
	public int nextPage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
