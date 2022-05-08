package com.busbooking.Auth.Infrastructure.Security;

import com.busbooking.Auth.Domain.AdminUsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record UserDetailsServiceImpl(AuthService authService) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminUsersEntity adminUsersEntity = authService.getByEmail(email);
        if (adminUsersEntity == null) {
            throw new UsernameNotFoundException("Not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new User(adminUsersEntity.getEmail(), adminUsersEntity.getPassword(), authorities);
    }
}
