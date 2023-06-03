package com.huy.webdoan.security.jwt;

import com.huy.webdoan.security.userprincal.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class jwtTokenFilter extends OncePerRequestFilter {
    private static final Logger logger= LoggerFactory.getLogger(jwtTokenFilter.class);
    public static String CURRENT_USER = "";
    @Autowired
    private jwtProvider jwtProvider;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwt(request);
            if (token!=null && jwtProvider.validateToken(token)){
                String username = jwtProvider.getUsernameFromToken(token);
                CURRENT_USER = username;
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        } catch (Exception e){
            logger.error("Can't set user authentication -> Mesage: {}", e);
        }
        filterChain.doFilter(request,response);
    }
    private String getJwt(HttpServletRequest request){
        String autrHeader = request.getHeader("Authorization");
        if(autrHeader != null && autrHeader.startsWith("Bearer")){
            return autrHeader.replace("Bearer","");
        }
        return null;
    }
}
