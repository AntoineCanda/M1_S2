package univlille.m1info.abd.tp2;

import univlille.m1info.abd.schema.RelationSchema;

public class TP2 {
	

	/** Fait une copie des données d'une relation vers une nouvelle relation.
	 * Retourne le nom de la nouvelle relation.
	 * @param sgbd
	 * @param relName
	 * @return
	 */
	public String copyRelation (SimpleSGBD sgbd, String relName) {
		// Mettre la relation en entrée et son schéma dans des variables pour pouvoir les utiliser plus facilement
		SimpleDBRelation rel = sgbd.getRelation(relName);
		RelationSchema relSchema = rel.getRelationSchema();
		
		// Générer un nom pour la nouvelle relation (évite de devoir en créer un)
		String newRelName = sgbd.getFreshRelationName();
	
		// Créer la nouvelle relation avec la même sorte que celle de la relation copiée
		SimpleDBRelation newRel = sgbd.createRelation(newRelName, sgbd.getRelation(relName).getRelationSchema().getSort());
	
		// Mettre le schéma de la nouvelle relation dans une variable pour pouvoir l'utiliser plus facilement
		RelationSchema newRelSchema = newRel.getRelationSchema();
		
		// Copier tous les tuples de la relation en entrée vers la nouvelle relation
		rel.switchToReadMode();
		newRel.switchToWriteMode();   // Normalement pas nécessaire, puisqu'à la création elle est déjà en mode écriture
		
		String[] relTuple;
		while ((relTuple = rel.nextTuple()) != null) {  // Tant qu'il reste des tuples dans rel
			// Créer un tuple vide pour la nouvelle relation
			String[] newRelTuple = newRelSchema.newEmptyTuple();
			// Copier les valeurs des attributs du tuple en entrée vers le nouveau tuple
			for (String attrName : relSchema.getSort()) {
				String attrValue = relSchema.getAttributeValue(relTuple, attrName);
				newRelSchema.setAttributeValue(attrValue, newRelTuple, attrName);
			}
			// Noter que ci-dessus il faut copier les valeurs des attributs
			// On ne peut pas simplement prendre relTuple et l'ajouter dans newRelTuple, car
			// dans ce cas les deux tuples seront des références vers le même tableau en mémoire
			// et une modification de l'un va aussi modifier l'autre
			
			// Ajouter le nouveau tuple à la nouvelle relation
			newRel.addTuple(newRelTuple);
		}
		
		// La copie est finie, retourner le nom de la nouvelle relation
		return newRelName;
		
	}
	

}
