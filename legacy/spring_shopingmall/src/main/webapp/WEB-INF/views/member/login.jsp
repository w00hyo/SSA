<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="/resources/css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login</title>
</head>
<%@ include file="/resources/include/header.jsp" %>
<body class="bg-gradient-primary">
${path}
<div class="container">
   <div class="row">
   <div class="col-md-3"></div>
   
      <div class="col-md-6">
		<div class="card o-hidden border-0 shadow-lg my-5 p-5" >
		
			<h1 class="display-3">Mall</h1>
	
			<div class="input-group mt-3 mb-3">
				<label class="input-group-text w10">id</label>
				<input type="text" class="form-control w90" placeholder="id">
			</div>
	
			<div class="input-group mt-3 mb-3">
				<label class="input-group-text w10">pw</label>
				<input type="password" class="form-control w90" placeholder="pw">
			</div>
	
			<div class="d-flex justify-content-end">
				<input type="submit" class="btn btn-primary" value="login">
			</div>
		</div> 

      </div>
      
      <div class="col-md-3"></div>
   </div>
</div>

</body>
</html>