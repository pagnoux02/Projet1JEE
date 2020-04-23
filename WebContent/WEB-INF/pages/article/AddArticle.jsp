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
	<core:if test="${updArticle.categorie == null || empty updArticle.categorie}">
	<form class="col s12"
		action="${pageContext.request.contextPath}/VendreActicle"
		method="post">
	</core:if>
	<core:if test="${updArticle.categorie != null && !empty updArticle.categorie}">
	<form class="col s12"
		action="${pageContext.request.contextPath}/ModifArticle"
		method="post">
	</core:if>

		<div class="input-field col s6">
			<input id="idArt" type="text" name="article" required="required" value="${updArticle.nomArticle}">
			<label for="idArt">Article :</label>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<textarea id="textarea1" class="materialize-textarea"
					data-lenght="300"  required="required" name="des">${updArticle.description}</textarea>
				<label for="textarea1">Description :</label>
			</div>
		</div>
		<div class="input-field col s6">
			<select id="testForm" name="cat" required="required">
				<option value="" disabled selected>Choisir catégorie</option>
				<core:forEach var="cat" items="${ListCat}">
					<core:if test="${updArticle.categorie == null || empty updArticle.categorie}">
						<option value="${cat.noCategorie}">${cat.libelle}</option>
					</core:if>
					<core:if test="${updArticle.categorie != null && !empty updArticle.categorie}">
						<core:if test="${cat.noCategorie == updArticle.categorie.noCategorie}">
							<option value="${cat.noCategorie}" selected="selected">${cat.libelle}</option>						
						</core:if>
						<core:if test="${cat.noCategorie != updArticle.categorie.noCategorie}">
							<option value="${cat.noCategorie}">${cat.libelle}</option>						
						</core:if>
					</core:if>
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
			<input id="NumPrix" type="number" name="miseAprix" value="${updArticle.miseAPrix}"
				required="required"> <label for="NumPrix">Mise à
				prix :</label>
		</div>
		
		<core:if test="${updArticle.categorie != null && !empty updArticle.categorie}">
			<div class="input-field col s6">
			<input id="NumPrix" type="number" name="NumPrixVentes" value="${updArticle.prixVente}"
				required="required"> <label for="NumPrixVentes">prix vente :</label>
		</div>
		<input id="idArtf" type="hidden" name="idArtMdf" value="${updArticle.noArticle}">
		<input id="etatVente" type="hidden" name="etatVen" value="${updArticle.etatVente}">
		</core:if>

		<div class="input-field col s6">
			<input id="DDEnchere" type="date" class="datepicker" name="debutEnch"
				required="required" value="${updArticle.dateDebutEncheres}"> <label for="DDEnchere">Début de
				l'enchère :</label>
		</div>

		<div class="input-field col s6">
			<input id="FFEnchere" type="date" class="datepicker" name="finEnch"
				required="required" value="${updArticle.dateFinEncheres}"> <label for="FFEnchere">Fin de
				l'enchère :</label>
		</div>

		<div class="col s6">
			<div class="lieuRetrais">
				<div class="card-content">
					<span class="titleLieuRetrais">Lieu de Retrait</span>
					<core:if test="${updArticle.categorie == null || empty updArticle.categorie}">
						<div class="input-field">
							<label for="test2">Rue :</label>
							<input id="test2" type="text" name="rue" required="required" value="${user.rue}">
						</div>
						<div class="input-field">	 
							<label for="test1">Code postal :</label>
							<input id="test1" type="text" name="codePostal" required="required" value="${user.codePostal}">
						</div>
						<div class="input-field">	
							<label for="test">Ville :</label>
							<input id="test" type="text" name="ville" required="required" value="${user.ville}">
						</div>
					</core:if>
					<core:if test="${updArticle.categorie != null && !empty updArticle.categorie}">
						<div class="input-field">
							<label for="test2">Rue :</label>
							<input id="test2" type="text" name="rue" required="required" value="${updArticle.retrait.rue}">
						</div>
						<div class="input-field">	 
							<label for="test1">Code postal :</label>
							<input id="test1" type="text" name="codePostal" required="required" value="${updArticle.retrait.code_postale}">
						</div>
						<div class="input-field">	
							<label for="test">Ville :</label>
							<input id="test" type="text" name="ville" required="required" value="${updArticle.retrait.ville}">
						</div>
					</core:if>	
				</div>
			</div>
		</div>

		<div class="input-field col s12">
		<core:if test="${updArticle.categorie == null || empty updArticle.categorie}">
			<input class="waves-effect waves-light btn" type="submit"
				value="Enregistrer">
				<a class="waves-effect waves-light btn red" href="${pageContext.request.contextPath}/">Annuler</a>
		</core:if>
		<core:if test="${updArticle.categorie != null && !empty updArticle.categorie}">
			<input class="waves-effect waves-light btn" type="submit"
				value="Mise a jour">
			 <a class="waves-effect waves-light btn red" href="${pageContext.request.contextPath}/">Annuler la mise à jour</a>
			 <a class="waves-effect waves-light btn blue" href="${pageContext.request.contextPath}/DeleteArticle?idDel=${updArticle.noArticle}">Annuler la vente</a>
		</core:if>		
		</div>
	</form>
</div>
<script>
	$('input#input_text, textarea#textarea1').characterCounter();

	$('select').formSelect();
</script>