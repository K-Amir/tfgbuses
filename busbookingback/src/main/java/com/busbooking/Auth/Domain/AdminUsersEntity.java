package com.busbooking.Auth.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "admin_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUsersEntity {
    @Id
    private String email;
    private String password;
}
