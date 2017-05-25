<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <xsl:variable name='family' select="//param[@name='family']"/>
  <xsl:variable name="sort_key" select="//param[@name='sort_key']"/>
  <!-- attention, il ne sait pas gerer la transmission des arbres XML -->

  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">

          <xsl:apply-templates select="document($families_xml)/CLASSIFICATION" mode="generate-select">
              <xsl:with-param name='default' select="$family"/>
          </xsl:apply-templates>

          <xsl:apply-templates select="document($catalog_xml)/CATALOG">
            <xsl:with-param name="sort_key" select="$sort_key"/>
            <xsl:with-param name="family" select="$family"/>
            <xsl:with-param name="families_xml" select="$families_xml"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CLASSIFICATION" mode="generate-select">
  <xsl:param name="default"/>
  <form>
    <b>Filtrer par Famille</b> : <select name="family" onChange="javascript:submit()">
   
	<option value="">Aucune</option>
      <xsl:apply-templates select="FAMILY" mode="generate-select">
        <xsl:with-param name="default" select="$default"/>
      </xsl:apply-templates>
    </select>
  </form>
</xsl:template>

<xsl:template match="FAMILY" mode="generate-select">
  <xsl:param name="default"/>

	<option value="{NAME}" > 
		<xsl:if test="$default = NAME">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:value-of select="NAME"></xsl:value-of>
	</option>
</xsl:template>

<xsl:template match="CATALOG">
  <xsl:param name="sort_key" select="$sort_key"/>
  <xsl:param name="family" select="$family"/>
  <xsl:param name="families_xml" select="$families_xml"/>


	<table border="1">
		<thead>
			<tr>
	     			<th><a href=" ?sort_key=COMMON">COMMON</a></th>
				<th><a href=" ?sort_key=BOTANICAL">BOTANICAL</a></th>
			       	<th><a href=" ?sort_key=ZONE">ZONE</a></th>
				<th><a href=" ?sort_key=LIGHT">LIGHT</a></th>
				<th><a href=" ?sort_key=PRICE">PRICE</a></th>
				<th><a href=" ?sort_key=AVAILABILITY">AVAILABILITY</a></th>
	   		</tr>
	 	</thead>
	 	<tbody>
			<xsl:choose>
				<xsl:when test="$family != ''">
					<xsl:apply-templates select="//PLANT[document($families_xml)//FAMILY[$family = NAME]/SPECIES = BOTANICAL]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="//PLANT" >
         					<xsl:sort order="ascending" data-type="text" select="*[name()=$sort_key]"/>
					</xsl:apply-templates>
				</xsl:otherwise>
			</xsl:choose>
		</tbody>
	</table>
</xsl:template>

<xsl:template match="PLANT">
	<xsl:variable name="plante" select="COMMON" />
	<tr>
		<td> <xsl:value-of select="COMMON"/> </td>
		<td> <xsl:value-of select="BOTANICAL"/> </td>
		<td> <xsl:value-of select="ZONE"/> </td>
		<td> <xsl:value-of select="LIGHT"/> </td>
		<td> <xsl:value-of select="PRICE"/> </td>
		<td> <xsl:value-of select="AVAILABILITY"/> </td>
		<td> <a href="panier_ajouter.php?plant={$plante}">Add</a></td>
	</tr>
</xsl:template>	

</xsl:stylesheet>
