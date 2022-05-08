package com.busbooking.Auth.Domain;

public interface AuthService {

    void save(AdminUsersEntity adminUsersEntity);

    void deleteByEmail(String email);

    AdminUsersEntity getByEmail(String email);


}
