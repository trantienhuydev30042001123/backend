package com.huy.webdoan.security.jwt;

import com.huy.webdoan.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class jwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(jwtProvider.class);
    private String jwtSecret = "huy.huy@gmail.com.acbvsfuasfuafbasbfasufbaufbaubaubfausfbaiafbai";
    private int jwtExpiration = 328941893;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch(SignatureException e){
            logger.error("Ivalid JWT sinature -> Message: {}", e);
        } catch (MalformedJwtException e){
            logger.error("the token invalid format -> Message: {}", e);
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty -> Message: {}", e);
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName ;
    }
}
