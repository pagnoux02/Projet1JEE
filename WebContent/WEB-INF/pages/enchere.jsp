<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<link src="../../css/Mycss" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>ajouter une enchere </title>
</head>
<body>
<h3 class="center-align">Créer un compte</h3>
<div style="width: 80%; margin-left: 5%" class="row">
	<form class="col s12" action="${pageContext.request.contextPath}/AddEnchere" method="post">
		<div class="input-field col s6">
			<input id="iduti" type="text" name="id_uti" required="required">
			<label for="iduti">id utilisateur :</label>
		</div>
		<div class="input-field col s6">
			<input id="idarti" type="text" name="id_arti" required="required">
			<label for="idarti">id article  :</label>
		</div>
		<div class="input-field col s6">
			<input id="idmontant" type="text" name="montant" required="required">
			<label for="idmontant">montant :</label>
		</div>
		<div class="center-align input-field col s12">
			<input class="waves-effect waves-light btn" id="impPseudo" type="submit" name="Btn" value="Connecter">
			<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Connection">Annuler</a>		
		</div>
	</form>
</div>
</body>
</html>