<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("CRLF", "\r\n"); %>
<!-- 
JSP의 스크립틀릿 블록입니다
pageContext jsp에서 제공하는 내장객체(implicit object)중 하나입니다
이는 jsp 페이지의 실행 컨텍스트를 나타내며  jsp 페이지에서 스코프에 데이터를
저장하거나 가져올수 있습니다

setAttribute("CRLF", "\r\n");
Carriage Return + Line Feed

\r : carriage return (커서를 줄의 맨 앞으로 이동)
\n : line feed (다음 줄로 이동)

이 JSP 페이지에서 CRLF라는 이름으로 줄바꿈 문자 "\r\n"을 사용할 수 있게 해라.”
 -->
<jsp:include page="include/header.jsp" flush="false"/>

<div class="container mt-5">
	<div class="row">
		<div class="col-md-12">
			<h1 class="mt-4 mb-2">
			read
			</h1>

			
				<div class="form-group">
					<label class="form-label">번호</label>
					<p>${blogCont.BLG_CONT_SEQ}</p>	
				</div>
				
				<div class="form-group">
					<label class="form-label">제목</label>
					<p>${blogCont.TITLE}</p>	
				</div>
				
				<div class="form-group">
					<label class="form-label">본문</label>
<p>${fn:replace(blogCont.CONT_BDY, CRLF, '<br/>') }</p>
				</div>
<form method="post" action="/delete">

    <div class="d-flex justify-content-end mt-4 mb-2">
    <input type="hidden" name="_method" value="delete"/>
    <input type="hidden" name="blogContSeq"
    value="${blogCont.BLG_CONT_SEQ}"/>
    
    <div class="btn-group">
    	<a href="/list" class="btn btn-outline-secondary">
    	목록으로
    	</a>
    	<a href="/edit/${blogContSeq}" class="btn btn-outline-secondary">
    	수정하기</a>
	    <input type="submit"
	    name="delete_button"
	    value="삭제"
	    class="btn btn-outline-danger"/>
	    </div>
	</div>		
</form>

<div class="d-flex">
<input type="text" id="cmtBdy" class="form-control"/>
<input type="password" id="tmpPw" class="form-control"/>
<input type="button" 
id="btn_comment_add" 
value="댓글쓰기"
class="btn btn-outline-secondary"
/>
</div>
<div id="comment_list">
</div>
<input type="hidden" id="blgContSeq" value="${blogCont.BLG_CONT_SEQ}">
<!--  jquery는 웹브라우저에서 사용되는 프론트엔드 자바스크립트 라이브러리 사용하는 이유 비동기 호출을 위해서 리액트나 뷰가 활성화 되기 전에 크로스브라우징 이슈때문에 얘 말고는 답이 없던시기가 있어서  -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>
	$("#btn_comment_add").click(function(){	
		let blgContSeq = $("#blgContSeq").val(); // hidden input에서 읽기
		let cmtBdy = $("#cmtBdy").val();
		let tmpPw = $("#tmpPw").val();

		let send_data = {};//cho gi hwa
		if (tmpPw ==""){
			send_data = {
			  "blgContSeq":blgContSeq,
			  "cmtBdy":cmtBdy
			};
		}else{
			send_data = {
			  "blgContSeq":blgContSeq,
			  "cmtBdy":cmtBdy,
			  "tmpPw":tmpPw
			};
		}
		$.post("/comment/add", send_data, function(data){
			//console.log(data);
			render_comment(data.cmtBdy, data.seqBlgCmt);
		});
	});
	
	//add
	function render_comment(cmtBdy, seqBlgCmt, tmpPw){
let append_val = '<p><a href="/comment/delete/{seqBlgCmt}" class="mt-3 mb-2">{cmtBdy}</a></p>'
.replace("{seqBlgCmt}", seqBlgCmt).replace("{cmtBdy}",cmtBdy);
$("#comment_list").append(append_val);
	}
	
function init_comment(){
	$.get("/comment/list/${blogCont.BLG_CONT_SEQ}", function(data){
		for (comment of data) {
			render_comment(comment.cmtBdy, comment.seqBlgCmt);
		}
	});
}	

$(document).ready(function(){
	init_comment();
});
	
</script>		
</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" flush="false"/>