<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Thu Mar 02 11:48:49 CET 2017 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
	
	<xsl:apply-templates select="//clubs"></xsl:apply-templates>
	</xsl:template>
	<xsl:template match="clubs">
		<xsl:copy-of select="."/>
	</xsl:template>
	

	
</xsl:stylesheet>


