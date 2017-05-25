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
	<br>
	<form method="get" action="panier">
		<button type="submit">Mon panier</button>
	</form>
	<br>
	<%
		String titre = request.getParameter("titre");
	%>
	<%
		String auteur = request.getParameter("author");
	%>
	<%
		String year = request.getParameter("year");
	%>
	<%
		String quantite = request.getParameter("quantite");
	%>
	<%
		if (titre == "" || titre == null || auteur == null || auteur == "" || year == null || year == "") {
	%>
	<form method="POST" action="addBook">
		<label>Titre : </label><input type="text" name="titre" />
		<br>
		<label>Auteur : </label><input type="text" name="auteur" />
		<br>
		<label>Annee de parution : </label><input type="text" name="year" />
		<br>
 		<label>Quantite disponible : </label><input type="text" name="quantite" />
 		<br>
		<input	type="submit" value="Send" />
	</form>
	<%
		} else {
	%>
	<div id="book">
		<label>Titre : <%=titre%></label>
		<br>
		<label>Auteur : <%=auteur%></label>
		<br>
		<label>Annee de	parution :<%=year%></label>
		<br>
		<label>Quantite disponible :<%=quantite%></label>	
	</div>
	<%
		}
	%>

</body>

</html>