package com.busbooking.Auth.Application;


import com.busbooking.Auth.Domain.AdminUsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Auth.Infrastructure.JwtUtil;
import com.busbooking.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa/v0/auth")
public record AuthController(
        UserDetailsService userDetailsService,
        JwtUtil jwtUtil,
        AuthenticationManager authenticationManager,
        AuthService authService,
        BCryptPasswordEncoder bCryptPasswordEncoder
) {


    @GetMapping("token")
    public ResponseEntity<?> getJwtToken(@RequestHeader("user") String user, @RequestHeader("password") String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
        } catch (AuthenticationException e) {
            throw new Exception("Credentials are incorrect");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user);
        String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(jwtToken);

    }

    @PostMapping
    public ResponseEntity<SuccessDto> registerNewAdminUser(@RequestBody AdminUsersEntity adminUsersEntity) {

        adminUsersEntity.setPassword(bCryptPasswordEncoder.encode(adminUsersEntity.getPassword()));

        authService.save(adminUsersEntity);

        return SuccessDto.send("User created successfully");
    }


    @GetMapping("token/{token}")
    public ResponseEntity<?> checkToken(@PathVariable String token) {
        String subject =  jwtUtil.getSubject(token);
        return ResponseEntity.ok().body(true);
    }
}
