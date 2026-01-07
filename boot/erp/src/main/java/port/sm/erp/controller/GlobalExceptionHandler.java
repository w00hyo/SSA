package port.sm.erp.controller;

//익셉션 핸들러를 잘못 활용하면 에러를 잡을수가 없음

/*import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        e.printStackTrace(); // ✅ 무조건 콘솔에 원인 찍힘
        return ResponseEntity.status(500).body("ERROR: " + e.getClass().getName() + " / " + e.getMessage());
    }
}*/



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 401/403/404 등 ResponseStatusException은 원래 상태코드 그대로 보내기
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatus(ResponseStatusException e) {
        // reason이 null일 수 있으니 방어
        String msg = (e.getReason() != null) ? e.getReason() : e.getMessage();
        return ResponseEntity.status(e.getStatus()).body(msg);
    }

    // ✅ 그 외 진짜 서버 에러만 500으로 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        e.printStackTrace(); // 원인 콘솔 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("SERVER_ERROR: " + e.getClass().getName() + " / " + e.getMessage());
    }
}
