package com.busbooking.Auth.Infrastructure;

import com.busbooking.Auth.Domain.AdminUsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Auth.Infrastructure.Jpa.UserJpaRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public record AuthServiceImpl(UserJpaRepo userRepo) implements AuthService {

    @Override
    public void save(AdminUsersEntity adminUsersEntity) {
        userRepo.save(adminUsersEntity);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepo.deleteById(email);
    }

    @Override
    public AdminUsersEntity getByEmail(String email) {
        return userRepo.findById(email).orElseThrow(() -> new EntityNotFoundException("Not found user with the provided email"));
    }


}
