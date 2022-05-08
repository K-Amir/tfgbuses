package com.busbooking.Auth.Infrastructure.Jpa;

import com.busbooking.Auth.Domain.AdminUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<AdminUsersEntity, String> {
}
