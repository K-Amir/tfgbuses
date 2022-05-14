package com.busbooking.Auth.Application;


import com.busbooking.Auth.Application.Dto.AppUserInputDto;
import com.busbooking.Auth.Application.Dto.AppUserOutputDto;
import com.busbooking.Auth.Domain.UsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Auth.Domain.UsersMapper;
import com.busbooking.Auth.Infrastructure.JwtUtil;
import com.busbooking.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<SuccessDto> registerNewAdminUser(@RequestBody @Valid AppUserInputDto appUserInputDto) {

        UsersEntity user = UsersMapper.MAP.appUserInputToUserEntity(appUserInputDto);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setAdmin(true);

        authService.save(user);

        return SuccessDto.send("User created successfully");
    }

    @DeleteMapping("{email}")
    public ResponseEntity<SuccessDto> deleteUserByEmail(@PathVariable String email) {
        this.authService.deleteByEmail(email);
        return SuccessDto.send("Use deleted successfully");
    }

    @PostMapping("app")
    public ResponseEntity<?> registerNewAppUser(@RequestBody @Valid AppUserInputDto appUserInputDto) {
        UsersEntity user = UsersMapper.MAP.appUserInputToUserEntity(appUserInputDto);

        user.setAdmin(false);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        authService.save(user);

        return SuccessDto.send("User created successfully");
    }


    @GetMapping("admin")
    public ResponseEntity<?> getAllUsers() {
        var admins = authService.getAllAdmins();

        List<AppUserOutputDto> output = new ArrayList<>();

        admins.forEach(x -> output.add(UsersMapper.MAP.userEntityToAppUserOutputDto(x)));

        return ResponseEntity.ok(output);
    }


    @GetMapping("token/{token}")
    public ResponseEntity<?> getUserByToken(@PathVariable String token) {
        String subject = jwtUtil.getSubject(token);

        UsersEntity user = authService.getByEmail(subject);

        AppUserOutputDto appUserOutputDto = UsersMapper.MAP.userEntityToAppUserOutputDto(user);

        return ResponseEntity.ok().body(appUserOutputDto);
    }
}
