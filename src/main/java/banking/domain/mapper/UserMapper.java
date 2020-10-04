package banking.domain.mapper;

import banking.api.dto.MovementRequest;
import banking.api.dto.UserRequest;
import banking.domain.entity.Movement;
import banking.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User mapToUser(UserRequest userRequest){
        User user = new User();
        user.setUserID(userRequest.getUserID());
        user.setEmail(userRequest.getEmail());
        user.setPasshash(userRequest.getPasshash());
        return user;
    }

    public UserRequest mapToUserRequest(User user){
        UserRequest userRequest = new UserRequest();
        userRequest.setUserID(user.getUserID());
        userRequest.setEmail(user.getEmail());
        userRequest.setPasshash(user.getPasshash());
        return userRequest;
    }

    public List<UserRequest> mapToUserRequestList(List<User> users){
        List<UserRequest> result = new ArrayList();
        users.forEach(user -> result.add(mapToUserRequest(user)));
        return result;
    }

    public List<User> mapToUserList(List<UserRequest> usersRequest){
        List<User> result = new ArrayList();
        usersRequest.forEach(user -> result.add(mapToUser(user)));
        return result;
    }
}
