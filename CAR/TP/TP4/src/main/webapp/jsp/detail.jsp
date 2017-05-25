<%@page import="car.tp4.entity.Book"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<form method="get" action="/books">
		<button type="submit">Liste de livre</button>
	</form>
	</br>
	<form method="get" action="panier">
		<button type="submit">Mon panier</button>
	</form>
	</br>
	<%
		Book book = (Book) request.getAttribute("id");
	%>


	<div id="book">
		<label>Titre : <%=book.getTitle()%></label>
		<br>
		<label>Auteur : <%=book.getAuthor()%></label>
		<br>
		<label>Annee de parution :<%=book.getYear()%></label>
		<br>
		<label>Quantite disponible :<%=book.getQuantite()%></label>
	</div>
</body>

</html>