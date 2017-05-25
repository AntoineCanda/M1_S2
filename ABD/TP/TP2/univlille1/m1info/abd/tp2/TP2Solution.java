 package univlille1.m1info.abd.tp2;


import java.util.ArrayList;
import java.util.List;

import univlille.m1info.abd.schema.RelationSchema;
import univlille.m1info.abd.schema.SimpleSGBD;

public class TP2 {

	public String computeProjection(SimpleSGBD sgbd, String inputRelName,
			String[] attributes) {
		// TODO Auto-generated method stub
		SimpleDBRelation dbRel = sgbd.getRelation(inputRelName);

		String name = sgbd.getFreshRelationName();
		
		SimpleDBRelation relation = sgbd.createRelation(name, attributes);
		RelationSchema schema = relation.getRelationSchema();
		
		RelationSchema oldSchema = dbRel.getRelationSchema();
		String sort[] = oldSchema.getSort();
		for(String attribut : attributes){
			boolean present = false;
			for(String att : sort){
				if(att.equals(attribut))
					present = true;
			}
			if(present == false)
				throw new IllegalArgumentException("Attributs inconnus");
		}
		
		dbRel.switchToReadMode();
		
		boolean test = true;
		while( test ){
			String[] tuples = dbRel.nextTuple();
			if(tuples == null){
				test = false;
			}
			else{
				String[] t = schema.newEmptyTuple();

				for(String att : attributes){
					schema.setAttributeValue(oldSchema.getAttributeValue(tuples, att), t, att);
				}
				relation.addTuple(t);
			}
		}
		
		return relation.getRelationSchema().getName();
	}

	public String computeSelection(SimpleSGBD sgbd, String inputRelName,
			String attribute, String value) {
		// TODO Auto-generated method stub
		
		SimpleDBRelation dbRel = sgbd.getRelation(inputRelName);
		RelationSchema schema = dbRel.getRelationSchema();
		String sort[] = schema.getSort();

		String name = sgbd.getFreshRelationName();
		SimpleDBRelation relation = sgbd.createRelation(name, sort);
		
		
		dbRel.switchToReadMode();

		boolean test = true;
		while( test ){
			String[] tuples = dbRel.nextTuple();
			if(tuples == null){
				test = false;
			}
			else{
				String val = schema.getAttributeValue(tuples, attribute);
				if(val.equals(value)){
					relation.addTuple(tuples);
				}
			}
		}
		
		return relation.getRelationSchema().getName();
	}

	public String computeJoin(SimpleSGBD sgbd, String inputRelName1, String inputRelName2) {
		// TODO Auto-generated method stub

		SimpleDBRelation dbRel = sgbd.getRelation(inputRelName1);
		RelationSchema schema = dbRel.getRelationSchema();
		String sort[] = schema.getSort();

		SimpleDBRelation dbRel2 = sgbd.getRelation(inputRelName2);
		RelationSchema schema2 = dbRel2.getRelationSchema();
		String sort2[] = schema2.getSort();

		List<String> liste = new ArrayList<String>();
		List<String> listeCommun = new ArrayList<String>();

		for (String s : sort) {
			liste.add(s);
		}

		for (String s : sort2) {
			if (!liste.contains(s)) {
				liste.add(s);
			} else {
				listeCommun.add(s);
			}
		}

		String sort3[] = new String[liste.size()];

		for (int i = 0; i < liste.size(); i++) {
			sort3[i] = liste.get(i);
		}

		String name = sgbd.getFreshRelationName();
		SimpleDBRelation relation = sgbd.createRelation(name, sort3);
		RelationSchema schema3 = relation.getRelationSchema();


		dbRel.switchToReadMode();
		dbRel2.switchToReadMode();

		boolean test = true;
		List<String[]> listeTuple1 = new ArrayList<String[]>();
		List<String[]> listeTuple2 = new ArrayList<String[]>();

		while (test) {
			String[] tuples = dbRel.nextTuple();
			String[] tuples2 = dbRel2.nextTuple();

			listeTuple1.add(tuples);
			listeTuple2.add(tuples2);

			if (tuples == null && tuples2 == null) {
				test = false;
			}
		}

		for (String[] tuples : listeTuple1) {
			for (int m =0; m < listeTuple2.size(); m++) {
				String[] tuples2 = listeTuple2.get(m);
				boolean testCommun = true;
				int l = 0;
				while (testCommun && l < listeCommun.size()) {
					String s = listeCommun.get(l);
					String val = "", val2="";
					if(tuples != null){
						val = schema.getAttributeValue(tuples, s);
					}
					
					if(tuples2 != null){
						 val2 = schema2.getAttributeValue(tuples2, s);
					}
					
					if (!val.equals(val2) || val == "" || val2 == "") {
						testCommun = false;
					}
					l++;
				}

				if (testCommun == true) {

					String[] t = relation.getRelationSchema().newEmptyTuple();

					for (int j = 0; j < sort3.length; j++) {

						if (j < sort.length) {
							schema3.setAttributeValue(schema.getAttributeValue(tuples, sort3[j]), t, sort3[j]);
						} else {
							schema3.setAttributeValue(schema2.getAttributeValue(tuples2, sort3[j]), t, sort3[j]);
						}

					}
					relation.addTuple(t);
				}
			}
		}

		return relation.getRelationSchema().getName();
	}

}
