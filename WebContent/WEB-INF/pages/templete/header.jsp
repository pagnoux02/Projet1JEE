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
<title>App Enchere</title>
</head>
<body>
	<nav style="background-color: #26a69a;" class="testBack">
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/Home"
				class="brand-logo center">Courses</a> <a href="#"
				data-target="mobile-demo" class="sidenav-trigger"><i
				class="material-icons">menu</i></a>
			<ul class="left hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath}/AddListes">Ajouter
						une Liste</a></li>
				<li><a
					href="${pageContext.request.contextPath}/VisualiserListes">Visualiser
						les Liste</a></li>
			</ul>
		</div>
	</nav>

	<ul class="sidenav" id="mobile-demo">
		<li><a href="${pageContext.request.contextPath}/AddListes">Ajouter
				une Liste</a></li>
		<li><a href="${pageContext.request.contextPath}/VisualiserListes">Visualiser
				les Liste</a></li>
	</ul>
</body>
</html>