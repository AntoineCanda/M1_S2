<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Wed Mar 08 13:23:16 CET 2017 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
		<clubs xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<xsl:apply-templates select="//club">
				<xsl:with-param name="var" select="//clubs/club/ville"/>
			</xsl:apply-templates>
		</clubs>
	</xsl:template>
	
	<xsl:template match="club">
		<xsl:param name="var" />
		<xsl:choose>
			<xsl:when test="@id= $var">
			<club><xsl:value-of select="./nom"/></club>
			</xsl:when>
			<xsl:when test="$var = //clubs/club/ville">
				<club>
					<xsl:copy-of select="./nom"/>
					<xsl:copy-of select="./ville"/>
					<rencontres>
						<domicile>
							<xsl:apply-templates select="//rencontre" mode="domicile">
								<xsl:with-param name="id" select="@id"/>
							</xsl:apply-templates>
						</domicile>		
						<exterieur>
							<xsl:apply-templates select="//rencontre" mode="exterieur">
								<xsl:with-param name="id" select="@id"/>
							</xsl:apply-templates>
						  </exterieur>
					</rencontres>	
				</club>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	
	<xsl:template match="rencontre" mode="domicile">
		<xsl:param name="id"/>
		<xsl:if test="./receveur = $id">
			<rencontre>
				<xsl:apply-templates select="//club">
					<xsl:with-param name="var" select="./invite"/>
				</xsl:apply-templates>
				<score><xsl:value-of select="./score"/></score>
			</rencontre>
		</xsl:if>		
	</xsl:template>
	
	<xsl:template match="rencontre" mode="exterieur">
		<xsl:param name="id"/>
		<xsl:if test="./invite = $id">
			<rencontre>
				<xsl:apply-templates select="//club">
					<xsl:with-param name="var" select="./receveur"/>
				</xsl:apply-templates>
				<score><xsl:value-of select="./score"/></score>
			</rencontre>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>



