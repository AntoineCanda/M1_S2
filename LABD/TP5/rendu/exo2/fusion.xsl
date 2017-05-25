<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Thu Mar 09 01:37:33 CET 2017 -->

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns="http://labd/art"
 xpath-default-namespace="http://labd/art" >

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
		<art  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<artistes>
				
  <xsl:apply-templates select="//artiste">

				
  </xsl:apply-templates>
			</artistes>
			 <xsl:apply-templates select="//mouvements"></xsl:apply-templates>
		</art>

	</xsl:template>


	 <xsl:template match="artiste">

	 	<xsl:variable name="nomArtiste" select="./nom"></xsl:variable>

	 	<xsl:variable name="prenomArtiste" select="./prénom"></xsl:variable>

	 	<artiste>
	
	 	<xsl:copy-of select="node()">
	
	
	 	</xsl:copy-of>
	
	 	<xsl:for-each select="document('catalogue-1.xml')//oeuvre[.//nom = $nomArtiste and .//prénom = $prenomArtiste]">
	
	 		<xsl:sort order="ascending" data-type="number" select="./date"></xsl:sort>
				
<oeuvre>
				
	 <xsl:attribute name="genre"  select="@genre"></xsl:attribute>
				
	<titre>
				
		<xsl:value-of select="./titre"></xsl:value-of>
				
	</titre>
				
	<date>
				
		<xsl:value-of select="./date"></xsl:value-of>
				
	</date>
				
</oeuvre>
			
</xsl:for-each>

	 	</artiste>

	 	

	 </xsl:template>

	 

	 <xsl:template match="mouvements">
		 <xsl:copy-of select="."></xsl:copy-of>

	 </xsl:template>
</xsl:stylesheet>
