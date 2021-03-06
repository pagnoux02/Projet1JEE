<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Cr�er un compte</title>
</head>
<body>
<h3 class="center-align">Cr�er un compte</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
<div style="width: 80%; margin-left: 5%" class="row">
	<form class="col s12" action="${pageContext.request.contextPath}/AddCompte" method="post">
		<div class="input-field col s6">
			<input id="impPseudo" type="text" name="pseudo" required="required">
			<label for="impPseudo">Pseudo :</label>
		</div>
		<div class="input-field col s6">
			<input id="impNom" type="text" name="nom" required="required">
			<label for="impNom">Nom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPrenom" type="text" name="prenom" required="required">
			<label for="impPrenom">Pr�nom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impEmail" type="text" name="email" required="required">
			<label for="impEmail">Email :</label>
		</div>
		<div class="input-field col s6">
			<input id="impTelephone" type="text" name="telephone" required="required">
			<label for="impTelephone">T�l�phone :</label>
		</div>
		<div class="input-field col s6">
			<input id="impRue" type="text" name="rue" required="required">
			<label for="impRue">Rue :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCodePostal" type="text" name="codePostal" required="required">
			<label for="impCodePostal">Code postal :</label>
		</div>
		<div class="input-field col s6">
			<input id="impVille" type="text" name="ville" required="required">
			<label for="impVille">Ville :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPass" type="password" name="pass" required="required">
			<label for="impPass">Mot de passe :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCfPass" type="password" name="cfPass" required="required">
			<label for="impCfPass">Confirmation :</label>
		</div>
		<div class="center-align input-field col s12">
			<input class="waves-effect waves-light btn" id="impPseudo" type="submit" name="Btn" value="Connecter">
			<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Connection">Annuler</a>		
		</div>
	</form>
</div>
</body>
</html>