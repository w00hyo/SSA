$(function(){
	$(".join_button").click(function(){
		$("#join_form").attr("action", "/member/join");
		$("#join_form").submit();
	});
});

//인증번호 이메일 전송
$(".mail_check_button").click(function(){
	var email = $(".mail_input").val(); //입력한 이메일
	$.ajax({
		type:"GET",
		url:"mailCheck?email=" + email
	});
});