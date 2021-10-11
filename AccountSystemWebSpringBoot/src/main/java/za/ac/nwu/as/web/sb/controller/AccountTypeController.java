package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.as.domain.dto.AccountTypeDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTypeFlow;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {
    private final FetchAccountTypeFlow fetchAccountTypeFlow;
//    private final CreateAccountTypeFlow createAccountTypeFlow;

    private static final String APP_URL = "/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-type";

    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;
    @Mock
    private CreateAccountTypeFlow createAccountTypeFlow;
    @Mock
    private ModifyAccountTypeFlow modifyAccountTypeFlow;
    @InjectMocks
    private AccountTypeController controller;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void getAll() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":[" +
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account
        type\",\"creationDate\":[2020,1,1]}," +
                "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"Play account
        type\",\"creationDate\":[2021,4,1]}]}";
        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto("MILES", "Miles account type",
                LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto("PLAY", "Play account type",
                LocalDate.parse("2021-04-01")));
        when(fetchAccountTypeFlow.getAllAccountTypes()).thenReturn(accountTypes);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "all")))
                .servletPath(APP_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTypeFlow, times(1)).getAllAccountTypes();
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        String accountTypeToBeCreated =
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account
        type\",\"creationDate\":[2020,1,1]},";
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account
        type\",\"creationDate\":[2020,1,1]}}";

        AccountTypeDto accountType = new AccountTypeDto("MILES", "Miles
                account type", LocalDate.parse("2020-01-01"));
                when(createAccountTypeFlow.create(eq(accountType))).then(returnsFirstArg());
        MvcResult mvcResult =
                mockMvc.perform(post(ACCOUNT_TYPE_CONTROLLER_URL)
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(accountTypeToBeCreated)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();
        verify(createAccountTypeFlow, times(1)).create(eq(accountType));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void deleteAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"Play account
        type\",\"creationDate\":[2021,4,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("PLAY", "Play
                account type", LocalDate.parse("2021-04-01"));
                when(modifyAccountTypeFlow.deleteAccountType(anyString())).thenReturn(accoun
                        tType);
        MvcResult mvcResult = mockMvc.perform(delete((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                .servletPath(APP_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(modifyAccountTypeFlow,
                times(1)).deleteAccountType(eq("PLAY"));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void updateAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play
        account type name\",\"creationDate\":[2021,4,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("PLAY", "The new
                Play account type name",
                LocalDate.parse("2021-04-01"));
        when(modifyAccountTypeFlow.updateAccountType(anyString(),
                anyString(), any(LocalDate.class))).thenReturn(accountType);
        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                .param("newAccountTypeName", "The new Play account
                        type name")
                                .param("newCreationDate", "2021-04-01")
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(modifyAccountTypeFlow,
                times(1)).updateAccountType(eq("PLAY"),
                eq("The new Play account type name"),
                eq(LocalDate.parse("2021-04-01")));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void updateAccountTypeWithNoOptionalDate() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":" +
                "{\"mnemonic\":\"PLAY\",\"accountTypeName\":\"The new Play
        account type name\",\"creationDate\":[2021,9,1]}}";
        AccountTypeDto accountType = new AccountTypeDto("PLAY", "The new
                Play account type name",
                LocalDate.parse("2021-09-01"));
        when(modifyAccountTypeFlow.updateAccountType(anyString(),
                anyString(), isNull())).thenReturn(accountType);
        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                .param("newAccountTypeName", "The new Play account
                        type name")
                                .servletPath(APP_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(modifyAccountTypeFlow,
                times(1)).updateAccountType(eq("PLAY"),
                eq("The new Play account type name"), eq(null));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
    @Test
    public void updateAccountTypeObitMandatory() throws Exception {
        mockMvc.perform(put((String.format("%s/%s",
                ACCOUNT_TYPE_CONTROLLER_URL, "PLAY")))
                10
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(modifyAccountTypeFlow,
                never()).updateAccountType(anyString(), anyString(), any(LocalDate.class));
        verify(modifyAccountTypeFlow,
                never()).updateAccountType(anyString(), anyString(), isNull());
        verify(modifyAccountTypeFlow,
                never()).updateAccountType(anyString(), isNull(), isNull());
    }

    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow, @Qualifier("createAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow){
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
//        this.createAccountTypeFlow = createAccountTypeFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configured Account types.", notes = "Returns a list of account types.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account type returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAll(){
        List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{mnemomic}")
    @ApiOperation(value = "Fetches the specified AccountType", notes = "Fetches the AccountType corresponding to the give mnemonic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found"),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountType(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){

        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);

        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
