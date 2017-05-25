<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Sat Mar 11 16:16:59 CET 2017 -->
<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://labd/art"
	xpath-default-namespace="http://labd/art">

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
		<art xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<artistes>
				<xsl:apply-templates select="//artiste"/>
			</artistes>
  			<xsl:copy-of select ="//mouvements"/>
  			<oeuvres>
  				<xsl:apply-templates select="document('catalogue-1.xml')//oeuvre"></xsl:apply-templates>
  			</oeuvres>
  		</art>
	</xsl:template>
	
	<xsl:template match="artiste">
		<artiste>
			<xsl:attribute name="num">
				<xsl:value-of select="position()"></xsl:value-of>
			</xsl:attribute>
			<xsl:copy-of select="node()"></xsl:copy-of>
		</artiste>
	</xsl:template>
	
	<xsl:template match="oeuvre">
		<xsl:variable name="nomArtiste" select="./auteur/nom"></xsl:variable>
		<xsl:variable name="prenomArtiste" select="./auteur/prénom"></xsl:variable>
		
		<oeuvre>
			<xsl:attribute name="auteur">
				<xsl:value-of select="document('artiste-avec-clef.xml')//artiste[./nom = $nomArtiste and ./prénom =$prenomArtiste]/@num" />
			</xsl:attribute>
			
			<xsl:copy-of select ="./titre"/>
			<xsl:copy-of select ="./date"/>
		</oeuvre>
		
	</xsl:template>
</xsl:stylesheet>
