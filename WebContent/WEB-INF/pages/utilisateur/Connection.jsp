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
<link src="WEB-INF/css/Mycss" rel="stylesheet">
<title>App Enchere</title>
</head>
<body>
	<h3 class="center-align">ENI-Enchere</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div style="width: 25%; margin-left: 30%;" class="row">
		<form class="col s12"
			action="${pageContext.request.contextPath}/Connection" method="post">
			<div class="input-field col s12">
				<input id="impPseudo" type="text" name="pseudo" required="required">
				<label for="impPseudo">Identifiant</label>
			</div>
			<div class="input-field col s12">
				<input id="impPass" type="password" name="pass" required="required" value="${cookie.PassENIEnchere.value}">
				<label for="impPass">Mot de passe</label>
			</div>
			<div class="center-align input-field col s12">
				<p>
					<label> <input type="checkbox" name="savePass" value="saveMe"/> <span>Se souvenir de moi</span>
					</label>
				</p>
				<a href="${pageContext.request.contextPath }/MotDePasseForget">Mot de passe oublier</a>
			</div>
			<div class="center-align input-field col s12">
				<input class="waves-effect waves-light btn" type="submit"
					value="connection">
					<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath }/AddCompte">Crééer un compte</a>
			</div>
			<div class="center-align">
			<a href="${pageContext.request.contextPath}">Retour</a>
			</div>
		</form>
	</div>
</body>