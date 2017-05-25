package univlille.m1info.abd.tp3;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.RelationSchema;
import univlille.m1info.abd.tp2.SimpleSGBD;

public class SequentialAccessOnARelationOperator implements PhysicalOperator {

	private SimpleSGBD sgbd;
	private String relationName;
	
	public SequentialAccessOnARelationOperator(SimpleSGBD sgbd, String relationName){
		this.sgbd = sgbd;
		this.relationName = relationName;
	}
	@Override
	public String[] nextTuple() {
		return this.sgbd.getRelation(this.relationName).nextTuple();
	}

	@Override
	public RelationSchema resultSchema() {
		// TODO Auto-generated method stub
		return this.sgbd.getRelation(this.relationName).getRelationSchema();
	}

	@Override
	public void reset() {
		
		this.sgbd.getRelation(this.relationName).switchToReadMode();

		// TODO Auto-generated method stub
		
	}
	@Override
	public int nextPage() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
