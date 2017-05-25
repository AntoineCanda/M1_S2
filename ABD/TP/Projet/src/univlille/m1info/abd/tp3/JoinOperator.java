package univlille.m1info.abd.tp3;

import java.util.ArrayList;
import java.util.List;

import univlille.m1info.abd.phys.PhysicalOperator;
import univlille.m1info.abd.schema.DefaultRelationSchema;
import univlille.m1info.abd.schema.RelationSchema;

public class JoinOperator implements PhysicalOperator {

	private PhysicalOperator subOp1;
	private PhysicalOperator subOp2;
	private RelationSchema schema;
	private List<String> listeCommun;
	//private List<String[]> listeTuple;
	//private boolean tuplesTrouve;
	private String[] tupleSub1;
	
	public JoinOperator(PhysicalOperator subOp1, PhysicalOperator subOp2){
		this.subOp1 = subOp1;
		this.subOp2 = subOp2;
		this.schema = null;
		this.listeCommun = new ArrayList<String>();
		//this.listeTuple = new ArrayList<String[]>();
		//this.tuplesTrouve = false;
		this.tupleSub1 = null;
		this.reset();
	}

	
	@Override
	public String[] nextTuple(){
		
		this.schema = this.resultSchema();
		RelationSchema schema1 = this.subOp1.resultSchema();
		RelationSchema schema2 = this.subOp2.resultSchema();

		String[] sort1 = schema1.getSort();
		String[] sort2 = schema2.getSort();
		
		if(this.tupleSub1 == null)
			this.tupleSub1 = this.subOp1.nextTuple();
		
		String[] tuplesSubOp2 = this.subOp2.nextTuple();
		
		if (this.tupleSub1 == null)
			return null;
		if (tuplesSubOp2 == null){
			this.tupleSub1 = this.subOp1.nextTuple();
			this.subOp2.reset();
		}
		
		while (this.tupleSub1!=null) {
			while (tuplesSubOp2!=null) {
				String[] tuples = schema.newEmptyTuple();
				boolean test = true;
				for (int i = 0; i < this.listeCommun.size(); i++) {
					if (!schema1.getAttributeValue(this.tupleSub1, this.listeCommun.get(i))
							.equals(schema2.getAttributeValue(tuplesSubOp2, this.listeCommun.get(i)))) {
						test = false;
					}
				}

				if (test) {
					for (int j = 0; j < schema1.getSort().length; j++) {
						String attributeName = sort1[j];
						String value = schema1.getAttributeValue(this.tupleSub1, attributeName);
						schema.setAttributeValue(value, tuples, attributeName);
					}

					for (int k = 0; k < sort2.length; k++) {
						String attributeName = sort2[k];
						if (!this.listeCommun.contains(attributeName)) {
							String value = schema2.getAttributeValue(tuplesSubOp2, attributeName);
							schema.setAttributeValue(value, tuples, attributeName);
						}
					}
					return tuples;
				}
				
				tuplesSubOp2 = this.subOp2.nextTuple();
			}
			this.tupleSub1 = this.subOp1.nextTuple();
			this.subOp2.reset();
		}
		return null;
	}
	
	@Override
	public RelationSchema resultSchema() {
		// TODO Auto-generated method stub
		RelationSchema schema1 = this.subOp1.resultSchema();
		RelationSchema schema2 = this.subOp2.resultSchema();
		String[] sort1 = schema1.getSort();
		String[] sort2 = schema2.getSort();
		
		List<String> liste = new ArrayList<String>();

		for (String s : sort1) {
			liste.add(s);
		}

		for (String s : sort2) {
			if (!liste.contains(s)) {
				liste.add(s);
			} else {
				this.listeCommun.add(s);
			}
		}

		String sort3[] = new String[liste.size()];

		for (int i = 0; i < liste.size(); i++) {
			sort3[i] = liste.get(i);
		}

		return new DefaultRelationSchema("NameToDefine", sort3);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.subOp1.reset();
		this.subOp2.reset();
	}
	
	public PhysicalOperator getSubOperatorRigth(){
		return this.subOp2;
	}

	public PhysicalOperator getSubOperatorLeft(){
		return this.subOp1;
	}
	
	public void setSubOperatorRight(PhysicalOperator op){
		this.subOp2 = op;
	}
	
	public void setSubOperatorLeft(PhysicalOperator op){
		this.subOp1 = op;
	}


	@Override
	public int nextPage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
