package com.busbooking.Auth.Infrastructure.Jpa;

import com.busbooking.Auth.Domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepo extends JpaRepository<UsersEntity, String> {

    List<UsersEntity> findAllByIsAdminIsTrue();

    void deleteByEmailIs(String email);
}
