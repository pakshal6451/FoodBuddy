package foodBuddy.foodBuddy.groupManagementTests;

import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupControllerTests {


//    private App

//    @Mock
//    private GroupCreationRequest groupCreationRequest;

//    @Mock
//    private GroupJoinRequest groupJoinRequest;

//    @Mock
//    private ViewGroupUsersResponse viewGroupResponse;

//    @Mock
//    private GroupCreationResponse groupCreationResponse;

//    @Mock
//    private GroupJoinResponse groupJoinResponse;

    @Mock
//    private GroupRepository groupRepository;
//
//    @Mock
//    private UserRepository userRepository;

    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        groupServices = new GroupServices(groupRepository);
//    }
//
//    @Test
//    void testCreateGroup() throws Exception {
//        when(groupServices.createGroup(groupCreationRequest)).thenReturn(groupCreationResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/groupApi/Create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{}");
//    }
//
//    @Test
//    void testJoinGroup() throws Exception {
//        when(groupServices.joinGroup(groupJoinRequest)).thenReturn(groupJoinResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/groupApi/Join")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{}");
//    }
//
//    @Test
//    void testViewGroup() throws Exception {
//        String groupCode = "Test Code";
//        List<User> users = new ArrayList<>();
//        users.add(new User("Test User", groupCode));
//        viewGroupResponse.setGroupUsersList(users);
//        viewGroupResponse.setMessage("Found Members");
//        viewGroupResponse.setStatus("success");
//        when(groupServices.viewGroup(groupCode)).thenReturn(viewGroupResponse);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/groupApi/view")
//                .param("groupCode", groupCode))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(result.getResponse().getContentAsString(), "{\"groupUsersList\":[{\"name\":\"Test User\",\"groupName\":\"Test Code\"}],\"message\":\"Found Members\",\"status\":\"success\"}");
//    }
}
