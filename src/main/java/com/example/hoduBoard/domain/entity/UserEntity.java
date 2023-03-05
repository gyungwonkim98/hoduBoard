package com.example.hoduBoard.domain.entity;

import com.example.hoduBoard.model.Role;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *  사용자 관리 Value Object 클래스
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="TB_USER") // DB 테이블과 매핑
@ToString
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Column(name="USER_NO")
    private Long userNo;

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

    @Column(name="USE_YN", length = 1)
    private String useYn;

    @Column(name="AUTH",nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Role auth;
//
//    @Temporal(value = TemporalType.TIMESTAMP)
//    @Column(name="AUTH",nullable = false, length = 5)
//    private Date regDt;

    @Column(name="DEL_YN", length = 1)
    private String delYn;

    //@Transient는 특정 변수를 영속 필드에서 제외할 때 사용한다.
    //테이블에 존재하지 않는 변수이기 때문에 @Transient 처리하였다.
//    @Transient
//    private String passwordCheck;

    @Builder
    public UserEntity(String userId, String userPwd, String userNm, String mobile, String email, String useYn, Role auth, String delYn) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNm = userNm;
        this.mobile = mobile;
        this.email = email;
        this.auth = auth;
    }


    // spring security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authArr = new ArrayList<GrantedAuthority>();
        authArr.add(new SimpleGrantedAuthority(auth.toString()));
        return authArr;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}




