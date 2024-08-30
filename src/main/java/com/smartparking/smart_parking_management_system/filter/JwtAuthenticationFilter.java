package com.smartparking.smart_parking_management_system.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.smartparking.smart_parking_management_system.service.CustomUserDetailsService;
import com.smartparking.smart_parking_management_system.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer ")){
           jwtToken=requestTokenHeader.substring(7);
           try{
            username=jwtUtil.getUsernameFromToken(jwtToken);
           }catch(IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
           }catch(ExpiredJwtException e){
            System.out.println("JWT Token has expired");
           }
        }
        else{
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);

           if(jwtUtil.validateToken(jwtToken, userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    

           }
        }
        filterChain.doFilter(request,response);


    }   
    
    
}