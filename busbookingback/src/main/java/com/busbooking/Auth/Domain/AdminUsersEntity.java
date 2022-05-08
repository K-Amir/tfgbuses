package com.busbooking.Auth.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "admin_users")
@Getter
@Setter
public class AdminUsersEntity {
    @Id
    private String email;
    private String password;
}
