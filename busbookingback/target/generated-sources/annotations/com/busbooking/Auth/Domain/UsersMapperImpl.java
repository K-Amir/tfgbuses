package com.busbooking.Auth.Domain;

import com.busbooking.Auth.Application.Dto.AppUserInputDto;
import com.busbooking.Auth.Application.Dto.AppUserOutputDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:56:26+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class UsersMapperImpl implements UsersMapper {

    @Override
    public UsersEntity appUserInputToUserEntity(AppUserInputDto appUserInputDto) {
        if ( appUserInputDto == null ) {
            return null;
        }

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setEmail( appUserInputDto.getEmail() );
        usersEntity.setPassword( appUserInputDto.getPassword() );
        usersEntity.setName( appUserInputDto.getName() );
        usersEntity.setSurname( appUserInputDto.getSurname() );

        return usersEntity;
    }

    @Override
    public AppUserOutputDto userEntityToAppUserOutputDto(UsersEntity usersEntity) {
        if ( usersEntity == null ) {
            return null;
        }

        AppUserOutputDto appUserOutputDto = new AppUserOutputDto();

        appUserOutputDto.setEmail( usersEntity.getEmail() );
        appUserOutputDto.setName( usersEntity.getName() );
        appUserOutputDto.setSurname( usersEntity.getSurname() );

        return appUserOutputDto;
    }
}
