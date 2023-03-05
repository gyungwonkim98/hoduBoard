package com.example.hoduBoard.model;

import com.example.hoduBoard.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 *  사용자 관리 Value Object 클래스
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long userNo;
    private String userId;
    private String userPwd;
    private String userNm;
    private String mobile;
    private String email;
    private String useYn;
    private Role auth;
    private Date regDt;
    private String delYn;

    @Builder
    public void UserDTO(String userId, String userPwd, String userNm, String email, String mobile, Role auth) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNm = userNm;
        this.email = email;
        this.mobile = mobile;
        this.auth = auth;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .userNm(userNm)
                .userPwd(new BCryptPasswordEncoder().encode(userPwd))
                .email(email)
                .mobile(mobile)
                .auth(auth)
                .build();
    }
}




