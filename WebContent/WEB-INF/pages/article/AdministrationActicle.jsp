<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<h3 class="center-align">Administration Article</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="miseEnformeDiv input-field col s12">
	<input id="searchBar" type="text" onkeyup="searchPseudo()"> <label
		for="searchBar">Entre L'article à rechercher :</label>
	</div>
	<div class="miseEnformeDiv">
	<table class="table" id="tablePseudo">
		<tr>
			<th>Nom Article</th>
			<th>description</th>
			<th>date de l'enchere</th>
			<th>date de fin de l'enchère</th>
			<th>mise a prix</th>
			<th>prix de vente</th>
			<th>etat de la vente</th>
			<th>Catègorie</th>
			<th>nom du vendeur</th>
			<th class="center-align" colspan="3">Lieu de retrait</th> 
			<th>Action</th>
		</tr>
		<core:forEach var="article" items="${listArticles}">
				<tr>
					<td>${article.nomArticle}</td>
					<td>${article.description}</td>
					<td>${article.dateDebutEncheres}</td>
					<td>${article.dateFinEncheres}</td>
					<td>${article.miseAPrix}</td>
					<td>${article.prixVente}</td>
					<td>${article.etatVente}</td>
					<td>${article.categorie.libelle}</td>
					<td>${article.utilisateur.pseudo}</td>
					<td>${article.retrait.rue}</td>
					<td>${article.retrait.code_postale}</td>
					<td>${article.retrait.ville}</td>
					<td>
						<a href="${pageContext.request.contextPath}"><i class="material-icons">edit</i></a>
						<a href="${pageContext.request.contextPath}/DeleteArticle?idDel=${article.noArticle}"><i class="material-icons">close</i></a>
					</td>
				</tr>
		</core:forEach>
		</table>
	</div>