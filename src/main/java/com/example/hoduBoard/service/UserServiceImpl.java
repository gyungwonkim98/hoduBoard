package com.example.hoduBoard.service;

import com.example.hoduBoard.domain.entity.UserEntity;
import com.example.hoduBoard.domain.repository.UserRepository;
import com.example.hoduBoard.model.Role;
import com.example.hoduBoard.model.UserDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserId(loginId)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다"));

        return User.builder()
                .username(userEntity.getUserId())
                .password(passwordEncoder.encode(userEntity.getUserPwd()))
                .roles(userEntity.getAuth().value())
                .build();
    }


    @Override
    public Long createUser(UserDTO userDto) {
        if (userRepository.findByMobile(userDto.getMobile()) != null){
            return null;
        }
//        String role = userDto.getAuth().value();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);

        UserEntity userEntity = userDto.toEntity();
        userRepository.save(userEntity);
        return userEntity.getUserNo();

    }
}