<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<title>Liste des ench�re</title>
<body>
	<h3 class="center-align">Liste des ench�re</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="row">
		<div class="col s12">
			<h6>Flitres :</h6>
		</div>
		<form action="${pageContext.request.contextPath}/ListEnchereCo" method="post">
			<div class="col s6">
				<div class="input-field col s12">
					<i class="material-icons prefix">search</i> <input type="text"
						id="text-filter" name="search"> <label for="text-filter">Le nom
						de l'article contient</label>
				</div>
				<div class="col s12">
					<div class="col s3">
						<span><br />Cat�gorie : </span>
					</div>
					<div class="input-field col s9">
						<select id="list-categorie" name="categorie">
						<option value="" disabled selected  >Choisir categorie</option>
							<c:forEach var="cat" items="${listCat}">
								<option value="${cat.noCategorie}">${cat.libelle}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col s12">
					<div class="col s6">
						<label> <input id="radAchat" class="with-gap"
							name="grp-achatVente" value="achats" type="radio" checked /> <span>Achats</span>
						</label>
					</div>
					<div class="col s6">
						<label> <input id="radVente" class="with-gap"
							name="grp-achatVente" value="ventes" type="radio"/> <span>Mes ventes</span>
						</label>
					</div>
					<div id="divAchat" class="col s6">
						<label> <input class="filled-in" type="checkbox" name="enOuv" value="ouvert"/> <span>ench�res
								ouvertes</span>
						</label> <label> <input class="filled-in" type="checkbox" name="enCour" value="enCour" /> <span>mes
								ench�re en cours</span>
						</label> <label> <input class="filled-in" type="checkbox" name="enRepor" value="enRepor" /> <span>mes
								ench�re remport�es</span>
						</label>
					</div>
					<div id="divVente" class="col s6">
						<label> <input class="filled-in" type="checkbox" name="veCour" value="veCour" disabled />
							<span>mes ventes en cours</span>
						</label> <label> <input class="filled-in" type="checkbox" name="nonDebut" value="nonDeb" disabled />
							<span>ventes non d�but�es</span>
						</label> <label> <input class="filled-in" type="checkbox" name="veTerm" value="veTerm" disabled />
							<span>ventes termin�es</span>
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
	<div id="tableArticle">
	<c:forEach var="article"  items="${listArt}">
	<div>
		<div class="container">
		<div class="">
			<a href="${pageContext.request.contextPath}/DetailEnchere?idArtEnch=${article.noArticle}&idUser=${user.numeroUtilisateur}">
			<div class="col s12 m4 testCol">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
						<span id="myTitle" class="card-title upper cardTitle">${article.nomArticle}</span>
						<p>
							<label class="lebalCard">Prix :</label>
							<c:forEach var="listEnchere" items="${listEnchere}">
								<c:if test="${listEnchere.noArticle ==  article.noArticle}">
									<c:if test="${listEnchere.montant_enchere > 0}">
										<span class="duBlanc"> ${listEnchere.montant_enchere} points</span>
									</c:if>
								</c:if>
							</c:forEach>
							<br/>
							<label class="lebalCard">Fin de l'ench�re :</label>
							<span class="duBlanc">${article.dateFinEncheres}</span>
							<br/>
							<label class="lebalCard ">Vendeur :</label>
							<span><a class="duBlanc" href="${pageContext.request.contextPath}/MonProfil?idprof=${article.utilisateur.numeroUtilisateur}">${article.utilisateur.pseudo}</a></span>
						</p>
					</div>
				</div>
			</div>
			</a>
		</div>
		</div>
	</div>
	</c:forEach>
	</div>
	</c:if>
	<c:if test="${listArt == null || empty listArt }">
		<h3 class="center-align">il y a aucun Article au ench�re</h3>
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