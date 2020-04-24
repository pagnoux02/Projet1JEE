<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="WEB-INF/css/François.css" rel="stylesheet">
<title>Liste Encheres</title>
<jsp:include page="../templete/header.jsp"></jsp:include>
<body>
	<div class="row">
		<div class="col s12">
			<h6>Flitres :</h6>
		</div>
		<form>
			<div class="col s6">
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
			</div>
			<div class="col s6 center ">
				<button class="btn waves-effect waves-light btn-large" type="submit"
					name="recherche">recherche</button>
			</div>
		</form>
	</div>
	<br />
	<c:if test="${listArt != null || !empty listArt }">
	<c:forEach var="article"  items="${listArt}">
	<div>
		<div class="container">
		<div class="">
			<a href="${pageContext.request.contextPath}/Connection">
			<div class="col s12 m4 testCol">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
						<span class="card-title upper cardTitle">${article.nomArticle}</span>
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
		<h3 class="center-align">il y a aucun Article aux enchères</h3>
	</c:if>
	
	<script> $(document).ready(function(){
    $('select').formSelect();
  });
 </script>
</body>
</html>