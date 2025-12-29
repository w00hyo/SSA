package com.react.mvc.dto;

import lombok.Data;

@Data
/*
@Data 안에 포함된 기능
@Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor 
(final 필드 생성자)
따라서 별도의 getter/setter가 필요 없음!
*/
public class LoginRequest {
private String email, password;

}
