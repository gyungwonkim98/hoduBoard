package com.example.hoduBoard.config;

import com.example.hoduBoard.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 된다.

public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private final AuthenticationFailureHandler customFailureHandler;


    public SecurityConfig(AuthenticationFailureHandler customFailureHandler) {
        this.customFailureHandler = customFailureHandler;
    }


    // 암호화 방식 빈(Bean) 생성
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    // 인증을 무시할 경로 설정
    @Override
    public void configure(WebSecurity web) {
        //이미지,자바스크립트,css 디렉토리 보안 설정
        web.ignoring().antMatchers("/resources/**");
//        web.ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
    }

    // 인증이 필요한 경로 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 비활성화 - Spring Security에서는 csrf 토큰 없이 요청하면 해당 요청을 막기 때문에 잠깐 비활성화.
        http.authorizeRequests()
                // role에 따라 들어 갈 수 있는 주소 설정
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                // role이 관리자일 경우 들어갈 수 있는 주소
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                // 인증만 되면 들어갈 수 있는 주소 - (개발 끝난 후 설정 예정)
                //.antMatchers("/user/**").authenticated()
                //.antMatchers("/login**", "/web-resources/**", "/actuator/**").permitAll()
                // 기타 url은 모두 허용
                .anyRequest().permitAll();
                //.anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login.do") // 로그인 페이지 경로 설정
                .loginProcessingUrl("/loginCheck.do") // 해당 주소로 오는 요청을 낚아채서 로그인을 진행
                .defaultSuccessUrl("/main.do") // 로그인 성공 후 리다이렉트 경로
                .failureHandler(customFailureHandler);//로그인 실패 시 처리하는 핸들러 등록.
        http.logout()
                .logoutSuccessUrl("/login.do") //로그아웃시 이동되는 페이지
                .invalidateHttpSession(true); // 로그아웃시 HTTP 세션을 초기화
    }

}