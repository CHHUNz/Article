package org.rean.crud.service;

import org.rean.crud.model.Users;
import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.model.response.PageResponse;

import java.util.List;

public interface UserService {
    UserDto addUser(UserRequest userRequest);

    Users addnewUser(Users users);

    PageResponse<List<UserDto>> getAlUsers(Integer pageNo, Integer pageSize);

    UserDto getUserById(Integer id);

    void deleteUserById(Integer id);

    UserDto updateUserById(UserRequest userRequest, Integer id);

}
