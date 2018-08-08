package com.seoulbike.demo.web;

import com.seoulbike.demo.UnAuthorizedException;
import com.seoulbike.demo.domain.Result;
import com.seoulbike.demo.dto.UserDto;
import com.seoulbike.demo.security.ExistException;
import com.seoulbike.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger logger = LoggerFactory.getLogger(ApiUserController.class);
    private static final String LOGIN_FORM = "/users/loginForm";

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Result> create(UserDto userDto) {
        Result result = null;

        try {
            userService.createUser(userDto);
            result = Result.ofSuccess(LOGIN_FORM);
        }catch (ExistException e){
            result = Result.ofFailed(e.getMessage());
        }
        return new ResponseEntity<Result>(result,HttpStatus.CREATED);
    }

}
