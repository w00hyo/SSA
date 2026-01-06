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

@Component
public class JwtUtil {

    // ⚠️ 최소 32바이트 이상
    private static final String SECRET =
        "this-is-very-long-secret-key-at-least-32-bytes";

    private final Key key = Keys.hmacShaKeyFor(
        SECRET.getBytes(StandardCharsets.UTF_8)
    );

    @Value("${app.jwt.seceret}")//토큰을 위조하지 못하게 잠그는 비밀번호
    private String jwtSecret;
    
    @Value("${app.jwt.expiration-ms}")//토큰 유효시간
    private long jwtExpiration;
    
    //토큰생성
    public String generateToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email)//토큰 주인 이메일
				//add 20260106                
                .claim("uid", userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
    
    //토큰에서 클레임 파싱 토큰 내용 꺼내기
    public Map<String, Object> getClaims(String token){
    	return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
    
    //유효성 검사
    public boolean validateToken(String token) {
    	try {
    		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    		return true;
    	}catch (Exception e) {
    		return false;
    	}
    }
    
}
