<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%
String id = request.getParameter("id");
if(id == null || id.trim().equals("")){
	response.sendRedirect("view.jsp");
	return;
}
ProductRepository dao = ProductRepository.getInstance();
//상품데이터 접근 클래스의 기본생성자에 대한 객체변수instance를 얻어오도록 작성합니다

Product book = dao.getProductById(id);
if(book == null){
	response.sendRedirect("exceptionNoProductId.jsp");
}
//상품아이디를 호출해 주고 getProductById로 불러온 아이디를 Product객체에 저장을 합니다
//만약 상품아이디에 대한 정보가 없으면 예외처리 페이지로 이동합니다
ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
//장바구니인 cartlist에 등록된 모든 상품을 가져오도록 세션 내장객체의 getAttribute()메소드를 작성합니다
//product객체를 생성합니다
Product goodsQnt = new Product();
for  (int i =0; i < cartList.size(); i++){//상품리스트 하나씩 출력하기
goodsQnt = cartList.get(i);
if(goodsQnt.getBookID().equals(id)){
	cartList.remove(goodsQnt);
}
}
response.sendRedirect("cart.jsp");
%>