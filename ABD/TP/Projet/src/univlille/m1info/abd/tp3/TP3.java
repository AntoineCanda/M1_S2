package univlille.m1info.abd.tp3;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.ra.JoinQuery;
import univlille.m1info.abd.ra.ProjectionQuery;
import univlille.m1info.abd.ra.RAQuery;
import univlille.m1info.abd.ra.RelationNameQuery;
import univlille.m1info.abd.ra.SelectionQuery;
import univlille.m1info.abd.tp2.SimpleSGBD;

public class TP3 {
	
	/** Creates an operator that allows to (efficiently) execute the given operation on the given database. */
	public PhysicalOperator getOperator(RAQuery query, SimpleSGBD sgbd) {
		if(query instanceof ProjectionQuery){
			ProjectionQuery queryP = (ProjectionQuery) query;
			RAQuery subQuery = queryP.getSubQuery();
			String[] attributes = queryP.getProjectedAttributesNames();
			if (subQuery instanceof RelationNameQuery){
				RelationNameQuery rNameQuery = (RelationNameQuery) subQuery;
				SequentialAccessOnARelationOperator saonaro = new SequentialAccessOnARelationOperator(sgbd, rNameQuery.getRelationName());
				ProjectionOperator opP = new ProjectionOperator(saonaro, attributes);
				return opP;
			}
		} 
		
		if(query instanceof SelectionQuery){
			SelectionQuery queryP = (SelectionQuery) query;
			RAQuery subQuery = queryP.getSubQuery();
			String attributeName = queryP.getAttributeName();
			String value = queryP.getConstantValue();
			if (subQuery instanceof RelationNameQuery){
				RelationNameQuery rNameQuery = (RelationNameQuery) subQuery;
				SequentialAccessOnARelationOperator saonaro = new SequentialAccessOnARelationOperator(sgbd, rNameQuery.getRelationName());
				SelectionOperator sOp = new SelectionOperator(saonaro, attributeName, value);
				return sOp;
			}
		} 
		
		if(query instanceof JoinQuery){
			JoinQuery joinQuery = (JoinQuery) query;
			RAQuery subQuery1 = joinQuery.getLeftSubQuery();
			RAQuery subQuery2 = joinQuery.getRightSubQuery();
			
			if(subQuery1 instanceof RelationNameQuery && subQuery2 instanceof RelationNameQuery){
				RelationNameQuery rNameQuery1 = (RelationNameQuery) subQuery1;
				SequentialAccessOnARelationOperator saonaro1 = new SequentialAccessOnARelationOperator(sgbd, rNameQuery1.getRelationName());
				RelationNameQuery rNameQuery2 = (RelationNameQuery) subQuery2;
				SequentialAccessOnARelationOperator saonaro2 = new SequentialAccessOnARelationOperator(sgbd, rNameQuery2.getRelationName());
				JoinOperator jOp = new JoinOperator(saonaro1, saonaro2);
				return jOp;
			}
		}
			throw new UnsupportedOperationException("not yet implemented");
		
	}
}
