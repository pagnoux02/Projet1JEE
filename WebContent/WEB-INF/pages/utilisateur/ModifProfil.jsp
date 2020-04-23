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
			<input id="impPseudo" type="text" name="pseudo" required="required" value="${utilisateur.pseudo}">
			<label for="impPseudo">Pseudo :</label>
		</div>
		<div class="input-field col s6">
			<input id="impNom" type="text" name="nom" required="required" value="${utilisateur.nom}">
			<label for="impNom">Nom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impPrenom" type="text" name="prenom" required="required" value="${utilisateur.prenom}">
			<label for="impPrenom">Prénom :</label>
		</div>
		<div class="input-field col s6">
			<input id="impEmail" type="text" name="email" required="required" value="${utilisateur.email}">
			<label for="impEmail">Email :</label>
		</div>
		<div class="input-field col s6">
			<input id="impTelephone" type="text" name="telephone" required="required" value="${utilisateur.telephone}">
			<label for="impTelephone">Téléphone :</label>
		</div>
		<div class="input-field col s6">
			<input id="impRue" type="text" name="rue" required="required" value="${utilisateur.rue}">
			<label for="impRue">Rue :</label>
		</div>
		<div class="input-field col s6">
			<input id="impCodePostal" type="text" name="codePostal" required="required"  value="${utilisateur.codePostal}">
			<label for="impCodePostal">Code postal :</label>
		</div>
		<div class="input-field col s6">
			<input id="impVille" type="text" name="ville" required="required" value="${utilisateur.ville}">
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
		<div>
		<core:if test="${user.administrateur == true }">
		<div class="input-field col s6">
    		<select>
      			<option name="ad" value="0" <core:if test="${utilisateur.administrateur == true}"> selected="selected" </core:if> >OUI<option>
      			<option name="ad" value="1" <core:if test="${utilisateur.administrateur == false}"> selected="selected" </core:if> >NON</option>
    		</select>
   			 <label>Si administrateur</label>
 		 </div>
 		 <div class="input-field col s6">
 		 	<input id="plusCre" type="number" name="credit" required="required" value="${utilisateur.credit}">
 		 	<label for="plusCre">Credit :</label>
 		 </div>
 		 </core:if>
		</div>
		<div class="input-field col s6">
			<input id="impCfPass" type="hidden" name="IdUtil" required="required" value="${utilisateur.numeroUtilisateur }">
		</div>
		<div class="center-align input-field col s12">
			<input class="waves-effect waves-light btn" id="impPseudo" type="submit" name="Btn" value="Modifier">
			<a class="waves-effect waves-light btn red" href="${pageContext.request.contextPath}/DeleteUtilisateur" value="${user.numeroUtilisateur}">Supprimer mon compte</a>
			<core:if test="${user.administrateur == true }">
				<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}/Profil">Annuler</a>
			</core:if>
			<core:if test="${user.administrateur == false }">
				<a class="waves-effect waves-light btn" href="${pageContext.request.contextPath}">Annuler</a>
			</core:if>	
		</div>
	</form>
</div>
<script>
$(document).ready(function(){
    $('select').formSelect();
  });

</script>
		