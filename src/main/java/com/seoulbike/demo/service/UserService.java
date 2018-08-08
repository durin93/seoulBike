package com.seoulbike.demo.service;

import com.seoulbike.demo.UnAuthenticationException;
import com.seoulbike.demo.UnAuthorizedException;
import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.domain.UserRepository;
import com.seoulbike.demo.dto.UserDto;
import com.seoulbike.demo.security.ExistException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    public User findById(User loginUser, long id) {
        return userRepository.findById(id)
                .filter(user -> user.equals(loginUser))
                .orElseThrow(UnAuthorizedException::new);
    }

    public User add(UserDto userDto){
        if(userRepository.findByUserId(userDto.getUserId()).isPresent()){
            throw new UnAuthorizedException("이미 존재하는 아이디");
        }
        return userRepository.save(userDto.toUser());
    }

    public void createUser(UserDto userDto) {
        if(userRepository.findByUserId(userDto.getUserId()).isPresent()){
            throw new ExistException("이미 존재하는 아이디");
        }
        userRepository.save(userDto.toUser());
    }

    public User login(String userId, String password) throws UnAuthenticationException {
        return userRepository.findByUserId(userId)
                .filter(user -> user.matchPassword(password))
                .orElseThrow(UnAuthorizedException::new);
    }
}
