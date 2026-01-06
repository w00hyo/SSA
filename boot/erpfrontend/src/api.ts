import axios from "axios";
//프론트리액트에서 백앤드스프링으로 GET, post 등을 보내는 것

const api = axios.create({
    baseURL: "http://localhost:8888", timeout:5000,
    withCredentials: true // <--- MUST match allowCredentials(true)
});
//매번 이주소를 http://localhost:8888 이렇게 길게 ㅅ면 불편 인스탄스로 하면 편하다
//baseURL: 백앤드 서버주소 

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
if(token && config.headers){
        config.headers.Authorization = `Bearer ${token}`;

    }
    return config;
})
export default api;
/*
//브라우저 저장소에서 토큰값을 꺼냅니다//토큰이 있을때만 아래 작업을 합니다
//A ?? B 는 “A가 null 또는 undefined면 B를 쓰겠다”는 뜻.
//config.headers가 없으면 {}(빈 객체)로 만들어라 있으면 그냥 기존 헤더를 유지해라
//HTTP 요청 헤더에 Authorization: Bearer 토큰값
// JWT 인증에서 흔히 쓰는 규칙이야. 백엔드(스프링)가 보통 이렇게 검사해:
//Authorization 헤더가 있는지 Bearer 로 시작하는지 뒤에 있는 토큰이 유효한지
//수정된 설정(config)을 “다시 axios에게 돌려줘야”
//axios가 그 설정대로 요청을 전송할 수 있다
*/


