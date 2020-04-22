<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="WEB-INF/pages/templete/header.jsp"></jsp:include>
<div>
	<h3 class="center-align">Listes des enchères</h3>
	<core:if test="${message != null || !empty message }">
		<h4 class="center-align">${message}</h4>
	</core:if>
</div>