package main;

import java.util.List;

import univlille.m1info.abd.phys.PhysicalOperatorOld;
import univlille.m1info.abd.ra.ComparisonOperator;
import univlille.m1info.abd.ra.RAQuery;
import univlille.m1info.abd.ra.RelationNameQuery;
import univlille.m1info.abd.ra.SelectionQuery;
import univlille.m1info.abd.tp2.SimpleDBRelation;
import univlille.m1info.abd.tp2.SimpleSGBD;
import univlille.m1info.abd.tp4.TreeQueryCreator;

public class App {

	public static void main(String[] args) {

		// Le SGBD 
		SimpleSGBD sgbd = new SimpleSGBD();

		// La Query a creer et changer pour test
		RAQuery relNameQuery = new RelationNameQuery("REL");
		RAQuery query = new SelectionQuery(relNameQuery, "attrA", ComparisonOperator.EQUAL, "a1");
		
		// La relation a mettre a jour 
		SimpleDBRelation relation = sgbd.createRelation("REL", "attrA", "attrB");
		relation.addTuple(new String[]{"a1", "b1"});
		relation.addTuple(new String[]{"a2", "b2"});
		
		// On parcours la requete en creeant les operateurs physiques associes
		TreeQueryCreator treeCreator = new TreeQueryCreator(query,sgbd);
		PhysicalOperatorOld operator = treeCreator.getOperator();
		// On recupere la liste des tuples
		List<String[]> tuples = treeCreator.collectAllTuplesOfAnOperator(operator);
		
		// On fait un simple affichage
		for(String[] tuple: tuples){
			System.out.println("Nouveau tuples");
			for(String attr : tuple){
				System.out.println("Attribut = " + attr);
			}
		}
	}

}
