<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="WEB-INF/css/François.css" rel="stylesheet">
<jsp:include page="../templete/header.jsp"></jsp:include>
<body>
	<c:if test="${ now == 0 || now == 2 }">
		<h3 class="center-align">Détail vente</h3>
	</c:if>
	<c:if
		test="${Enchere.noUtilisateur == userSession.numeroUtilisateur && now == 1}">
		<h3 class="center-align">Vous avez remporté la vente</h3>
	</c:if>
	<c:if
		test="${Enchere.noUtilisateur != userSession.numeroUtilisateur && now == 1}">
		<h3 class="center-align">${Enchere.utilisateur.pseudo} a remporté
			l'enchere</h3>

	</c:if>
	<div class="row">

		<div class="col s12">



			<div class="col s3">
				<img class="materialboxed" width="250" height="250"
					src="https://cdn.discordapp.com/emojis/602470070475554846.png?v=1">
			</div>

			<div class="col s9">
				<form
					action="${pageContext.request.contextPath}/DetailEnchere?idArtEnch=${Article.noArticle}&idUser=${userSession.numeroUtilisateur}"
					method="post">
					<label class="lbltxt">${Article.nomArticle}</label><br /> <label
						class="lbltxt"><span class="infoBlack">Description
							:</span> ${Article.description}</label><br />

					<c:if test="${now == 0}">
						<label class="lbltxt"><span class="infoBlack">Catégorie
								:</span> ${Article.categorie.libelle}</label>
						<br />
					</c:if>
					<c:if test="${Enchere.montant_enchere == 0 }">
						<label class="lbltxt"><span class="infoBlack">Meilleur
								offre :</span> aucune </label>
						<br />
					</c:if>
					<c:if test="${Enchere.montant_enchere > 0 }">
						<label class="lbltxt"><span class="infoBlack">Meilleur
								offre :</span> ${Enchere.montant_enchere}<c:if
								test="${Enchere.noUtilisateur != userSession.numeroUtilisateur || now == 0}">
								<a class="duBlanc"
									href="${pageContext.request.contextPath}/MonProfil?idprof=${Article.utilisateur.numeroUtilisateur}">par
									${Enchere.utilisateur.pseudo} </a>
							</c:if></label>
						<br />
					</c:if>

					<label class="lbltxt"><span class="infoBlack">Mise à
							prix :</span> ${Article.miseAPrix}</label><br />
					 <label class="lbltxt"><span class="infoBlack">Retrait :</span> ${Article.retrait.rue}</label><br /> 
					 <label class="lbltxt"> ${Article.retrait.code_postale} ${Article.retrait.ville}</label><br /> 
					 <c:if
						test="${Enchere.noUtilisateur != userSession.numeroUtilisateur || now == 0}">
						<label class="lbltxt"><span class="infoBlack">Fin
								de l'enchere:</span> ${Article.dateFinEncheres}</label>
						<br />
					</c:if>
					 
					 <label class="lbltxt"><span
						class="infoBlack">Vendeur :</span> ${Article.utilisateur.pseudo} </label>
					<br />
					
					<c:if
						test="${Enchere.noUtilisateur == userSession.numeroUtilisateur && now == 1}">
						<label class="lbltxt"><span class="infoBlack">Tel :</span>
							${Article.utilisateur.telephone} </label>
						<br />

						<a href="${pageContext.request.contextPath}/ListEnchereCo"
							class="waves-effect waves-light btn-large">Back</a>
					</c:if>


					<c:if test="${now == 0}">
						<label class="lbltxt" for="credit"><span class="infoBlack">Ma
								Proposition </span> </label>

						<input type="number" id="credit" name="credit"
							min="${Enchere.montant_enchere}" max="${userSession.credit}"
							value="${Enchere.montant_enchere}">

						<button class="btn waves-effect waves-light" type="submit"
							name="btnValidation" value="Enchérir">Enchérir</button>

					</c:if>
					<c:if test="${now == 2 }">
						<label class="lbltxt"><span class="infoBlack">Debut
								de l'enchere :</span> ${Article.dateDebutEncheres} </label>
						<br>
					</c:if>
					<c:if
						test="${Enchere.noUtilisateur != Article.utilisateur.numeroUtilisateur && Enchere.noUtilisateur != userSession.numeroUtilisateur && now == 1 || now == 2 }">
						<a href="${pageContext.request.contextPath}/ListEnchereCo"
							class="waves-effect waves-light btn-large">Back</a>
					</c:if>
					<c:if
						test="${Enchere.noUtilisateur == Article.utilisateur.numeroUtilisateur && now == 1}">
						<input type="submit" name="btnValidation" value="Retrait effectué"
							class="waves-effect waves-light btn-large">
					</c:if>


				</form>
			</div>

		</div>
	</div>
</body>