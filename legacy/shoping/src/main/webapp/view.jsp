<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "dto.Product" %>
<jsp:useBean id="productDAO" class="dao.ProductRepository" scope="session"/>
<%@ page errorPage="exceptionNoProductId.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="js/addto.js"></script>
<title>상품상세페이지</title>
</head>
<body>
<jsp:include page="include/menu.jsp"></jsp:include>

<div class="container">
   <div class="row">
      <div class="col-md-12">
		<h5>상품정보</h5>
      </div>
   </div>
</div>
<%
String id = request.getParameter("id");
Product product = productDAO.getProductById(id);
%>
<div class="container">
	<div class="row">
		<div class="col-md-6">
		<img src="resources/upload/<%=product.getFilename()%>" class="mw-100 img-thumbnail"/>
		<h3><%=product.getBookName()%></h3>
		<p><%=product.getDescription()%></p>
		<p>
			<b>상품코드:</b>
			<span class="badge bg-danger">
			<%=product.getBookID()%>
			</span>
		</p>
		<p><b>저자 :</b><%=product.getAuthor()%></p>
		<p><b>출판사 :</b><%=product.getPublisher()%></p>
		<p><b>분류 :</b><%=product.getCategory()%></p>
		<p><b>재고 수 :</b><%=product.getUnitsInStock()%></p>
		<h4><%=product.getUnitPrice()%>원</h4>
		<form>
		<a href="#" class="btn btn-info" onclick="addToCart()">상품주문&raquo;</a>
		<a href="./cart.jsp" class="btn btn-warning">장바구니&raquo;</a>
		<a href="products.jsp" class="btn btn-secondary">상품목록&raquo;</a>
		</form>
		</div>	
	</div>
</div>










<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>