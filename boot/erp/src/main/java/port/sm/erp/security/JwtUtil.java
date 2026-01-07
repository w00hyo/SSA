package port.sm.erp.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/*
만들고(generateToken)
검증하고(validateToken)
안에 들어 있는 정보를 꺼내는 역활(getClaims)를 합니다
*/
@Component//spring이 자동으로 객체(Bean)로 만들어서 관리하게 해줍니다
//JWT로그인한 사용자를 증명하는 전자신분증
public class JwtUtil {

    /* ⚠️ 최소 32바이트 이상
    private static final String SECRET =
        "this-is-very-long-secret-key-at-least-32-bytes";

    private final Key key = Keys.hmacShaKeyFor(
        SECRET.getBytes(StandardCharsets.UTF_8)
    );*/

    @Value("${app.jwt.secret}")//토큰을 위조하지 못하게 잠그는 비밀번호
    private String jwtSecret;
    
    @Value("${app.jwt.expiration-ms}")//토큰 유효시간 3600000 -> 1시간
    private long jwtExpiration;
    
    private Key getKey() {//비밀키 생성[jwt를 서명 할때 사용하는 키 생성]
    	return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    	//HS256알고리즘을 사용하므로 비밀키 하나로 서명 + 검증을 합니다
    	//이토큰은 내가 만든게 맞다라고 증명하는 도장
    }
    
    //토큰생성 로그인 성공후 JWT문자열 하나를 만들어서 반환
    public String generateToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email)//토큰 주인 이메일
				//add 20260106                
                .claim("uid", userId)//사용자 id를 uid라는 이름으로 저장
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //토큰이 언제 만들어 졌는지
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                //토큰만료 시간 , 지금 + 1시간
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                //비밀키로 서명 누군가 토큰을 바꾸면 검증 실패
                .compact();
        //JWT를 문자열로 완성
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsInVpZCI6MSwiaWF0IjoxNj... 

    }
    
    //토큰에서 클레임 파싱 토큰 내용 꺼내기 Claims
    public Map<String, Object> getClaims(String token){//JWT안에 들어있는 정보를 정보를 꺼내는 메서드
    	return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    	//이키로 만든 코큰인지 확인 만료되지 않았는지 검사 getBody() JWT안의 데이터 반환
    }
    
    //유효성 검사 검증하는 부분인데 토큰이 변조됨 만료됨 비밀키가 다르고 형식이 잘못됨
    public boolean validateToken(String token) {
    	try {
    		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    		//이키로 만든 코큰인지 확인 만료되지 않았는지 검사
    		return true;
    	}catch (Exception e) {
    		return false;
    	}
    }
    
}
