package com.busbooking.Auth.Domain;

import java.util.List;

public interface AuthService {

    void save(UsersEntity usersEntity);

    void deleteByEmail(String email);

    UsersEntity getByEmail(String email);

    List<UsersEntity> getAllAdmins();

    void deleteUser(String email);


}
