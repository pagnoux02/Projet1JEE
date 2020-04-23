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
	<div class="row">

		<div class="col s6">
			<div class="col s2"></div>
			<div class="card blue-grey darken-1 col s8">
				<div class="col s4"></div>

				<div class="col s8">
					<div class="card-content white-text">
						<span class="card-title">Nom Article </span>
						<p>Prix : X points</p>
						<p>Fin de l'enchère : Date</p>
						<br>
						<p>
							Vendeur : <a>Pseudo</a>
						</p>

					</div>
				</div>
			</div>
			<div class="col s2"></div>
		</div>

		<div class="col s6">
			<div class="col s2"></div>
			<div class="card blue-grey darken-1 col s8">
				<div class="col s4"></div>

				<div class="col s8">
					<div class="card-content white-text">
						<span class="card-title">Nom Article </span>
						<p>Prix : X points</p>
						<p>Fin de l'enchère : Date</p>
						<br>
						<p>
							Vendeur : <a>Pseudo</a>
						</p>

					</div>
				</div>
			</div>
			<div class="col s2"></div>
		</div>
	</div>
	<script> $(document).ready(function(){
    $('select').formSelect();
  });
 </script>
</body>
</html>