<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='panier_xml' select="//param[@name='panier_xml']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($panier_xml)/BASKET"/>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="BASKET">
	<h2> Panier </h2>
	<table border="1">
		<thead>
			<tr>
				<th>COMMON</th>
			</tr>
		</thead>
		<tbody>
			<xsl:apply-templates select="//COMMON" >
				<xsl:sort order="ascending" data-type="text" select="."/>
			</xsl:apply-templates>
		</tbody>
	</table>	
</xsl:template>

<xsl:template match="COMMON">
	<xsl:variable name="num" select="position()" />
	<tr>
		<td><xsl:value-of select="." /></td>
		<td><a href="panier_supprimer.php?no_item={$num}"> Retirer </a></td>
	</tr>
</xsl:template>


</xsl:stylesheet>
