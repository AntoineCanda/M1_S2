<%@page import="car.tp4.entity.Panier"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acceuil</title>
</head>
<body>
	<form method="get" action="/books">
		<button type="submit">Liste de livre</button>
	</form>
	<br>
	<form method="get" action="/add">
		<button type="submit">Ajouter un livre</button>
	</form>
	<br>
	<form method="get" action="panier">
		<button type="submit">Mon panier</button>
	</form>
	<br>
	<h2>Toute les commandes</h2>
	<table>
		<tr>
			<th>Numero de la commande</th>
			<th>Quantite de livres</th>

		</tr>

		<%
			Collection<Panier> paniers = (Collection<Panier>) request.getAttribute("panier");
			for (Panier panier : paniers) {
				out.print("<tr><td>" + panier.getId() + "</td><td>" + panier.getQuantite() + "</td></tr>");
			}
		%>
	</table>

<style>
table {
	border-collapse: collapse;
}

td, th /* Mettre une bordure sur les td ET les th */ {
	border: 1px solid black;
}
</style>
</html>
