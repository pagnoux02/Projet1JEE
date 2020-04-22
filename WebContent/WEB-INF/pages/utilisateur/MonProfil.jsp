<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="center-align">Mon profil</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div style="margin-left: 5%; float: left;">
</div>
	<div style="margin-left: 30%; float: left;">
		<h5>${utilisateur.pseudo}</h5>
		<table>
			<tr>
				<th>Nom :</th>
				<td>${utilisateur.nom}</td>
			</tr>
			<tr>
				<th>Prénom :</th>
				<td>${utilisateur.prenom}</td>
			</tr>
			<tr>
				<th>Email :</th>
				<td>${utilisateur.email}</td>
			</tr>
			<tr>
				<th>Téléphonne :</th>
				<td>${utilisateur.telephone}</td>
			</tr>
			<tr>
				<th>Rue :</th>
				<td>${utilisateur.rue}</td>
			</tr>
			<tr>
				<th>Code postal :</th>
				<td>${utilisateur.codePostal}</td>
			</tr>
			<tr>
				<th>Ville :</th>
				<td>${utilisateur.ville}</td>
			</tr>
		</table>
	</div>
	<div>
	<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Profil?modif=true&idmod=${user.numeroUtilisateur}">Modifier</a>
	</div>