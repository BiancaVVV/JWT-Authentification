package com.example.backend.mappers;

import com.example.backend.dtos.SignUpDto;
import com.example.backend.dtos.UserDto;
import com.example.backend.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T09:36:40+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.login( user.getLogin() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( signUpDto.getFirstName() );
        user.lastName( signUpDto.getLastName() );
        user.login( signUpDto.getLogin() );

        return user.build();
    }
}
