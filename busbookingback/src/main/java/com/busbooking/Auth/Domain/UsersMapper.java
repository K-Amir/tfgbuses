package com.busbooking.Auth.Domain;

import com.busbooking.Auth.Application.Dto.AppUserInputDto;
import com.busbooking.Auth.Application.Dto.AppUserOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {

    UsersMapper MAP = Mappers.getMapper(UsersMapper.class);

    UsersEntity appUserInputToUserEntity(AppUserInputDto appUserInputDto);

    AppUserOutputDto userEntityToAppUserOutputDto(UsersEntity usersEntity);
}
