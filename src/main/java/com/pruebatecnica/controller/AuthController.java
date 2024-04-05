package com.pruebatecnica.controller;

import com.pruebatecnica.DTO.AuthRequestsDTO;
import com.pruebatecnica.util.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestsDTO authRequestsDTO) {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestsDTO.getUser(),
                            authRequestsDTO.getPassword()
                    )
            );

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestsDTO.getUser());

            String jwt = this.jwtService.generateToken(userDetails);

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }

    @PostMapping("/role")
    public ResponseEntity<?> role(@RequestBody AuthRequestsDTO authRequestsDTO) {

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestsDTO.getUser(),
                            authRequestsDTO.getPassword()
                    )
            );

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestsDTO.getUser());

            return ResponseEntity.ok(userDetails.getAuthorities());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }
}
