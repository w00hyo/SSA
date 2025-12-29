<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="./resources/js/validation.js"></script>
<title>상품등록페이지</title>
</head>
<body>
<fmt:setLocale value='<%=request.getParameter("language") %>'/>
<fmt:bundle basename="resourceBundle.message">
<jsp:include page="include/menu.jsp"></jsp:include>
<div class="container">
   <div class="row mt-5">
      <div class="col-md-12">
		<h1>
		<fmt:message key="title"/>
		</h1>
      </div>
   </div>
</div>

<div class="container">

<div class="d-flex justify-content-end">
<a href="?language=ko" class="btn btn-primary">korean</a><a href="?language=en" class="mx-3 btn btn-primary">English</a>
</div>

	<div class="row mt-5">
		<div class="col-md-12">
			<form name="newProduct" action="./processAddProduct.jsp" method="post" enctype="multipart/form-data">
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
						<fmt:message key="bookID"/>					
					</label>
					<div class="col-sm-10">
					 <input type="text" id="bookID" name="bookID" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
						<fmt:message key="bookName"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="bookName" name="bookName" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="unitPrice"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="unitPrice" name="unitPrice" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="description"/>	
					</label>
					<div class="col-sm-10">
					 <textarea name="description" rows="5" class="form-control"></textarea>
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="author"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="author" name="author" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="publisher"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="publisher" name="publisher" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="category"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="category" name="category" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="unitsInStock"/>	
					</label>
					<div class="col-sm-10">
					 <input type="text" id="unitsInStock" name="unitsInStock" class="form-control">
					</div>
				</div>
				
				<div class="form-group row mt-5">
					<label class="col-sm-2">
					<fmt:message key="condition"/>
					</label>
					<div class="col-sm-10">
					 <input type="radio"  name="condition" value="New">
					 <fmt:message key="condition_NEW"/>	
					 <input type="radio"  name="condition" value="Old">
					 <fmt:message key="condition_OLD"/>
					 <input type="radio"  name="condition" value="E-Book">
					 <fmt:message key="condition_EBOOK"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2">
						<fmt:message key="bookImage"/>
					</label>
					
					<div class="col-sm-10">
					 <input type="file" name="productImage" class="form-control">
					</div>
				</div>
				
			    <div class="form-group row mt-5 mb-5">
					<div class="d-flex justify-content-end">
					 <input 
					 type="submit"  
					 class="btn btn-primary" 
					 value="<fmt:message key="button"/>" 
					 onclick="CheckAddProduct()"
					 >
					</div>
				</div>
			
			</form>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp"></jsp:include>
</fmt:bundle>
</body>
</html>