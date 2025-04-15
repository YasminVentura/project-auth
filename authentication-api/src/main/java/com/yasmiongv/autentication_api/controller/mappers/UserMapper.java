package com.yasmiongv.autentication_api.controller.mappers;

import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    User toEntity(UserDTO dto);

    UserDTO toDTO(User user);
}
