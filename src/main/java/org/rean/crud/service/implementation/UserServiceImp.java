package org.rean.crud.service.implementation;

import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto addUser(UserRequest userRequest) {
        var userEntity = userRequest.toEntity();
        return userRepository.save(userEntity).toDto();
    }
}
