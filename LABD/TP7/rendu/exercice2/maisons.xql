xquery version "1.0";

(: Generated with EditiX at Sun Mar 19 15:32:20 CET 2017 :)
<html>
	<head><title>Exercice 2 TP 7</title></head>
	<body>
		<table border="1">
			<thead> 
				<tr>
					<th> Maisons </th>
					<th> Surface(m2) </th>
				</tr>
			</thead>
			<tbody>
				{ for $m in doc("maisons.xml")//maison
				return  <tr><td>Maison {number($m/@id)}</td><td> {sum($m//@surface-m2[not(../ancestor::*[attribute::surface-m2])]) }</td></tr>}
			</tbody>
		</table>
	</body>
</html>
