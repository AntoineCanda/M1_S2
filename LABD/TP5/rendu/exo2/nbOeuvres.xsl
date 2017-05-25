<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Thu Mar 09 01:15:30 CET 2017 -->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns="http://labd/art"
 xpath-default-namespace="http://labd/art" >

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
		
<artistes>
		
			<xsl:apply-templates  select="//artiste"/>
		
</artistes>
	</xsl:template>
	
	 <xsl:template match="//artiste">
	 
	 	<xsl:variable name="nomArtiste" select="./nom"></xsl:variable>
	 	<xsl:variable name="prenomArtiste" select="./prénom"></xsl:variable>
	 	<artiste>
		 	<nom>
		 		<xsl:value-of select="./nom"></xsl:value-of>
		 	</nom>
		 	<prenom>
		 		<xsl:value-of select="./prénom"></xsl:value-of>
		 	</prenom>
		 	<nbOeuvres>
		 		<xsl:value-of select="count(document('catalogue-1.xml')//oeuvre[.//nom = $nomArtiste and .//prénom = $prenomArtiste])"> </xsl:value-of>
		 	</nbOeuvres>
	 	</artiste>	
	 </xsl:template>

</xsl:stylesheet>
