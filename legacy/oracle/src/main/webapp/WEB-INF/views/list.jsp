<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("CRLF", "\r\n"); %>

<jsp:include page="include/header.jsp" flush="false"/>


<c:if test="${not empty blogListResponseVOList }">
<!-- c:if 는 jstl에서 조건을 나타냅니다 test에 조건을 넣고  
not empty 비어있지 않다면 이라는 의미로 스칼라 타입[프로그래밍에서 "하나의 값만 담는 단일형 
데이터타입{더 이상 쪼갤수 없는 기본 단위의 데이터 타입}"]이라면 != null 구문과 동일하고
리스트 타입이라면 비어있지 않다 (length > 0)
-->
<div class="container mt-5">
	<div class="row">
		<div class="col-md-12">
			<h1 class="mt-4 mb-2">
			list
			</h1>
<form>
	<div class="d-flex mt-4 mb-2">
	
		<input type="text" 
		name="search" 
		value="${blogListRequestVO.search}"
		class="form-control"/>
		
		<input type="submit" name="search_button" value="검색" class="btn btn-outline-secondary"/>
	</div>
</form>
			
<table class="table table-striped">
	<colgroup>
		<col width="5%"/>
		<col width="70%"/>
		<col width="15%"/>
		<col width="10%"/>
	</colgroup>
	<thead>
		<tr class="text-center"><!-- table row -->
			<th>글번호</th><th>제목</th><th>입력일</th><th>수정</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="blogListResponseVO" items="${blogListResponseVOList}">
	<tr>
		<td class="text-center">${blogListResponseVO.blgContSeq}</td>
		<td>
			<a href="/read/${blogListResponseVO.blgContSeq}"
			class="text-decoration-none text-dark"
			>
			${blogListResponseVO.title}
			</a>
		</td>
		<td class="text-center">${blogListResponseVO.insertDtFormat}</td>
		<td class="text-center"><a href="/edit/${blogListResponseVO.blgContSeq}" 
		class="btn btn-outline-warning">수정</a></td>
	</tr>
</c:forEach>	
	</tbody>
</table>
		
		</div>
	</div>
</div>
</c:if>
<!-- 검색결과가 없을 경우 대비 -->
<!--  test
${blogListResponseVOList }-->
<c:if test="${empty blogListResponseVOList}">
<div class="mt-5">
<strong class="mt-200">검색 결과가 없습니다</strong>
</div>
</c:if>

<style>
.mt-200{margin-top:200px !important;}
</style>











<jsp:include page="include/footer.jsp" flush="false"/>