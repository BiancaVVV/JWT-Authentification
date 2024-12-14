package com.example.backend.controllers;



import com.example.backend.config.UserAuthenticationProvider;
import com.example.backend.dtos.CredentialsDto;
import com.example.backend.dtos.SignUpDto;
import com.example.backend.dtos.UserDto;
import com.example.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3003", allowCredentials = "true")
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        //Metoda login din UserService verifica dacă utilizatorul există și dacă parola este corectă
        UserDto userDto = userService.login(credentialsDto);
        //generarea tokenului
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
