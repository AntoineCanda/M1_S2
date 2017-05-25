<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
	
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<xsl:apply-templates select="//journees">
			<xsl:with-param name="n" select="18"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="journees">
		<xsl:param name="n"/>
		<xsl:copy-of select="//journee[$n]"/>
	</xsl:template>
	
</xsl:stylesheet>