package port.sm.erp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
/*이 출처는 허용할께 CORS에 참뜻
implements WebMvcConfigurer (인터셉터,포멧터,CORS등)을 커스터마이징 할수 있음
*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//CORS허용을 적용할 요청 경로패턴
        .allowedOriginPatterns("http://localhost:*")//허용할 프론트 출처
.allowedMethods("GET","POST","PUT","DELETE","OPTIONS")//허용할 http메서드 목록
.allowedHeaders("*")//프론트에서 보내는 요청중에 어떤걸 허용할지 Authorization, 
//Content-type, X-requested-with
.allowCredentials(true);
//쿠키/세션/인증정보 포함 요청을 허용할지       
/*
이게 true면, 프론트 axios/fetch 쪽에서도 같이 설정해야 쿠키가 오감:
axios: withCredentials: true
fetch: credentials: 'include'
⚠️ 중요 포인트: 이걸 켜면 응답에 
Access-Control-Allow-Credentials: true가 붙고, 
브라우저가 쿠키를 주고받을 수 있게 됨.
세션 로그인 쓰는 경우 거의 필수.
*/       
        
        
        
        
        
        
    }
}
