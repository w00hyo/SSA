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
<title>nopage</title>
</head>
<body>
<jsp:include page="include/menu.jsp"></jsp:include>

<div class="container">
   <div class="row">
      <div class="col-md-12">
		<h2 class="mt-3 mb-3 alert alert-primary">요청페이지를 찾을수 없습니다</h1>
      </div>
   </div>
</div>

<div class="container">
<p><%=request.getRequestURL() %>?<%=request.getQueryString()%></p>
<p><a href="products.jsp" class="btn btn-primary">상품목록&raquo;</a></p>
</div>







<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>