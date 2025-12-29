<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ page import="java.util.*" %>

<%
request.setCharacterEncoding("UTF-8");

String filename="";
String realFolder ="D:\\dev\\workspace\\shoping\\src\\main\\webapp\\resources\\upload";
int maxSize = 5* 1024* 1024;	//최대 업로드 파일의 크기 5MG
String encType = "utf-8";	//인코딩 유형
MultipartRequest multi = new MultipartRequest(request, realFolder,
		maxSize, encType, new DefaultFileRenamePolicy());


String bookID = multi.getParameter("bookID");
String bookName = multi.getParameter("bookName");
String unitPrice = multi.getParameter("unitPrice");
String description = multi.getParameter("description");
String author = multi.getParameter("author");
String publisher = multi.getParameter("publisher");
String category = multi.getParameter("category");
String unitsInStock = multi.getParameter("unitsInStock");
String condition = multi.getParameter("condition");

Enumeration files = multi.getFileNames();
String fname = (String) files.nextElement();
String fileName = multi.getFilesystemName(fname);

Integer price;

if(unitPrice.isEmpty())
	price = 0;
else
	price = Integer.valueOf(unitPrice);
long stock;









%>