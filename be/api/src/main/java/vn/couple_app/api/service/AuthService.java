package vn.couple_app.api.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import vn.couple_app.api.config.JwtCustom;
import vn.couple_app.api.dto.request.IntrospectRequest;
import vn.couple_app.api.dto.response.IntrospectResponse;
import vn.couple_app.api.dto.request.LoginRequest;
import vn.couple_app.api.dto.request.RegisterRequest;
import vn.couple_app.api.dto.response.LoginResponse;
import vn.couple_app.api.dto.response.RegisterResponse;
import vn.couple_app.api.entity.Account;
import vn.couple_app.api.entity.User;
import vn.couple_app.api.exception.AppException;
import vn.couple_app.api.exception.ErrorCode;
import vn.couple_app.api.repository.AccountRepository;
import vn.couple_app.api.repository.UserRepository;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    final JwtCustom jwtCustom;
    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    public RegisterResponse register(RegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);

        Account newAccount = Account
            .builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

        User newUser = User
            .builder()
            .account(newAccount)
            .build();

        newUser = userRepository.save(newUser);

        return RegisterResponse
            .builder()
            .token(jwtCustom.encode(newUser))
            .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) {
        boolean isValid = true;
        try {
            jwtCustom.decode(request.getToken());
        } catch (JwtException jwtException) {
            isValid = false;
        }
        return IntrospectResponse
            .builder()
            .isValid(isValid)
            .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByAccountEmail(loginRequest.getEmail())
            .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getAccount().getPassword()))
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        return LoginResponse
            .builder()
            .token(jwtCustom.encode(user))
            .build();
    }
}
