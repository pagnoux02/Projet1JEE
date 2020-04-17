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
<title>App Enchere</title>
</head>
<body>
	<nav style="background-color: #26a69a;" class="testBack">
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}"
				class="brand-logo center">ENI_ENCHERE</a> <a href="#"
				data-target="mobile-demo" class="sidenav-trigger"><i
				class="material-icons">menu</i></a>
			<core:if test="${ userTrouver != true}">	
			<ul class="right hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath}/AddCompte">S'inscrie</a></li>
				<li><a
					href="${pageContext.request.contextPath}/Connection">Se connecter</a></li>
			</ul>
			</core:if>
			<core:if test="${user != null || !empty user || userTrouver == true}">
			<ul class="right hide-on-med-and-down">
				<li>Bienvenue : ${ user.pseudo }</li>
				<li><a href="${pageContext.request.contextPath}">Enchères</a></li>
				<li><a href="${pageContext.request.contextPath}">Vendre un article</a></li>
				<li><a href="${pageContext.request.contextPath}/Profil">profil</a></li>
				<li><a href="${pageContext.request.contextPath}/Connection?LogOut=true">Déconnexion</a></li>
			</ul>
			</core:if>
		</div>
	</nav>

	<ul class="sidenav" id="mobile-demo">
		<core:if test="${ userTrouver != true} ">	
				<li><a href="${pageContext.request.contextPath}/AddCompte">S'inscrie</a></li>
				<li><a
					href="${pageContext.request.contextPath}/Connection">Se connecter</a></li>
			</core:if>
			<core:if test="${user != null || !empty user || userTrouver == true}">
				<li><a href="${pageContext.request.contextPath}">Enchères</a></li>
				<li><a href="${pageContext.request.contextPath}">Vendre un article</a></li>
				<li><a href="${pageContext.request.contextPath}/Profil">profil</a></li>
				<li><a href="${pageContext.request.contextPath}/Connection?LogOut=true">Déconnexion</a></li>
			</core:if>
	</ul>
	<script type="text/javascript">
		$(document).ready(function () {
			$('.sidenav').sidenav();
		});
	</script>
</body>
</html>