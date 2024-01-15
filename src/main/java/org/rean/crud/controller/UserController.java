package org.rean.crud.controller;

import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.UserDto;
import org.rean.crud.model.request.UserRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin
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

    @GetMapping("")
    public ResponseEntity<?> getAllUser(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
        return ResponseEntity.ok().body(userService.getAlUsers(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getUserById(@PathVariable("id") UUID id){
        var findUser = userService.getUserById(id);
        if (findUser != null){
            return ApiResponse.<UserDto>builder()
                    .message("User was found successfully")
                    .status(HttpStatus.OK)
                    .payload(findUser)
                    .build();
        } else {
            throw new NotFoundExceptionClass(("User not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        userService.deleteUserById(id);
        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .message("Delete successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> updateUserById(@RequestBody UserRequest userRequest, @PathVariable("id") UUID id){
        if (userRequest == null || userRequest.getName() == null || userRequest.getRole() == null){
            throw new IllegalArgumentException("User name can not be blank");
        }
        var payload = userService.updateUserById(userRequest,id);
        return ApiResponse.<UserDto>builder()
                .message("update successfully")
                .status(HttpStatus.OK)
                .payload(payload)
                .build();
    }
}
