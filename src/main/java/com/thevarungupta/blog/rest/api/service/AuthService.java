package com.thevarungupta.blog.rest.api.service;

import com.thevarungupta.blog.rest.api.payload.LoginDto;
import com.thevarungupta.blog.rest.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
