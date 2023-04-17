<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="pt_br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Meus Projetos</title>
<%-- 	<script src="<c:url value="/static/node_modules/jquery/dist/jquery.min.js"/>"></script> --%>
	<link href="<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>"	rel="stylesheet">
	
	<style type="text/css">
		.btn-icon {
			border: unset;
    		background: unset;
		}
		._nav {
			background: linear-gradient(150deg, #732ef9db 0%, #732ef938 100%)
		}
		.navbar-brand {
			color: #ffff
		}
	</style>
</head>
<nav class="navbar mb-1 navbar-expand-lg navbar-light _nav">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Meus Projetos</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarTogglerDemo02"
			aria-controls="navbarTogglerDemo02" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>
</nav>

<div class="container">
	<c:forEach items="${msgs}" var="message">
		<div class="float-end w-50 alert ${message.type} alert-dismissible" role="alert">
			<div>${message.msg}</div>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:forEach>
</div>