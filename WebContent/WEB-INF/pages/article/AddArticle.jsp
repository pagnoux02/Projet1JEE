<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<h3 class="center-align">Vendre un Aticle</h3>
<core:if test="${message != null || !empty message }">
	<h4 class="center-align">${message}</h4>
</core:if>
<div style="width: 80%;" class="row center-align">
	<form class="col s12"
		action="${pageContext.request.contextPath}/VendreActicle"
		method="post">

		<div class="input-field col s6">
			<input id="idArt" type="text" name="article" required="required">
			<label for="idArt">Article :</label>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<textarea id="textarea1" class="materialize-textarea"
					data-lenght="300" required="required" name="des"></textarea>
				<label for="textarea1">Description :</label>
			</div>
		</div>
		<div class="input-field col s6">
			<select id="testForm" name="cat" required="required">
				<option value="" disabled selected>Choisir catégorie</option>
				<core:forEach var="cat" items="${ListCat}">
					<option value="${cat.noCategorie}">${cat.libelle}</option>
				</core:forEach>
			</select> <label for="testForm">Choisir catégorie</label>
		</div>

		<div class="file-field input-field col s6">
			<div class="btn">
				<span>File</span> <input type="file">
			</div>
			<div class="file-path-wrapper col s11">
				<input class="file-path validate" type="text" name="file">
			</div>
		</div>

		<div class="input-field col s6">
			<input id="NumPrix" type="number" name="miseAprix" value="0"
				required="required"> <label for="NumPrix">Mise à
				prix :</label>
		</div>

		<div class="input-field col s6">
			<input id="DDEnchere" type="date" class="datepicker" name="debutEnch"
				required="required"> <label for="DDEnchere">Début de
				l'enchère :</label>
		</div>

		<div class="input-field col s6">
			<input id="FFEnchere" type="date" class="datepicker" name="finEnch"
				required="required"> <label for="FFEnchere">Fin de
				l'enchère :</label>
		</div>

		<div class="col s6">
			<div class="lieuRetrais">
				<div class="card-content">
					<span class="titleLieuRetrais">Lieu de Retrait</span>
					<div class="input-field">
						<label for="idArt">Rue :</label>
						<input id="idArt" type="text" name="rue" required="required" value="${user.rue}">
					</div>
					<div class="input-field">	 
						<label for="idArt">Code postal :</label>
						<input id="idArt" type="text" name="codePostal" required="required" value="${user.codePostal}">
					</div>
					<div class="input-field">	
						<label for="idArt">Ville :</label>
						<input id="idArt" type="text" name="ville" required="required" value="${user.ville}">
					</div>	
				</div>
			</div>
		</div>

		<div class="input-field col s12">
			<input class="waves-effect waves-light btn" type="submit"
				value="Enregistrer"> <a
				class="waves-effect waves-light btn red"
				href="${pageContext.request.contextPath}/">Annuler</a>
		</div>
	</form>
</div>
<script>
	$('input#input_text, textarea#textarea1').characterCounter();

	$('select').formSelect();
</script>