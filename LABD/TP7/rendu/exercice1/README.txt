Exercice 1

Requete 1 : On souhaite avoir les titres des livres écrits à partir de 1991 par Addison-Wesley. 

Le contenu devrait contenir en autre : 

<bib>
    <book year="1994">
        <title>TCP/IP Illustrated</title>
    </book>
 
    <book year="1992">
        <title>Advanced Programming in the Unix environment</title>
    </book>
</bib>

Le resultat obtenu avec saxon est : 

<?xml version="1.0" encoding="UTF-8"?>
	<bib>
		<book year="1994">
			<title>TCP/IP Illustrated</title>                                                                        
		</book>
		
		<book year="1992">
			<title>Advanced Programming in the Unix environment</title>                                                                                                
		</book>		
	</bib>


Requete 2 :

La requete creer une liste de resultat (results) où chaque résultat (result) contient le couple titre du livre et auteur pour tout les livres du fichier qui ont un titre et un auteur. Si il y a plusieurs auteurs pour un même livre on aura plusieurs fois le livre qui sera la avec autant de nombre que d'auteurs.

On devrait avoir un résultat de la forme 

<results>
	<result>
		<title>TCP/IP Illustrated</title>
        <author><last>Stevens</last><first>W.</first></author>
	</result>
	
	<result>
		<title>Advanced Programming in the Unix environment</title>
        <author><last>Stevens</last><first>W.</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Abiteboul</last><first>Serge</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Buneman</last><first>Peter</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Suciu</last><first>Dan</first></author>
	</result>
</results>

Le résultat obtenu avec saxon est : 

<?xml version="1.0" encoding="UTF-8"?>
<results>
	<result>
		<title>TCP/IP Illustrated</title>
        <author><last>Stevens</last><first>W.</first></author>
	</result>
	
	<result>
		<title>Advanced Programming in the Unix environment</title>
        <author><last>Stevens</last><first>W.</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Abiteboul</last><first>Serge</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Buneman</last><first>Peter</first></author>
	</result>
	
	<result>
		<title>Data on the Web</title>
        <author><last>Suciu</last><first>Dan</first></author>
	</result>
</results>

Requete 3 :

La requete va creer une arboresence ou on associe pour chaque auteur les titres des livres qu'il a ecrit.
On a également trié la liste selon la balise <last> puis <first> des auteurs dans l'ordre alphabétique. 

On devrait avoir un résultat du genre : 

<results>
	<result>
	    <author><last>Abiteboul</last><first>Serge</first></author>
		<title>Data on the Web</title>
	</result>
	
	<result>
		<author><last>Buneman</last><first>Peter</first></author>
		<title>Data on the Web</title>
	</result>
	
	<result>
	    <author><last>Stevens</last><first>W.</first></author>
		<title>TCP/IP Illustrated</title>
		<title>Advanced Programming in the Unix environment</title>
	</result>
	
	<result>
        <author><last>Suciu</last><first>Dan</first></author>
		<title>Data on the Web</title>	
	</result>
</results>

Le résultat obtenu avec saxon est : 

<?xml version="1.0" encoding="UTF-8"?>
<results>
	<result>
	    <author><last>Abiteboul</last><first>Serge</first></author>
		<title>Data on the Web</title>
	</result>
	
	<result>
		<author><last>Buneman</last><first>Peter</first></author>
		<title>Data on the Web</title>
	</result>
	
	<result>
	    <author><last>Stevens</last><first>W.</first></author>
		<title>TCP/IP Illustrated</title>
		<title>Advanced Programming in the Unix environment</title>
	</result>
	
	<result>
        <author><last>Suciu</last><first>Dan</first></author>
		<title>Data on the Web</title>	
	</result>
</results>

Requete 4 :

La requête j'ai l'impression donnera le même résultat que la requete 3 mais cette fois en utilisant 
une fonction locale. 

Le resultat prédit est identique au précédent.

Le résultat obtenu par saxon est également identique.

Requete 5 :

La requete va construire une arboresence ou apparaitront les livres ayant au moins un auteur.
Dans le cas où il y a plusieurs auteurs pour ce livre, on affichera les deux premiers et ensuite on remplacera par la balise <et-al/>.

Ce qui donnerait le résultat suivant : 

<bib>
   <book>
      <title>TCP/IP Illustrated</title>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
   </book>
   
   <book>
      <title>Advanced Programming in the Unix environment</title>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
   </book>
   
   <book>
      <title>Data on the Web</title>
      <author>
         <last>Abiteboul</last>
         <first>Serge</first>
      </author>
      <author>
         <last>Buneman</last>
         <first>Peter</first>
      </author>
      <et-al/>
   </book>
</bib>

Le résultat obtenu par saxon est :

<?xml version="1.0" encoding="UTF-8"?>
<bib>
   <book>
      <title>TCP/IP Illustrated</title>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
   </book>
   
   <book>
      <title>Advanced Programming in the Unix environment</title>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
   </book>
   
   <book>
      <title>Data on the Web</title>
      <author>
         <last>Abiteboul</last>
         <first>Serge</first>
      </author>
      <author>
         <last>Buneman</last>
         <first>Peter</first>
      </author>
      <et-al/>
   </book>
</bib>

Requete 6 :

La requete associe pour chaque auteur le nombre de livre dont il est auteur (ou co-auteur dans le cas d'un livre ecrits à plusieurs). Le resultat est trie selon le last puis le first de auteur. 

Le resultat devrait être : 

<results>
   <result>
      <author>
         <last>Abiteboul</last>
         <first>Serge</first>
      </author>
      <number>1</number>
   </result>
   
   <result>
      <author>
         <last>Buneman</last>
         <first>Peter</first>
      </author>
      <number>1</number>
   </result>
   
   <result>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
      <number>2</number>
   </result>
   
   <result>
      <author>
         <last>Suciu</last>
         <first>Dan</first>
      </author>
      <number>1</number>
   </result>
</results>

Le résultat obtenu par saxon est : 

<?xml version="1.0" encoding="UTF-8"?>
<results>
    <result>
      <author>
         <last>Abiteboul</last>
         <first>Serge</first>
      </author>
      <number>1</number>
   </result>
   
   <result>
      <author>
         <last>Buneman</last>
         <first>Peter</first>
      </author>
      <number>1</number>
   </result>
   
   <result>
      <author>
         <last>Stevens</last>
         <first>W.</first>
      </author>
      <number>2</number>
   </result>
   
   <result>
      <author>
         <last>Suciu</last>
         <first>Dan</first>
      </author>
      <number>1</number>
   </result>
</results>

Requete 7 :

La requete 7 fait la moyenne des prx des livres pour chaque année. 
Ici, ca revient a recopier le prix pour chaque année car ils ont toute des années différentes mais si il y avait eu plusieurs livres on aurait bien eu la moyenne via la fonction avg().

Le resultat serait de la forme :

<data>
	<year value="1994" avgprice="65.95"/>
	<year value="1992" avgprice="65.95"/>
	<year value="2000" avgprice="39.95"/>
	<year value="1999" avgprice="129.95"/>
</data>

Et on obtient avec saxon :

<?xml version="1.0" encoding="UTF-8"?>
<data>
	<year value="1994" avgprice="65.95"/>
	<year value="1992" avgprice="65.95"/>
	<year value="2000" avgprice="39.95"/>
	<year value="1999" avgprice="129.95"/>
</data>