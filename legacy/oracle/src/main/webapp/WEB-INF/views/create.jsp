<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="include/header.jsp" flush="false"/>

<div class="container mt-5">
	<div class="row">
		<div class="col-md-12">
			<h1 class="mt-4 mb-2">
			쓰기
			</h1>
			<form method="post">
			
				<div class="form-group">
					<label class="form-label">컨텐츠 제목</label>
					<input 
					type="text" 
					name="title" 
					class="form-control"
					placeholder="제목을 적어 주세요"
					/>			
				</div>
				
				<div class="form-group">
					<label class="form-label">본문</label>
<textarea rows="10" name="content_body" class="form-control">글을 적어보아요</textarea>		
				</div>
				
				<div class="d-flex justify-content-end mt-4 mb-2">
				<input type="submit" name="create" value="쓰기" class="btn btn-lg btn-outline-success"/>
				</div>
			
			</form>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" flush="false"/>