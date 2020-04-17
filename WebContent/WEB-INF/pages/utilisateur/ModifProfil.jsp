<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<h3 class="center-align">Modifier mon compte</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
<div style="width: 80%; margin-left: 5%" class="row">
	<form class="col s12" action="${pageContext.request.contextPath}/Profil" method="post">
		<div class="input-field col s6">
			<input id="impPseudo" type="text" name="pseudo" required="required" value="${user.pseudo}">
			<label for="impPseudo">Pseudo :</label>
		</div>
		<div class="input-field col s6">
			<input id="impNom" type="text" name="nom" required="required" value="${user.nom}">
			<label for="impNom">Nom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPrenom" type="text" name="prenom" required="required" value="${user.prenom}">
			<label for="impPrenom">Prénom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impEmail" type="text" name="email" required="required" value="${user.email}">
			<label for="impEmail">Email :</label>
		</div>
		<div class="input-field col s6">
			<input id="impTelephone" type="text" name="telephone" required="required" value="${user.telephone}">
			<label for="impTelephone">Téléphone :</label>
		</div>
		<div class="input-field col s6">
			<input id="impRue" type="text" name="rue" required="required" value="${user.rue}">
			<label for="impRue">Rue :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCodePostal" type="text" name="codePostal" required="required"  value="${user.codePostal}">
			<label for="impCodePostal">Code postal :</label>
		</div>
		<div class="input-field col s6">
			<input id="impVille" type="text" name="ville" required="required" value="${user.ville}">
			<label for="impVille">Ville :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPass" type="password" name="AncPass" required="required" >
			<label for="impPass">Ancien Mot de passe :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPass" type="password" name="pass" required="required" >
			<label for="impPass">Nouveaux Mot de passe :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCfPass" type="password" name="cfPass" required="required">
			<label for="impCfPass">Confirmation Nouveaux Mot de passe :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCfPass" type="hidden" name="IdUtil" required="required" value="${user.numeroUtilisateur }">
		</div>
		<div class="center-align input-field col s12">
			<input class="waves-effect waves-light btn" id="impPseudo" type="submit" name="Btn" value="Modifier">
			<a class="waves-effect waves-light btn red" href="${pageContext.request.contextPath}/DeleteUtilisateur" value="${user.numeroUtilisateur}">Supprimer mon compte</a>
			<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Profil">Annuler</a>		
		</div>
	</form>
</div>		