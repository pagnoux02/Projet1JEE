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
<style>
  <jsp:include page="../css/Mycss.css" />
  <jsp:include page="../css/ilan.css" />
  <jsp:include page="../css/Fran�ois.css" />
</style>
<meta charset="UTF-8">
<title>App Enchere</title>
</head>
<body>
	<ul id="dropdown1" class="dropdown-content">
  		<li><a href="${pageContext.request.contextPath}">Enchere</a></li>
  		<li><a href="${pageContext.request.contextPath}/AdministrationArticle">Article</a></li>
  		<li class="divider"></li>
  		<li><a href="${pageContext.request.contextPath}/Profil?idprof=${user.numeroUtilisateur}">Profil</a></li>
	</ul>
	<ul id="dropdown2" class="dropdown-content">
  		<li><a href="${pageContext.request.contextPath}">Enchere</a></li>
  		<li><a href="${pageContext.request.contextPath}/AdministrationArticle">Article</a></li>
  		<li class="divider"></li>
  		<li><a href="${pageContext.request.contextPath}/Profil?idprof=${user.numeroUtilisateur}">Profil</a></li>
	</ul>
	<nav class="sixEmeb">
		<div class="nav-wrpper">
			<a href="${pageContext.request.contextPath}"
				class="brand-logo center">ENI_ENCHERE</a> <a href="#"
				data-target="mobile-demo" class="sidenav-trigger"><i
				class="material-icons">menu</i></a>
			<core:if test="${user == null || empty user || user.pseudo == null || empty user.pseudo || userTrouver == false}">	
			<ul class="right hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath}/AddCompte">S'inscrire</a></li>
				<li><a
					href="${pageContext.request.contextPath}/Connection">Se connecter</a></li>
			</ul>
			</core:if>
			<core:if test="${user != null || !empty user || userTrouver == true}">
			<ul class="right hide-on-med-and-down">
				<li>Bienvenue : ${ user.pseudo }</li>
				<core:if test="${user.administrateur == true}">
					<li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Administration<i class="material-icons right">arrow_drop_down</i></a></li>
				</core:if>
				<li><a href="${pageContext.request.contextPath}/ListEnchereCo">Listes des Ench�res</a></li>
				<li><a href="${pageContext.request.contextPath}/VendreActicle">Vendre un article</a></li>
				<li><a href="${pageContext.request.contextPath}/MonProfil?idprof=${user.numeroUtilisateur}">Mon profil</a></li>
				<li><a href="${pageContext.request.contextPath}/Connection?LogOut=true">D�connexion</a></li>
			</ul>
			</core:if>
		</div>
	</nav>
	<ul class="sidenav" id="mobile-demo">
		<core:if test="${user.pseudo == null || empty user.pseudo || userTrouver == false}">	
				<li><a href="${pageContext.request.contextPath}/AddCompte">S'inscrire</a></li>
				<li><a
					href="${pageContext.request.contextPath}/Connection">Se connecter</a></li>
		</core:if>
		<core:if test="${user != null || !empty user || userTrouver == true}">
				<li>Bienvenue : ${ user.pseudo }</li>
				<core:if test="${user.administrateur == true}">
					<li><a class="dropdown-trigger" href="#!" data-target="dropdown2">Administration<i class="material-icons right">arrow_drop_down</i></a></li>
				</core:if>
				<li><a href="${pageContext.request.contextPath}/ListEnchereCo">Listes des Ench�res</a></li>
				<li><a href="${pageContext.request.contextPath}/VendreActicle">Vendre un article</a></li>
				<li><a href="${pageContext.request.contextPath}/MonProfil?idprof=${user.numeroUtilisateur}">Mon profil</a></li>
				<li><a href="${pageContext.request.contextPath}/Connection?LogOut=true">D�connexion</a></li>
		</core:if>
	</ul>
	<script type="text/javascript">
		$(document).ready(function () {
			$('.sidenav').sidenav();
			

			$(".dropdown-trigger").dropdown();
		});
	</script>
</body>
</html>