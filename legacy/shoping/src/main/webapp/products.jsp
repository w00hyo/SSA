<%@ page import = 'dao.ProductRepository' %>
<%@ page import = "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dto.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>list</title>
</head>
<body>
<jsp:include page="include/menu.jsp"></jsp:include>

<div class="container">
   <div class="row">
      <div class="col-md-12">
		<h1 class="mt-3 mb-3">도서 목록</h1>
      </div>
   </div>
</div>
<%
ProductRepository dao = ProductRepository.getInstance();
ArrayList<Product>listOfProducts = dao.getAllProducts();
%>
<div class="container">
<div class="row">
<%
for (int i=0; i < listOfProducts.size(); i++){
Product book = listOfProducts.get(i);	
%>
<div class="col-md-3">
	<div class="text-center">
	<img src="resources/upload/<%=book.getFilename()%>" class="mw-100 img-thumbnail imgw"/>
	</div>
	<br>
	<h3 class="mt-3 mb-3 title">
	<%=book.getBookName() %>
	</h3>
	<p class="des"><%=book.getDescription()%></p>
	<p class="mt-5 d-flex justify-content-end"><%=book.getUnitPrice()%></p>
	<div class="d-flex justify-content-end mt-3 mb-3">
		<a href="./view.jsp?id=<%=book.getBookID()%>" class="btn btn-secondary">
		more &raquo;
		</a>
	</div>
</div>
<%} %>
</div>
</div>


<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>