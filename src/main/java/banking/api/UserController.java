package banking.api;

import banking.api.dto.ErrorRequest;
import banking.api.dto.UserRequest;
import banking.domain.entity.User;
import banking.domain.mapper.UserMapper;
import banking.exception.InvalidException;
import banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService ;
    private final UserMapper userMapper;

    //* Client should be able to sign up with email & password
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public long newUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userMapper.mapToUser(userRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserRequest> allUsers() {
        return userMapper.mapToUserRequestList(userService.allUsers());
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad User")
    ErrorRequest onSaveError(Exception e) {
        return new ErrorRequest("400", e.toString());
    }
}
