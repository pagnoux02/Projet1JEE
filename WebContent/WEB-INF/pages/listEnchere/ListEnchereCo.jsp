<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templete/header.jsp"></jsp:include>
<title>Liste des enchère</title>
<body>
	<h3 class="center-align">Liste des enchère</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
	<div class="row">
		<div class="col s6">
			<h6>Flitres :</h6>
			<div class="input-field col s12">
          		<i class="material-icons prefix">search</i>
          		<input type="text" id="text-filter" >
          		<label for="text-filter">Le nom de l'article contient</label>
        	</div>
		</div>
	</div>
</body>
</html>