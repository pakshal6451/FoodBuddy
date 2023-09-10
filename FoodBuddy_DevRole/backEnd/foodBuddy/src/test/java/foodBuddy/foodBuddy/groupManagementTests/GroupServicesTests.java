package foodBuddy.foodBuddy.groupManagementTests;


import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.groupManagement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GroupServicesTests {

    private AppGroupService appGroupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appGroupService = new AppGroupService(groupRepository, userRepository, expenseRepository);
    }


    @Test
    public void testCreateGroupSuccess() {

        String groupCode="1234";
        String groupName="DemoGroup";
        String userName="user@email.com";
        GroupCreationRequest request = new GroupCreationRequest(groupCode,groupCode,userName);
        GroupJoinRequest joinReq = new GroupJoinRequest(groupCode, userName);
        GroupJoinResponse groupJoinResponse = new GroupJoinResponse();
        groupJoinResponse.setStatus("success");
        groupJoinResponse.setMessage("Joined successfully");
        when(appGroupService.generateCode()).thenReturn(groupCode);
        GroupCreationResponse response = appGroupService.createGroup(request);
        assertEquals("Group created successfully",response.getMessage());
    }

    @Test
    public void testCreateGroupFailure() {

        String groupCode="1234";
        String groupName="DemoGroup";
        String userName="user@email.com";
        GroupCreationRequest request = new GroupCreationRequest(groupCode,groupCode,userName);
        GroupJoinRequest joinReq = new GroupJoinRequest(groupCode, userName);
        GroupJoinResponse groupJoinResponse = new GroupJoinResponse();
        groupJoinResponse.setStatus("success");
        groupJoinResponse.setMessage("Joined successfully");
        when(appGroupService.generateCode()).thenReturn(groupCode);
        GroupCreationResponse response = appGroupService.createGroup(request);
        assertEquals("joinFailed",response.getStatus());
    }
}
