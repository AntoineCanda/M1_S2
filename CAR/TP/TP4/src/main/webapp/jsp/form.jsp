<%@ page language="java" contentType="text/html"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>CAR TP4</title>
</head>
<body>

	<% String titre = request.getParameter("book-title"); %>
	<% String auteur = request.getParameter("book-author"); %>
	<% String year = request.getParameter("book-year"); %>
	
	<div class="container">
		<header>
			<h1>Formulaire avec JSP</h1>
		</header>
	
		<% if((titre != null) || (auteur != null) || (year!= null)) { %>
		<section>
			<h2> Récapitulatif informations</h2>
			<ul>
				<li> Titre = <% if(titre != null) {out.print(titre);} %> </li>
				<li> Auteur = <% if( auteur != null) {out.print(auteur);} %> </li>
				<li> Parution = <% if(year != null) {out.print(year);} %> </li>		
			</ul>
		</section>
		<% } %>
	</div>
	
	<div class="main">
		<form name="jsp-form" action="index.jsp">
			<div class="column">
				<label for="book-title"> Titre </label>
				<input id="book-title" name="book-title" type="text" <% if (titre != null) { %> value="<%=titre %>"<% } %> />
			</div>
			<div class="column">
				<label for="book-author"> Auteur </label>
				<input id="book-author" name="book-author" type="text" <% if (auteur != null) { %> value="<%=auteur %>"<% } %> />
			</div>
			<div class="column">
				<label for="book-year"> Titre </label>
				<input id="book-year" name="book-year" type="text" <% if (year != null) { %> value="<%=year %>"<% } %> />
			</div>						
		</form>
	</div>
</body>
</html>