package banking.web;

import banking.api.AccountController;
import banking.api.UserController;
import banking.api.dto.AccountRequest;
import banking.api.dto.UserRequest;
import banking.domain.entity.Account;
import banking.domain.entity.User;
import banking.domain.mapper.AccountMapper;
import banking.domain.mapper.UserMapper;
import banking.service.AccountService;
import banking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @Mock
    private AccountService accountService;
    @Mock
    private AccountMapper accountMapper;
    private MockMvc mockMvc;

    private Account account;
    private AccountRequest accountRequest;

    @Before
    public void init() {
        account = new Account();
        account.setAmount(10l);

        accountRequest = new AccountRequest();
        accountRequest.setAmount(10l);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new AccountController(accountService, accountMapper))
                .build();
    }

    @Test
    public void shouldBeReturnedAllUsers() throws Exception {
        when(accountService.depositMoney(1L, 10L)).thenReturn(account);
        when(accountMapper.mapToAccountRequest(account)).thenReturn(accountRequest);
        mockMvc.perform(post("/accounts/deposit?user_id=1&sum=10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;"))
                .andExpect(jsonPath("amount").value("10"));
    }

    @Test
    public void ifUsersParamIsMissedThrowException() throws Exception {
        mockMvc.perform(get("/accounts/balance?user_id=test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
