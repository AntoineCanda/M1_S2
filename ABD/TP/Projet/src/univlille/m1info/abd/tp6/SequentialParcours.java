package univlille.m1info.abd.tp6;

import univlille.m1info.abd.phys.DefaultRelation;
import univlille.m1info.abd.phys.MemoryManager;
import univlille.m1info.abd.phys.Page;
import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.RelationSchema;

public class SequentialParcours implements PhysicalOperator {

	private DefaultRelation relation;
	private MemoryManager mem;
	private Page page;
	
	public SequentialParcours(DefaultRelation relation, MemoryManager mem){
		this.relation = relation;
		this.mem = mem;
		this.page = null;
	}
	@Override
	public String[] nextTuple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelationSchema resultSchema() {
		// TODO Auto-generated method stub
		return relation.getRelationSchema();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int nextPage() {
		if(page == null){
			return this.relation.getFirstPageAddress();
		}
		else {
			return page.getAddressnextPage();
		}
	}

}
