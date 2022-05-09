package com.busbooking.Auth.Infrastructure;

import com.busbooking.Auth.Domain.UsersEntity;
import com.busbooking.Auth.Domain.AuthService;
import com.busbooking.Auth.Infrastructure.Jpa.UserJpaRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public record AuthServiceImpl(UserJpaRepo userRepo) implements AuthService {

    @Override
    public void save(UsersEntity usersEntity) {
        userRepo.save(usersEntity);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepo.deleteById(email);
    }

    @Override
    public UsersEntity getByEmail(String email) {
        return userRepo.findById(email).orElseThrow(() -> new EntityNotFoundException("Not found user with the provided email"));
    }


}
