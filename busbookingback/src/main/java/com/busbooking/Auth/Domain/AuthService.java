package com.busbooking.Auth.Domain;

public interface AuthService {

    void save(UsersEntity usersEntity);

    void deleteByEmail(String email);

    UsersEntity getByEmail(String email);


}
