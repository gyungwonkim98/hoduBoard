package com.example.hoduBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 암호화 방식 빈(Bean) 생성
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 비활성화
        http.authorizeRequests()
                // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/user/**").authenticated()
                // role이 관리자일 경우 들어갈 수 있는 주소
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                // 기타 url은 모두 허용
                .anyRequest().permitAll()
                .and()
                // 로그인 페이지 사용
                .formLogin()
                .loginPage("/login") // 로그인 페이지 경로 설정
                .loginProcessingUrl("/loginCheck") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .defaultSuccessUrl("/"); // 로그인 성공 후 기본적으로 리다이렉트되는 경로
    }
}