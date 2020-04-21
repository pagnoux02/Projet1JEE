<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<h3 class="center-align">Profil</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="input-field col s12" style="margin-left: 5%; width: 15%;">
	<input id="searchBar" type="text" onkeyup="searchPseudo()"> <label
		for="searchBar">Entre Le pseudo � rechercher :</label>
	</div>
<div style="margin-left: 5%; float: left;">
	<table id="tablePseudo">
		<tr>
			<th>Pseudo</th>
		</tr>
		<core:forEach var="liste" items="${listUtilisateur}">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/Profil?idProfil=${liste.numeroUtilisateur}">${liste.pseudo}</a>
			</tr>
		</core:forEach>

	</table>
</div>

<core:if test="${userProfil != null || !empty userProfil }">
	<div style="margin-left: 30%; float: left;">
		<h5>${userProfil.pseudo}</h5>
		<table>
			<tr>
				<th>Nom :</th>
				<td>${userProfil.nom}</td>
			</tr>
			<tr>
				<th>Prenom :</th>
				<td>${userProfil.prenom}</td>
			</tr>
			<tr>
				<th>Email :</th>
				<td>${userProfil.email}</td>
			</tr>
			<tr>
				<th>Telephonne :</th>
				<td>${userProfil.telephone}</td>
			</tr>
			<tr>
				<th>Rue :</th>
				<td>${userProfil.rue}</td>
			</tr>
			<tr>
				<th>Code postal :</th>
				<td>${userProfil.codePostal}</td>
			</tr>
			<tr>
				<th>Ville :</th>
				<td>${userProfil.ville}</td>
			</tr>
		</table>
	</div>
		<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Profil?modif=true&idmod=${userProfil.numeroUtilisateur}">Modifier</a>
		<a class="waves-effect waves-light btn red" href="${pageContext.request.contextPath}/DeleteUtilisateur?idDel=${userProfil.numeroUtilisateur}">Supprimer le compte</a>
</core:if>


<script type="text/javascript">
	function searchPseudo() {

		var input, filter, table, tr, td, i;

		input = document.getElementById("searchBar"); //contenu de l'input dans la barre de recherche

		filter = input.value.toUpperCase(); //

		table = document.getElementById("tablePseudo"); //contenu de latable

		tr = table.getElementsByTagName("tr"); //contenu de la ligne

		for (i = 0; i < tr.length; i++) {

			tdPseudo = tr[i].getElementsByTagName("td")[0];

			if (tdPseudo) {

				if (tdPseudo.innerHTML.toUpperCase().indexOf(filter) > -1) {

					tr[i].style.display = "";

				} else {

					tr[i].style.display = "none";

				}

			}

		}

	}
</script>


