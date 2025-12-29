function includeHTML(){
    var z, i, elmnt, file, xhttp; //각각선언

	//1.변수 z는 도큐먼트에서 모든 태그명을 찾고
    z = document.getElementsByTagName("*");
	
	//2.모든 html요소를 하나씩 검사(반복문)
	for (i=0; i< z.length; i++){
		elmnt = z[i];
		//3.현재 요소에서 'w3-include-html'속성을 찾음
			file = elmnt.getAttribute("w3-include-html")
			
			//4.만약에 속성이 있다면
			if(file){
				
				//5. xmlhttprequest객체 생성(서버에 요청을 보내기 위해소)
				xhttp = new XMLHttpRequest();
				
				//6.서버 응답이 바뀔때 마다 실행되는 함수지정
				xhttp.onreadystatechange = function(){
				//7.요청이 [데이터를 전부 받은 상태]완료되는지 확인	
				if(this.readyState == 4){
					
					//8) 서버가 정상적으로 파일을 찾앗다면 
					if(this.status == 200){
						elmnt.innerHTML = this.responseText;
						//읽어온 html을 해당 요소안에 넣음
					}
					
					//9) 서버에서 파일을 찾지 못하면
					if(this.status == 404){
						elmnt.innerHTML = "page not found";
					}
					
					//10)파일을 삽입후에 속성제거(중복실행 방지)
					elmnt.removeAttribute("w3-include-html");
					
					//11.) 노파심에 또다른 포함 요소가 있는지 재귀 호출
					includeHTML(); 
				}
			}	
			//자바스크립트에 서 중요한 순위 객체, 12) 비동기 get요청 준비 (파일 경로 사용)
			xhttp.open("GET", file, true);
			//
			xhttp.send();//13)요청보내기
			
			//14.여기서 함수 종료(한번에 한 파일만 처리)
			return;
	}
	
	
		
/*
readyState
0 객체만 생성(open매서드가 호출되지 않음) uninitialized
1 open매서드가 호출     loading
2 send매서드 호출 status가 헤더에 도착하지 않은 상태 loaded
3 데이터의 일부를 받은 상태 interactive
4 데이터를 전부 받은상태 complete

status 서버로부터의 응답 상태
200 :ok 요청성공
403 : 접근 거부
404 : 페이지 없음
500 : 서버오류 발생
 */			
}		
			
			
			
			
			

	}
    

