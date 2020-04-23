<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<title>Liste des enchère</title>
<body>
	<h3 class="center-align">Liste des enchère</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="row">
		<div class="col s12">
			<h6>Flitres :</h6>
		</div>
		<form>
			<div class="col s6 cole">
				<div class="input-field col s12">
					<i class="material-icons prefix">search</i> <input type="text"
						id="text-filter"> <label for="text-filter">Le nom
						de l'article contient</label>
				</div>
				<div class="col s12">
					<div class="col s3">
						<span><br />Catégorie : </span>
					</div>
					<div class="input-field col s9">
						<select id="list-categorie">
							<option value="oui">oui</option>
							<option value="oui">oui</option>
							<option value="oui">oui</option>
						</select>
					</div>
				</div>
				<div class="col s12">
					<div class="col s6">
						<label> <input id="radAchat" class="with-gap"
							name="grp-achatVente" type="radio" checked /> <span>Achats</span>
						</label>
					</div>
					<div class="col s6">
						<label> <input id="radVente" class="with-gap"
							name="grp-achatVente" type="radio" /> <span>Mes ventes</span>
						</label>
					</div>
					<div id="divAchat" class="col s6">
						<label> <input class="filled-in" type="checkbox" /> <span>enchères
								ouvertes</span>
						</label> <label> <input class="filled-in" type="checkbox" /> <span>mes
								enchère en cours</span>
						</label> <label> <input class="filled-in" type="checkbox" /> <span>mes
								enchère remportées</span>
						</label>
					</div>
					<div id="divVente" class="col s6">
						<label> <input class="filled-in" type="checkbox" disabled />
							<span>mes ventes en cours</span>
						</label> <label> <input class="filled-in" type="checkbox" disabled />
							<span>ventes non débutées</span>
						</label> <label> <input class="filled-in" type="checkbox" disabled />
							<span>ventes terminées</span>
						</label>
					</div>
				</div>
			</div>
			<div class="col s6 center vertical-center">
				<button class="btn waves-effect waves-light btn-large" type="submit"
					name="recherche">recherche</button>
			</div>
		</form>
		<div class="col s12"></div>
	</div>
	<c:if test="${listArt != null || !empty listArt }">
	<c:forEach var="article"  items="${listArt}">
	<div>
		<div class="container">
		<div class="">
			<a href="${pageContext.request.contextPath}/DetailEnchere?idArtEnch=${article.noArticle}&idUser=${user.numeroUtilisateur}">
			<div class="col s12 m4 testCol">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
						<span class="card-title upper">${article.nomArticle}</span>
						<p>
							<label class="lebalCard">Prix :</label>
							<span>${article.prixVente} points</span>
							<br/>
							<label class="lebalCard">Fin de l'enchère :</label>
							<span>${article.dateFinEncheres}</span>
							<br/>
							<label class="lebalCard">Vendeur :</label>
							<span>${article.utilisateur.pseudo}</span>
						</p>
					</div>
				</div>
			</div>
			</a>
		</div>
		</div>
	</div>
	</c:forEach>
	</c:if>
	<c:if test="${listArt == null || empty listArt }">
		<h3 class="center-align">il y a aucun Article au enchère</h3>
	</c:if>

</body>
<script>
	$(document).ready(function() {
		$('select').formSelect();

		$('#radAchat').on('change', function() {
			$("#divAchat *").prop('disabled', false);
			$("#divVente *").prop('disabled', true);
			$("#divVente *").prop('checked', false);
		});
		$('#radVente').on('change', function() {
			$("#divAchat *").prop('disabled', true);
			$("#divAchat *").prop('checked', false);
			$("#divVente *").prop('disabled', false);
		});
	});
</script>
</html>