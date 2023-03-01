package com.example.hoduBoard.model.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *  사용자 관리 Value Object 클래스
 */

@Getter
@Setter
public class UserDTO {


    private String userNo;
    private String userId;
    private String userPwd;
    private String userNm;
    private String mobile;
    private String email;
    private String useYn;
    private String auth;
    private Date regDt;
    private String delYn;
//
//    @Transient
//    private String passwordCheck;

}




