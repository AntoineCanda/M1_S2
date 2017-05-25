<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>

	<xsl:template match="/">
		<html>
			<body>
			      <table border="1">
					<thead>
				    		<tr>
						       	<th>COMMON</th>
						       	<th>BOTANICAL</th>
						       	<th>ZONE</th>
							<th>LIGHT</th>
							<th>PRICE</th>
							<th>AVAILABILITY</th>
				    		</tr>
				 	</thead>
				 	<tbody>
				 		<xsl:apply-templates select="//PLANT">
         						<xsl:sort order="ascending" data-type="text" select="COMMON"/>
				 		</xsl:apply-templates>
				 	</tbody>
				</table>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="PLANT">
		<tr>
			<td> <xsl:value-of select="COMMON"/> </td>
			<td> <xsl:value-of select="BOTANICAL"/> </td>
			<td> <xsl:value-of select="ZONE"/> </td>
			<td> <xsl:value-of select="LIGHT"/> </td>
			<td> <xsl:value-of select="PRICE"/> </td>
			<td> <xsl:value-of select="AVAILABILITY"/> </td>
		</tr>
	</xsl:template>

</xsl:stylesheet>
