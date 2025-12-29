<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/resources/include/header.jsp" %>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="resources/js/member.js"></script>
  <link rel="stylesheet" href="/resources/css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>join</title>
</head>
<body>
<div class="container">
   <div class="row">
   <div class="col-md-2"></div>
      <div class="col-md-8">
			<form action="" method="POST">
			<fieldset>
			<legend class="mt-3 mb-3">회원가입</legend>
			
			<div class="mb-3 mt-3">
			<label for="id" class="form-label">ID</label>
			<input type="text" class="form-control" name="memberId">
						<span class="id_input_re_1">사용가능한 아이디 입니다</span>
			<span class="id_input_re_2">아이디가 이미 존재합니다</span>
			<span class="final_id_ck">아이디가 이미 존재합니다</span>
			</div>

			
			<div class="mb-3 mt-3">
			<label for="password" class="form-label">PW</label>
			
			<input type="password" class="form-control" name="memberPw">
			<span class="final_pw_ck">비밀번호를 입력해 주세요</span>
			</div>
			
			<div class="mb-3 mt-3">
			<label for="password" class="form-label">RE PW</label>
			<input type="password" class="form-control" name="memberPw">
			<span class="final_pw_ck">비밀번호를 확인을 입력해 주세요</span>
			<span class="pwck_input_re_1">비밀번호가 일치합니다</span>
			<span class="pwck_input_re_2">비밀번호가 일치하지 않습니다</span>
			</div>
			
			<div class="mb-3 mt-3">
			<label for="name" class="form-label">name</label>
			<input type="text" class="form-control" name="memberName">
			<span class="final_name_ck">이름을 입력해 주세요</span>
			</div>
			
			<div class="mb-3 mt-3">
				<div class="input-group">
					<label for="email" class="form-label">email</label>
					<input type="email" class="form-control" name="memberMail">
					<div id="mail_check_input_box_false">
					<input class="mail_check_input" disabled="disabled">
					<button class="btn btn-success" id="mail_check_button">인증번호 전송</button>
					</div>
				</div>
			<span class="final_mail_ck">이메일을 입력해 주세요</span>
			</div>
			
			<div class="mb-3 mt-3 input-group">
			<label for="address" class="input-group-text">address</label>
			<input type="text" class="form-control" name="memberAddr1">
			<button class="btn btn-primary" onclick="execution_daum_address()">주소찾기</button>
			</div>
			
			<div class="mb-3 mt-3 input-group">
			<label for="address" class="input-group-text">address</label>
			<input type="text" class="form-control" name="memberAddr2">
			</div>
			
			<div class="mb-3 mt-3 input-group">
			<label for="address" class="input-group-text">address</label>
			<input type="text" class="form-control" name="memberAddr3">
			</div>
			<span class="final_name_ck">주소를 입력해 주세요</span>
			
		    <div class="mb-3 mt-3">
			<input type="text" class="form-control" name="email">
			</div>
			
			</fieldset>
			
			<div class="d-flex justify-content-end mt-3 mb-3">
			<input type="submit" class="btn btn-primary" value="가입하기" />
			</div>
						
			</form>
      </div>
      <div class="col-md-2"></div>
   </div>
</div>

</body>
</html>