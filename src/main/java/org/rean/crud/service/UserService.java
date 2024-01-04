package org.rean.crud.service;

import org.rean.crud.model.Users;
import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.model.response.PageResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto addUser(UserRequest userRequest);

    Users addnewUser(Users users);

    PageResponse<List<UserDto>> getAlUsers(Integer pageNo, Integer pageSize);

    UserDto getUserById(UUID id);

    void deleteUserById(UUID id);

    UserDto updateUserById(UserRequest userRequest, UUID id);

}
