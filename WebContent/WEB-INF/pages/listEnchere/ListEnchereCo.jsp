<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<title>Liste des ench�re</title>
<body>
	<h3 class="center-align">Liste des ench�re</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="row">
		<div class="col s12">
			<h6>Flitres :</h6>
		</div>
		<form>
			<div class="col s6 cole">
				<div class="input-field col s12">
	          		<i class="material-icons prefix">search</i>
	          		<input type="text" id="text-filter" >
	          		<label for="text-filter">Le nom de l'article contient</label>
	        	</div>
	        	<div class="col s12">
	        		<div class="col s3">
	        			<span><br/>Cat�gorie : </span>
	        		</div>
	        		<div class="input-field col s9">
	        			<select id="list-categorie">
	        				<option value="oui">oui</option>
	        				<option value="oui">oui</option>
	        				<option value="oui">oui</option>
	        			</select>
	        		</div>
	        	</div>
	        	<div class="col s12">
	        		<div class="col s6">
	        			<label>
	        				<input id="radAchat" class="with-gap" name="grp-achatVente" type="radio" checked/>
	        				<span>Achats</span>
	        			</label>
	        		</div>
	        		<div class="col s6">
	        			<label>
	        				<input id="radVente" class="with-gap" name="grp-achatVente" type="radio"/>
	        				<span>Mes ventes</span>
	        			</label>
	        		</div>
	        		<div id="divAchat" class="col s6">
	        			<label>
	        				<input class="filled-in" type="checkbox"/>
	        				<span>ench�res ouvertes</span>
	        			</label>
	        			<label>
	        				<input class="filled-in" type="checkbox"/>
	        				<span>mes ench�re en cours</span>
	        			</label>
	        			<label>
	        				<input class="filled-in" type="checkbox"/>
	        				<span>mes ench�re remport�es</span>
	        			</label>
	        		</div>
	        		<div id="divVente" class="col s6">
	        			<label>
	        				<input class="filled-in" type="checkbox" disabled/>
	        				<span>mes ventes en cours</span>
	        			</label>
	        			<label>
	        				<input class="filled-in" type="checkbox" disabled/>
	        				<span>ventes non d�but�es</span>
	        			</label>
	        			<label>
	        				<input class="filled-in" type="checkbox" disabled/>
	        				<span>ventes termin�es</span>
	        			</label>
	        		</div>
	        	</div>
			</div>
			<div class="col s6 center vertical-center">
				<button class="btn waves-effect waves-light btn-large" type="submit" name="recherche">recherche</button>
			</div>
		</form>
		<div class="col s12">
		
		</div>
	</div>
</body>
<script> $(document).ready(function(){
    $('select').formSelect();
    
    $('#radAchat').on('change',function() {
    	$("#divAchat *").prop('disabled',false);
    	$("#divVente *").prop('disabled',true);
    	$("#divVente *").prop('checked',false);
	});
	$('#radVente').on('change',function() {
    	$("#divAchat *").prop('disabled',true);
    	$("#divAchat *").prop('checked',false);
    	$("#divVente *").prop('disabled',false);
	});
  });
 </script>
</html>