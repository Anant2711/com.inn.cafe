package com.inn.cafe.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private CustomerUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;
        Claims claims = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("manilla ")) {
            token = authorizationHeader.substring(8);
            userName = JwtUtil.extractUsername(token);
            claims = JwtUtil.extractAllClaims(token);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = service.loadUserByUsername(userName);
                if (userDetails != null && JwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    // Handle invalid token or user not found
                    // You can choose to return an error response or perform other actions
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // Method to check if the current user is an admin based on JWT claims
    public boolean isAdmin(Claims claims) {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    // Method to check if the current user is a regular user based on JWT claims
    public boolean isUser(Claims claims) {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    // Method to get the current user from JWT claims
    public String getCurrentUser(Claims claims) {
        return claims.getSubject();
        //git
    }
}



