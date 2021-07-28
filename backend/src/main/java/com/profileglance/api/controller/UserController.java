package com.profileglance.api.controller;


import com.profileglance.api.request.UserLoginPostReq;
import com.profileglance.api.service.UserService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.config.JwtTokenProvider;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "유저 API", tags = {"User"})
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    public ResponseEntity<? extends BaseResponseBody> join(@RequestBody User user) {

        userService.createUser(user);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 로그인
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public String login(@RequestBody UserLoginPostReq userLoginPostReq) {
        User member = userRepository.findByUserEmail(userLoginPostReq.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userLoginPostReq.getUser_password(), member.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUserEmail());
    }
}