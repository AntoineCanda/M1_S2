<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="sort_key" select="//param[@name='sort_key']"/>
            <xsl:with-param name='families_xml' select="//param[@name='families_xml']"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CATALOG">
  <!--xsl:variable name="sort_key" select="//param[@name='sort_key']"/-->
  <xsl:param name="sort_key"/>
  <xsl:param name="families_xml"/>
	<table border="1">
		<thead>
	    		<tr>
			       	<th><a href=" ?sort_key=COMMON">COMMON</a></th>
			       	<th><a href=" ?sort_key=BOTANICAL">BOTANICAL</a></th>
			       	<th><a href=" ?sort_key=ZONE">ZONE</a></th>
				<th><a href=" ?sort_key=LIGHT">LIGHT</a></th>
				<th><a href=" ?sort_key=PRICE">PRICE</a></th>
				<th><a href=" ?sort_key=AVAILABILITY">AVAILABILITY</a></th>
				<th>FAMILY</th>
	    		</tr>
		</thead>		
		<tbody>

			<xsl:apply-templates select="//PLANT">
            			<xsl:with-param name='families_xml' select="$families_xml"/>
			 	<xsl:sort order="ascending" data-type="text" select="*[name()=$sort_key]"/>
			</xsl:apply-templates>

		</tbody>
	</table>
</xsl:template>

<xsl:template match="PLANT">
  <xsl:param name="families_xml"/>
  <xsl:variable name="plante" select="COMMON" />
  <xsl:variable name="espece" select="BOTANICAL" />
  <xsl:variable name="family" select="document($families_xml)//FAMILY[SPECIES = $espece]/NAME" />

	<tr>
		<td> <xsl:value-of select="COMMON"/> </td>
		<td> <xsl:value-of select="BOTANICAL"/> </td>
		<td> <xsl:value-of select="ZONE"/> </td>
		<td> <xsl:value-of select="LIGHT"/> </td>
		<td> <xsl:value-of select="PRICE"/> </td>
		<td> <xsl:value-of select="AVAILABILITY"/> </td>
		<td> <a href="catalogue_par_famille.php?family={$family}"><xsl:value-of select="$family"/></a></td>
		<td> <a href="panier_ajouter.php?plant={$plante}">Add</a></td>
	</tr>
</xsl:template>

</xsl:stylesheet>
