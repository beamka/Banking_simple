package banking.web;

import banking.api.UserController;
import banking.api.dto.UserRequest;
import banking.domain.entity.User;
import banking.domain.mapper.UserMapper;
import banking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;
    private MockMvc mockMvc;

    private List<User> users;
    private List<UserRequest> users_r;

    @Before
    public void init() {
        User user1 = new User();
        user1.setUserID(1L);
        user1.setEmail("email_1");
        user1.setPasshash("pas111");
        User user2 = new User();
        user2.setUserID(2L);
        user2.setEmail("email_2");
        user2.setPasshash("pas222");
        users = asList(user1, user2);

        UserRequest user11 = new UserRequest();
        user11.setUserID(1L);
        user11.setEmail("email_1");
        user11.setPasshash("pas111");
        UserRequest user22 = new UserRequest();
        user22.setUserID(2L);
        user22.setEmail("email_2");
        user22.setPasshash("pas222");
        users_r = asList(user11, user22);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserController(userService, userMapper))
                .build();
    }

    @Test
    public void shouldBeReturnedAllUsers() throws Exception {
        when(userService.allUsers()).thenReturn(users);
        when(userMapper.mapToUserRequestList(users)).thenReturn(users_r);
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;"))
                .andExpect(jsonPath("$[0].email").value("email_1"))
                .andExpect(jsonPath("$[0].passhash").value("pas111"))
                .andExpect(jsonPath("$[1].email").value("email_2"))
                .andExpect(jsonPath("$[1].passhash").value("pas222"));
    }
}
