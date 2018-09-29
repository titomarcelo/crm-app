<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <title>TMAO CRM</title>
  
  <%-- font --%>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <%-- css --%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/bootstrap-datepicker3.standalone.min.css" />
  <link rel="stylesheet" href="/resources/css/app.css">

  <%-- js --%>  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/bootstrap-datepicker.min.js"></script>
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
          <li class="active"><a href="/product/list">Products</a></li>
        </ul>
        <ul class="nav navbar-nav">
          <li class="active"><a href="/customer/list">Customers</a></li>
        </ul>
        <ul class="nav navbar-nav">
          <li class="active"><a href="/sale/crud">Sales</a></li>
        </ul>
        <ul class="nav navbar-nav">
	      <li class="dropdown">
	        <a class="dropdown-toggle active" data-toggle="dropdown" href="#">Reports</a>
	        <ul class="dropdown-menu">
	          <li><a href="/report/product">Products</a></li>
	          <li><a href="/report/customer">Customers</a></li>
	          <li><a href="/report/sale">Sales</a></li>
	        </ul>
	      </li>
        </ul>
        
        <a class="navbar-brand pull-right" href="/">TMAO CRM</a>
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
<div id="message">
    <div style="padding: 5px;">
        <div id="inner-message" class="alert alert-${msg.style}" role="alert">
		    <button type="button" class="close" data-dismiss="alert">x</button>
		    <c:out value="${msg.text}"></c:out>
        </div>
    </div>
</div>
</c:if>
