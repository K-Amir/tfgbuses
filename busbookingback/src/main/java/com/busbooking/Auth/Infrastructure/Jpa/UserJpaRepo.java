package com.busbooking.Auth.Infrastructure.Jpa;

import com.busbooking.Auth.Domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<UsersEntity, String> {
}
