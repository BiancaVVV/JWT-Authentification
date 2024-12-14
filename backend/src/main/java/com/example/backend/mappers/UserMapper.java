package com.example.backend.mappers;


import com.example.backend.dtos.SignUpDto;
import com.example.backend.dtos.UserDto;
import com.example.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    //Transformă un obiect SignUpDto (datele primite de la client) într-un obiect User
    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
