package com.wisecrm.crmapiservice.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;

//@Component
//@RequiredArgsConstructor
//public class SystemClientFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//
//        if (request.getServletPath().contains("/api/v1/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        final String clientId  = request.getParameter("ClientId");
//
//        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        filterChain.doFilter(request,response);
//
//    }
//}
