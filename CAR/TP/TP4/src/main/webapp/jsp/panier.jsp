<%@page import="car.tp4.entity.Book"%>
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
	<h2>Mon Panier</h2>
	<table>
		<tr>
			<th>Titre</th>
			<th>Auteur</th>
			<th>Annee</th>
		</tr>

		<%
			Collection<Book> books = (Collection<Book>) request.getAttribute("books");
			for (Book book : books) {
				out.print("<tr><td>" + book.getTitle() + "</td><td>" + book.getAuthor() + "</td><td> " + book.getYear()
						+ "</td></tr>");
			}
		%>
	</table>
	<form method="POST" action="books">
		<input type="hidden" name="type" value="valide"> 
		<input type="submit" value="confirmer la commande">
	</form>
	<form method="POST" action="books">
		<input type="hidden" name="type" value="reset"> 
		<input type="submit" value="annuler la commande">
	</form>
</body>

<style>
table {
	border-collapse: collapse;
}

td, th /* Mettre une bordure sur les td ET les th */ {
	border: 1px solid black;
}
</style>
</html>