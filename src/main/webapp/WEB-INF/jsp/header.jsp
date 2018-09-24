<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <title>Bangal&ocirc; Restaurante</title>
  
  <%-- font --%>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <%-- css --%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.standalone.min.css" />
  <link rel="stylesheet" href="/resources/css/app.css">

  <%-- js --%>  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
  <script src="/resources/js/jquery.mask.min.js"></script>
  <script src="/resources/js/app.js"></script>
</head>

<body>

    <%-- Navigation --%>
    <nav class="navbar-app bg-secondary fixed-top text-uppercase">
      <div class="container-fluid">
        <ul class="nav navbar-nav">
          <li class="active"><a href="/">Home</a></li>
        </ul>
        <ul class="nav navbar-nav">
          <li class="active"><a href="/product/list">Produtos</a></li>
        </ul>
        <ul class="nav navbar-nav">
          <li class="active"><a href="/customer/list">Clientes</a></li>
        </ul>
        <ul class="nav navbar-nav">
          <li class="active"><a href="/sale/crud">Vendas</a></li>
        </ul>
        <a class="navbar-brand pull-right" href="/">Bangal&ocirc; Restaurante</a>
      </div>
    </nav>
    
    <%-- Header --%>
    <header class="masthead bg-primary text-white text-center">
      <div class="container">
        <h2 class="font-weight-light mb-0">Customer Relationship Management</h2>
      </div>
    </header>
	
<div class="container">

<c:if test="${not empty msg}">
  </br>
  <div class="alert alert-success" id="success-alert">
    <button type="button" class="close" data-dismiss="alert">x</button>
    <c:out value="${msg}"></c:out>
  </div>
</c:if>
