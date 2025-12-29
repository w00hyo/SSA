<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("CRLF", "\r\n"); %>
<jsp:include page="include/header.jsp" flush="false"/>

<div class="container mt-5">
	<div class="row">
		<div class="col-md-12">
			<h1 class="mt-4 mb-2">
			수정하기
			</h1>
			<form method="post">
			<input type="hidden" name="_method" value="put"/>
<!-- 수정 할때 잊지말자 히든을 -->
<input type="hidden" name="blogContSeq" value="${blogCont.BLG_CONT_SEQ }"/>
				<div class="form-group">
<label class="form-label">글번호</label>
<p>${blogCont.BLG_CONT_SEQ}</p>
				</div>

				<div class="form-group">
<label class="form-label">컨텐츠 제목</label>
<input type="text" name="title" class="form-control"
value="${blogCont.TITLE}"/>
				</div>
				
				<div class="form-group">
					<label class="form-label">본문</label>
<textarea rows="10" name="contBdy" class="form-control">${blogCont.CONT_BDY}</textarea>
				</div>

<div class="form-group">
    <label class="form-label">입력일</label>
    <p>
<fmt:formatDate value="${blogCont.INSERT_DT}" pattern="yyyy.MM.dd HH:mm:ss"/>
    </p>
</div>
				
<div class="d-flex justify-content-end mt-4 mb-2">
<input type="submit" name="create" value="컨텐츠수정" class="btn btn-lg btn-outline-success"/>
</div>
			
			</form>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" flush="false"/>