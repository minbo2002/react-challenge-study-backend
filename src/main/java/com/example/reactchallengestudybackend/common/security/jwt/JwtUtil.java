package com.example.reactchallengestudybackend.common.security.jwt;

import com.example.reactchallengestudybackend.common.exception.CustomException;
import com.example.reactchallengestudybackend.common.exception.ErrorCode;
import com.example.reactchallengestudybackend.common.security.dto.PrincipalDetails;
import com.example.reactchallengestudybackend.domain.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;           // 설정 파일에서 jwt 토큰에 서명하는데 사용할 secret key를 가져온다.

    @Value("${jwt.accessTokenExpirationPeriod}")
    private int jwtExpirationInMs;      // 설정 파일에서 jwt 토큰의 유효기간을 가져온다.


    // Token 생성
    public String generateToken(User user) {

        String username = user.getName();

        Date currentDate = new Date();
//        Date expireDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));    // 7 days
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);    // 7 days
        log.info("jwtExpirationInMs: {}", jwtExpirationInMs);
        log.info("expireDate: " + expireDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    // Token 생성
    /*
    public String generateToken(PrincipalDetails principalDetails) {  // User user 대신 PrincipalDetails principalDetails 사용하면 에러발생

        String username = principalDetails.getUsername();  // user 대신 principalDetails 사용하면 에러발생

        Date currentDate = new Date();
//        Date expireDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));    // 7 days
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);    // 7 days
        log.info("jwtExpirationInMs: {}", jwtExpirationInMs);
        log.info("expireDate: " + expireDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    */

    // Token으로부터 username을 가져오기
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // JWT Token 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex){
            throw new CustomException(ErrorCode.INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException ex) {
            throw new CustomException(ErrorCode.INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new CustomException(ErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new CustomException(ErrorCode.JWT_CLAIMS_EMPTY);
        }
    }
}
