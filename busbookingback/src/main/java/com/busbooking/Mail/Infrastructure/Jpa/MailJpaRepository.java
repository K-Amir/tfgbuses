package com.busbooking.Mail.Infrastructure.Jpa;

import com.busbooking.Mail.Domain.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailJpaRepository extends JpaRepository<MailEntity, Integer> {
}
