<?xml version="1.0" encoding="UTF-8"?>

<!-- New document created with EditiX at Thu Mar 02 10:48:29 CET 2017 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html"/>
	
	<xsl:template match="/">
	<html>
		<body>
		
	<h2>Les clubs de Ligue 1 <br/><br/>
       			saison 2014-2015
     			 </h2>
      <table border="1">
         <thead>
            <tr>
               <th>ville</th>
               <th>club</th>
            </tr>
         </thead>
         <tbody>
         		<xsl:apply-templates select="//club">
         			<xsl:sort order="ascending" data-type="text" select="ville"/>
         		</xsl:apply-templates>
         </tbody>
	</table>

		</body>
	</html>
	</xsl:template>


	<xsl:template match="club">
			<tr>
				<td><xsl:value-of select="ville"/></td>
				<td><xsl:value-of select="nom"/></td>

			</tr>         			
         	</xsl:template>
</xsl:stylesheet>


