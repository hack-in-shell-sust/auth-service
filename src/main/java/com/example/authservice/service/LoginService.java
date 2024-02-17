package com.example.authservice.service;

import com.example.authservice.common.exceptions.InvalidRequestDataException;
import com.example.authservice.common.exceptions.RecordNotFoundException;
import com.example.authservice.common.utils.SessionIdUtil;
import com.example.authservice.data.entity.User;
import com.example.authservice.data.repository.UserRepository;
import com.example.authservice.domain.dto.LoginRequest;
import com.example.authservice.domain.dto.LoginResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService extends BaseService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ID_TOKEN_SECRET = "IR3piPE7-0pqMRgSYimQd0NcMHsvT1aF2t72bk9H9TA";


    public LoginResponse loginUser(LoginRequest loginRequest) {
        //TODO: validate user details
        //TODO: if user is valid, create session
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty())
            throw new RecordNotFoundException("User does not exists");
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()))
            throw new InvalidRequestDataException("Credentials do not match");
        //TODO: create an ID token in redis to store user claims
        final String sessionId = SessionIdUtil.generateSessionId();
        final String idToken = createIDToken(sessionId, user.get());


        //TODO: create a access token to give to user
        //TODO: give user the access token
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(idToken);
        return loginResponse;
    }

    public void logoutUser() {
        //TODO: remove ID/session token from redis
        //TODO: update user is as logged out
        //TODO: remove/invalidate access token from user
        //TODO: remove user session
        //TODO: Logout user
    }


    private String createIDToken(String sessionId, User user) {
        try {
            JWSSigner signer = new MACSigner(ID_TOKEN_SECRET);

            // Add user-specific claims to the ID token
            JWTClaimsSet claimsSet = new Builder()
                    .subject(sessionId)
                    .expirationTime(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                    .claim("token_type", "access_token")
                    .claim("session_id", sessionId)
                    .claim("email", user.getEmail())
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            return null;
        }
    }
}
