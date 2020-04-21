<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="WEB-INF/css/Mycss.css" rel="stylesheet">
<jsp:include page="../templete/header.jsp"></jsp:include>
 <c:if test = "${Enchere.noUtilisateur == 0 && now < Article.dateFinEncheres}">
<h3 class="center-align">Détail vente</h3>
</c:if>
 <c:if test = "${Enchere.noUtilisateur == userSession.numeroUtilisateur && now > Article.dateFinEncheres}">
<h3 class="center-align">Vous avez remporté la vente</h3>
</c:if>
<c:if test = "${Enchere.noUtilisateur != userSession.numeroUtilisateur && now > Article.dateFinEncheres}">
<h3 class="center-align">${Enchere.noUtilisateur} a remporté l'enchere</h3>
</c:if>
    <div class="row">

      <div class="col s12">
 
 
          
  <div class="col s3">
         <img class="materialboxed" width="250" height="250" src="https://cdn.discordapp.com/emojis/602470070475554846.png?v=1">
      </div>

  <div class="col s9"><form action="${pageContext.request.contextPath}/DetailEnchere" method="post">
  <label class="lbltxt">${Article.nomArticle}</label><br/>
<label class="lbltxt">Description : ${Article.description}</label><br/>

 <c:if test = "${Enchere.noUtilisateur == 0 && now < Article.dateFinEncheres}">
<label class="lbltxt">Catégorie : ${Article.categorie.libelle}</label><br/>
</c:if>
<c:if test = "${Enchere.montant_enchere == 0 }">
<label class="lbltxt">Meilleur offre : aucune </label><br/>
</c:if>
<c:if test = "${Enchere.montant_enchere > 0 }">
<label class="lbltxt">Meilleur offre : ${Enchere.montant_enchere} par ${Enchere.utilisateur.pseudo} </label><br/>
</c:if>

 <label class="lbltxt">Mise à prix : ${Article.miseAPrix}</label><br/>
  <c:if test = "${Enchere.noUtilisateur != userSession.numeroUtilisateur || now < Article.dateFinEncheres}">
  <label class="lbltxt">Fin de l'enchere: ${Article.dateFinEncheres}</label><br/> </c:if>
<label>Retrait : </label><br/>
<%-- <label>Retrait : ${Article.retrait.rue}</label><br/> --%>
<%-- <label> ${Article.retrait.code_postale}  ${Article.retrait.ville}</label> --%>

<label >Vendeur : ${Article.utilisateur.pseudo} </label> <br/>
 <c:if test = "${Enchere.noUtilisateur == 0 && now < Article.dateFinEncheres}">
<label for="credit">Ma Proposition </label>

<input type="number" id="credit" name="credit"
       min="${Enchere.montant_enchere}" max="${userSession.credit}" >
       <input type="submit" value="Enchérir" class="waves-effect waves-light btn-large">
  
</c:if>
<c:if test = "${Enchere.noUtilisateur == userSession.numeroUtilisateur && now > Article.dateFinEncheres}">
<label >Tel : ${Article.utilisateur.telephone} </label><br/>
<input type="submit" value="Back" class="waves-effect waves-light btn-large">
</c:if>
<c:if test = "${Enchere.noUtilisateur != userSession.numeroUtilisateur && now > Article.dateFinEncheres}">
<input type="submit" value="Retrait effectué" class="waves-effect waves-light btn-large">
</c:if>

   </form>
      </div>

</div>
   </div>
