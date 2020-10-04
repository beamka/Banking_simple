package banking.api.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequest {
    private Long userID;
    private String email;
    private String passhash;
}
