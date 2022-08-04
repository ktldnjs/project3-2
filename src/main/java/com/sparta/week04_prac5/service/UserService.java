package com.sparta.week04_prac5.service;

import com.sparta.week04_prac5.dto.SignupRequestDto;
import com.sparta.week04_prac5.model.MemoUser;
import com.sparta.week04_prac5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<MemoUser> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

//        String passwordCheck = requestDto.getPasswordCheck();


        MemoUser user = new MemoUser(username, password);
        userRepository.save(user);
    }
}