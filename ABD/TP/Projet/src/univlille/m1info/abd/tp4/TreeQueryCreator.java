package univlille.m1info.abd.tp4;

import java.util.ArrayList;
import java.util.Stack;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.ra.JoinQuery;
import univlille.m1info.abd.ra.ProjectionQuery;
import univlille.m1info.abd.ra.RAQuery;
import univlille.m1info.abd.ra.RAQueryVisitor;
import univlille.m1info.abd.ra.RelationNameQuery;
import univlille.m1info.abd.ra.RenameQuery;
import univlille.m1info.abd.ra.SelectionQuery;
import univlille.m1info.abd.tp2.SimpleSGBD;
import univlille.m1info.abd.tp3.JoinOperator;
import univlille.m1info.abd.tp3.ProjectionOperator;
import univlille.m1info.abd.tp3.SelectionOperator;
import univlille.m1info.abd.tp3.SequentialAccessOnARelationOperator;

public class TreeQueryCreator implements RAQueryVisitor {

	private Stack<PhysicalOperator> stack;
	private SimpleSGBD sgbd;
	
	public TreeQueryCreator(RAQuery q, SimpleSGBD sgbd){
		this.stack = new Stack<PhysicalOperator>();
		this.sgbd = sgbd;
		this.createTreeQuery(q);
	}
	
	public TreeQueryCreator(){
		this.stack = new Stack<PhysicalOperator>();
		this.sgbd = null;
	}
	
	public void setSGBD(SimpleSGBD sgbd){
		this.sgbd = sgbd;
	}
	
	public void createTreeQuery(RAQuery q) {
		// TODO Auto-generated method stub
		q.accept(this);
	}
	
	public PhysicalOperator getOperator(){
		return this.stack.pop();
	}

	@Override
	public void visit(SelectionQuery q) {

		// TODO Auto-generated method stub
		RAQuery subQuery = q.getSubQuery();
		subQuery.accept(this);
		String attributeName = q.getAttributeName();
		String value = q.getConstantValue();
		PhysicalOperator subOp = this.stack.peek();
		SelectionOperator sop = new SelectionOperator(subOp, attributeName, value);
		this.stack.push(sop);
	}

	@Override
	public void visit(ProjectionQuery q) {

		// TODO Auto-generated method stub
		RAQuery subQuery = q.getSubQuery();
		subQuery.accept(this);
		String[] attributes = q.getProjectedAttributesNames();
		PhysicalOperator subOp = this.stack.peek();
		ProjectionOperator pop = new ProjectionOperator(subOp, attributes);
		this.stack.push(pop);
	}

	@Override
	public void visit(JoinQuery q) {

		// TODO Auto-generated method stub
		RAQuery subQuery1 = q.getLeftSubQuery();
		subQuery1.accept(this);

		PhysicalOperator subOp1 = this.stack.peek();
		RAQuery subQuery2 = q.getRightSubQuery();
		subQuery2.accept(this);
		PhysicalOperator subOp2 = this.stack.peek();
		JoinOperator jop = new JoinOperator(subOp1, subOp2);
		this.stack.push(jop);
	}

	@Override
	public void visit(RenameQuery q) {

		// TODO Auto-generated method stub
		String oldAttributeName = q.getOldAttrName();
		String newAttributeName = q.getNewAttrName();
		RAQuery subQuery = q.getSubQuery();
		subQuery.accept(this);
		PhysicalOperator subOp = this.stack.peek();
		RenameOperator rop = new RenameOperator(subOp,oldAttributeName, newAttributeName);
		this.stack.push(rop);
	}

	@Override
	public void visit(RelationNameQuery q) {

		// TODO Auto-generated method stub
		SequentialAccessOnARelationOperator saonarop = new SequentialAccessOnARelationOperator(this.sgbd, q.getRelationName());
		this.stack.push(saonarop);
	}
	
	public Stack<PhysicalOperator> getTreeQuery(){
		return this.stack;
	}

	
	public void modifTreeQuery(){
		boolean aJoin = false;
		boolean aSelect = false;
		boolean aJoinAvantSelect = false;
		ArrayList<PhysicalOperator> listeOp = new ArrayList<PhysicalOperator>();
		int i=0;
		int indexSelect =0;
		int indexJoin = 0;
		while(!this.stack.empty()){
			PhysicalOperator op = this.stack.pop();
			listeOp.add(op);
			if(op instanceof SelectionOperator){
				if(!aSelect){
					if(aJoin){
						aJoinAvantSelect = true;
					}
					aSelect = true;
					indexSelect = i;
				}
			}
			else if(op instanceof JoinOperator){
				if(!aJoin){
					
					indexJoin = i;
					aJoin = true;
				}
			}
			i++;
		}
		
		if(aJoinAvantSelect){
			JoinOperator jop = (JoinOperator)listeOp.get(indexJoin);
			SelectionOperator sop = (SelectionOperator) listeOp.get(indexSelect);
			PhysicalOperator subLeft = jop.getSubOperatorLeft();
			PhysicalOperator subOp = sop.getSubOperator();
			
			jop.setSubOperatorLeft(subOp);
			sop.setSubOperator(subLeft);
			
			listeOp.set(indexJoin,sop);
			listeOp.set(indexSelect,jop);
		}
		
		for(int j=listeOp.size()-1;j >= 0; j--){
			this.stack.push(listeOp.get(j));
		}
		
	}
}
