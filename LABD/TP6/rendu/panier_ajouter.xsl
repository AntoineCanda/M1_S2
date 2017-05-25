<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:param name="plant"/>

<xsl:template match="/">

		<xsl:apply-templates select="BASKET" />

</xsl:template>

<xsl:template match="BASKET">
	<xsl:copy>
		<xsl:copy-of select="*" />
		<COMMON>
			<xsl:value-of select="$plant" />
		</COMMON>
	</xsl:copy>
</xsl:template>

</xsl:stylesheet>
