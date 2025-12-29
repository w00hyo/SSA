<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%
String id = request.getParameter("id");
if(id == null || id.trim().equals("")){
	response.sendRedirect("products.jsp");
	return;
}
%>