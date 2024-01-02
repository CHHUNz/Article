package org.rean.crud.controller;

import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ApiResponse<?> addUser(@RequestBody UserRequest userRequest) {

        if (userRequest == null || userRequest.getName() == null || userRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Username can not blank");
        }
        var payload = userService.addUser(userRequest);
        return ApiResponse.<UserDto> builder()
                .message("Insert Successfully")
                .status(HttpStatus.OK)
                .payload(payload)
                .build();

    }
}
