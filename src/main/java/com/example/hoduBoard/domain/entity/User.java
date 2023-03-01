package com.example.hoduBoard.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *  사용자 관리 Value Object 클래스
 */

@Getter
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="TB_USER") // DB 테이블과 매핑
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Column(name="USER_NO")
    private String userNo;

    @Column(name="USER_ID", nullable = false, unique = true, length = 20)
    private String userId;

    @Column(name="USER_PWD",nullable = false, length = 64)
    private String userPwd;

    @Column(name="USER_NM",nullable = false, length = 100)
    private String userNm;

    @Column(name="MOBILE",nullable = false, length = 32)
    private String mobile;

    @Column(name="EMAIL",nullable = false, length = 64)
    private String email;

    @Column(name="USE_YN",nullable = false, length = 1)
    private String useYn;

    @Column(name="AUTH",nullable = false, length = 5)
    private String auth;
//
//    @Temporal(value = TemporalType.TIMESTAMP)
//    @Column(name="AUTH",nullable = false, length = 5)
//    private Date regDt;

    @Column(name="DEL_YN", nullable = false, length = 1)
    private String delYn;

    //@Transient는 특정 변수를 영속 필드에서 제외할 때 사용한다.
    //테이블에 존재하지 않는 변수이기 때문에 @Transient 처리하였다.
    @Transient
    private String passwordCheck;

}




