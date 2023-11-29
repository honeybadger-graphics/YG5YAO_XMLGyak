<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h1>Simko Levente - 2023/24. I.felev</h1>
				<table border ="1">
					<tr bgcolor="#355e3b">
						<th>ID</th>
						<th>TIPUS</th>
						<th>Kurzusnev</th>
						<th>Oktato</th>
						<th>Tanterem</th>
						<th>Nap</th>
						<th>Kezdet</th>
						<th>Vege</th>
						<th>Szak</th>
					</tr>
					<xsl:for-each select="YG5YAO_Orarend/Ora">
						<tr>
							<td><xsl:value-of select="@Id"/></td>
							<td><xsl:value-of select="@TIPUS"/></td>
							<td><xsl:value-of select="Kurzusnev"/></td>
							<td><xsl:value-of select="Oktato"/></td>
							<td><xsl:value-of select="Tanterem"/></td>
							<td><xsl:value-of select="Idopont/Nap"/></td>
							<td><xsl:value-of select="Idopont/Kezdet"/></td>
							<td><xsl:value-of select="Idopont/Vege"/></td>
							<td><xsl:value-of select="Szak"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>