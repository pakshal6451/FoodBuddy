package foodBuddy.foodBuddy.groupManagementTests;

//import foodBuddy.foodBuddy.appuser.User;
import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.groupManagement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppGroupServiceTests {

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
    void testCreateGroupSuccess() {
        // Arrange
        String groupName="SampleGroup";
        String groupCode="1234";
        AppGroup appGroup = new AppGroup(groupName,groupCode);

        when(groupRepository.findByGroupName(groupName)).thenReturn(Optional.empty());

        GroupCreationResponse response = appGroupService.CreateGroup(appGroup);
        assertEquals("Group created successfully",response.getMessage());
        assertEquals("success",response.getStatus());
    }

    @Test
    void testCreateGroupFailure() {
        // Arrange
        String groupName="SampleGroup";
        String groupCode="1234";
        AppGroup appGroup = new AppGroup(groupName,groupCode);
        when(groupRepository.findByGroupName(groupName)).thenReturn(Optional.of(appGroup));
        GroupCreationResponse response = appGroupService.CreateGroup(appGroup);
        assertEquals("GroupName Exists",response.getMessage());
        assertEquals("failure",response.getStatus());
    }

    @Test
    void testJoinGroupSuccess() {
        String groupName="SampleGroup";
        String groupCode="1234";
        String userName="user@email.com";
        GroupJoinRequest request = new GroupJoinRequest(groupCode,userName);
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(groupCode);
        GroupJoinResponse response = appGroupService.joinGroup(request);
        assertEquals("Joined successfully",response.getMessage());
        assertEquals("success",response.getStatus());
    }

    @Test
    void testJoinGroupFailure() {
        String groupName="SampleGroup";
        String groupCode="1234";
        String userName="user@email.com";
        GroupJoinRequest request = new GroupJoinRequest(groupCode,userName);
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(" ");
        GroupJoinResponse response = appGroupService.joinGroup(request);
        assertEquals("please verify the groupCode: Unable to join",response.getMessage());
        assertEquals("failure",response.getStatus());
    }

    @Test
    void testFindGroupUsersSuccess() {
        String groupCode="1234";
        List<ViewGroupUsers> users = new ArrayList<ViewGroupUsers>();
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(groupCode);
        when(userRepository.findUsersByGroupCode(groupCode)).thenReturn(users);
        ViewGroupUsersResponse response = appGroupService.findGroupUsers(groupCode);
        assertEquals("Found Members",response.getMessage());
        assertEquals("success",response.getStatus());
        assertEquals(0,response.getGroupUsersList().size());
    }

    @Test
    void testFindGroupUsersFailure() {
        String groupCode="1234";
        List<ViewGroupUsers> users = new ArrayList<ViewGroupUsers>();
        when(groupRepository.findGroupByCode(groupCode)).thenReturn(" ");
        when(userRepository.findUsersByGroupCode(groupCode)).thenReturn(users);
        ViewGroupUsersResponse response = appGroupService.findGroupUsers(groupCode);
        assertEquals("Invalid GroupCode",response.getMessage());
        assertEquals("failure",response.getStatus());
        assertEquals(null,response.getGroupUsersList());
    }

    @Test
    void testLeaveGroupNotInGroup() {
    // Arrange
    String groupCode = "1234";
    String userName = "JohnDoe";
    LeaveGroupRequest request = new LeaveGroupRequest(groupCode, userName);

    when(groupRepository.findGroupByCode(groupCode)).thenReturn("");

    LeaveGroupResponse response = appGroupService.leaveGroup(request);
    assertEquals("Does not belong to any group", response.getMessage());
    assertEquals("failure", response.getStatus());
  }

//  @Test
//  void testGenerateCode() {
//    // Arrange
//    when(groupRepository.findGroupByCode(anyString())).thenReturn(null);
//
//    // Act
//    String groupCode = appGroupService.generateCode();
//
//    // Assert
//    assertNotNull(groupCode);
//    assertTrue(groupCode.matches("\\d{6}")); // assert that the generated code is a 6-digit number
//  }

  @Test
   void testFindGroupUsersInvalidGroupCode() {
    // Arrange
    String groupCode = "invalidCode";

    when(groupRepository.findGroupByCode(groupCode)).thenReturn("");

    ViewGroupUsersResponse response = appGroupService.findGroupUsers(groupCode);
    assertNull(response.getGroupUsersList());
    assertEquals("Invalid GroupCode", response.getMessage());
    assertEquals("failure", response.getStatus());
  }
}
    
