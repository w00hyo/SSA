package port.sm.erp.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String result;  // "success" / "fail"
    private String token;   // JWT
}