package com.rest_jpa.security;

import com.rest_jpa.entity.RefreshToken;
import com.rest_jpa.entity.User;
import com.rest_jpa.entity.to.*;
import com.rest_jpa.servise.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthenticationRequestDto requestDto) {
        String userName = requestDto.getUserName();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
        User user = userService.findByUserName(userName);

        String token = jwtTokenProvider.createToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        Set<String> roles = user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(token, refreshToken.getToken(), user.getId(),
                user.getUserName(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseTO> registerUser(@RequestBody AuthenticationRequestDto requestDto) {
        User user = new User();
        user.setUserName(requestDto.getUserName());
        user.setPassword(requestDto.getPassword());

        User registerUser = userService.register(user);
        return ResponseEntity.ok(new UserResponseTO(registerUser));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);
        refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String accessToken = jwtTokenProvider.createToken(user);
        return ResponseEntity.ok(new TokenRefreshResponse(accessToken, requestRefreshToken));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        refreshTokenService.deleteByUserId(jwtUser.getId());
        return ResponseEntity.ok().build();
    }
}
