package org.rean.crud.service.implementation;

import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.model.response.PageResponse;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Users addnewUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public PageResponse<List<UserDto>> getAlUsers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<UserDto> pageResult = userRepository.findAll(pageable).map(Users::toDto);
        return PageResponse.<List<UserDto>>builder()
                .message("get all users successfully")
                .status(HttpStatus.OK)
                .payload(pageResult.getContent())
                .page(pageNo)
                .size(pageSize)
                .totalElement(pageResult.getTotalElements())
                .totalPage(pageResult.getTotalPages())
                .build();
    }

    @Override
    public UserDto getUserById(Integer id) {
        return userRepository.findById(id).get().toDto();
    }

    @Override
    public void deleteUserById(Integer id) {
        Users users = userRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("Id not found"));
        userRepository.deleteById(users.getId());
    }

    @Override
    public UserDto updateUserById(UserRequest userRequest, Integer id) {
        Optional<Users> optionalUser = Optional.ofNullable(userRepository.findById(id).orElseThrow(()->new NotFoundExceptionClass("there is no data")));
        if (optionalUser.isPresent()){
            Users users = optionalUser.get();
            // update
            users.setUsername(userRequest.getName());
            users.setRole(userRequest.getRole());
            return userRepository.save(users).toDto();
        } else {
            throw new NotFoundExceptionClass("User not found with id : " + id);
        }
    }
}
