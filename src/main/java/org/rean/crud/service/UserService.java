package org.rean.crud.service;

import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;

public interface UserService {
    UserDto addUser(UserRequest userRequest);
}
