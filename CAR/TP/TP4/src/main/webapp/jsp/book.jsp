<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acceuil</title>
</head>
<body>
	<form method="get" action="/add">
		<button type="submit">Ajouter un livre</button>
	</form>
	<br>
	<form method="get" action="panier">
		<button type="submit">Mon panier</button>
	</form>
	<br>
	<form method="post" action="panier">
		<input type="hidden" name="type" value="liste">
		<button type="submit">Commandes</button>
	</form>
	<br>

	<form method="post" action="search">
		<label>Rechercher par : </label> <input type="radio" name="attribut"
			value="author" /> Auteur <input type="radio" name="attribut"
			value="title" /> Titre <br> <label>Recherche : </label> <input
			type="text" name="search" /> <br>
		<button type="submit">rechercher</button>
	</form>

	<h2>Mes livres</h2>

	<table>
		<tr>
			<th>Titre</th>
			<th>Auteur</th>
			<th><a href="/booksSort">Annee de parution</a></th>
			<th>Quantite</th>
		</tr>
		<%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");

            for (Book book: books) {
            	if(book.getQuantite() < 0){
        	out.print("<tr><td><a href=\"/book?id=" + book.getId() + "\">" + book.getTitle() + "</td><td>"
				+ book.getAuthor() + "</td><td> " + book.getYear() + "</td><td> " + book.getQuantite()
				+ "</td><td><input type=\"submit\" disabled=\"disabled\"name=\"order\" value=\"Ajouter au panier\"></td></tr>");
            	}
            	else
            	{
        	%>
		<form method="POST" action="panier">
			<input type="hidden" name="bookId" value=<%=book.getId()%>> 
			<input type="hidden" name="type" value="add">
			<%
			out.print("<tr><td><a href=\"/book?id=" + book.getId() + "\">" + book.getTitle() + "</td><td>"
					+ book.getAuthor() + "</td><td> " + book.getYear() + "</td><td> " + book.getQuantite()
					+ "</td><td><input type=\"submit\" "name=\"order\" value=\"Ajouter au panier\"></td></tr>");
			%>
		</form>
		<%
            	}
            }
		%>
		</table>
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
