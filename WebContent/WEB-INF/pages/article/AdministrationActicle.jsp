<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<h3 class="center-align">Administration Article</h3>
	<core:if test="${listArticles != null && !empty listArticles}">
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="miseEnformeDiv input-field col s12">
	<input id="searchBar" type="text" onkeyup="searchArticle()"> <label
		for="searchBar">Entre L'article à rechercher :</label>
	</div>
	<div class="miseEnformeDiv">
	<table class="table" id="tableArticle">
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
						<a href="${pageContext.request.contextPath}/VendreActicle?isModif=true&idArt=${article.noArticle}"><i class="material-icons">edit</i></a>
						<a href="${pageContext.request.contextPath}/DeleteArticle?idDel=${article.noArticle}"><i class="material-icons">close</i></a>
					</td>
				</tr>
		</core:forEach>
		</table>
	</div>
	</core:if>
	<core:if test="${listArticles == null || empty listArticles}">
		<div class="miseEnformeDiv">
			<h3 class="center-align">Il n'y a aucun article</h3>
		</div>
	</core:if>
	<script type="text/javascript">
	function searchArticle() {

		var input, filter, table, tr, td, i;
		input = document.getElementById("searchBar"); //contenu de l'input dans la barre de recherche
		filter = input.value.toUpperCase(); //
		table = document.getElementById("tableArticle"); //contenu de latable
		tr = table.getElementsByTagName("tr"); //contenu de la ligne
		for (i = 0; i < tr.length; i++) {
			tdPseudo = tr[i].getElementsByTagName("td")[0];
			des = tr[i].getElementsByTagName("td")[1];
			dateDebEn = tr[i].getElementsByTagName("td")[2];
			dateFinEn = tr[i].getElementsByTagName("td")[3];
			miseAprix = tr[i].getElementsByTagName("td")[4];
			prixVente = tr[i].getElementsByTagName("td")[5];
			etatVente = tr[i].getElementsByTagName("td")[6];
			categorie = tr[i].getElementsByTagName("td")[7];
			nonVendeur = tr[i].getElementsByTagName("td")[8];
			lieuRet = tr[i].getElementsByTagName("td")[9];
			
			if ((tdPseudo) || (des) || (dateDebEn) || (dateFinEn) || (miseAprix) || (prixVente) || (etatVente) || (categorie) 
					|| (nonVendeur) || (lieuRet)) {
				if ((tdPseudo.innerHTML.toUpperCase().indexOf(filter) > -1) || (des.innerHTML.toUpperCase().indexOf(filter) > -1) ||
				(dateDebEn.innerHTML.toUpperCase().indexOf(filter) > -1) || (dateFinEn.innerHTML.toUpperCase().indexOf(filter) > -1) || 
				(miseAprix.innerHTML.toUpperCase().indexOf(filter) > -1) || (prixVente.innerHTML.toUpperCase().indexOf(filter) > -1) ||
				(etatVente.innerHTML.toUpperCase().indexOf(filter) > -1) || (categorie.innerHTML.toUpperCase().indexOf(filter) > -1) ||
				(nonVendeur.innerHTML.toUpperCase().indexOf(filter) > -1) || (lieuRet.innerHTML.toUpperCase().indexOf(filter) > -1)) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>	